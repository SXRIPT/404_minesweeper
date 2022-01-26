package at.ac.fhcampuswien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Game {
    private final Board board;
    private final int MAX_SIZE;
    private boolean lost = false;

    public Game(int boardSize, int bombCount) {
        this.board = new Board(boardSize, bombCount);
        MAX_SIZE = boardSize;
        addOnClickToTiles();
        GUIManager.createLabel(); //label for flagCount
    }

    private void addOnClickToTiles() { //action handleReveal is added to each tile
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard()[row].length; col++) {
                board.getBoard()[row][col].setOnMouseClicked(this::handleReveal);
            }
        }
    }

    private boolean hasWon() { return board.getRevealCount() + board.getBombCount() == MAX_SIZE * MAX_SIZE; } //checks if player has won

    private void revealTile(Tile tile) { //reveals the tile and increases the revealCount
        if (!tile.isRevealed()) board.increaseRevealCount();
        tile.setRevealed();
    }

    private void revealZeroFields(int x, int y) { //checks for adjacent empty tiles if empty tile is opened and reveals them
        if (isNotInBound(x, y)) return; //checks for bounds of the board

        Tile tile = board.getBoard()[y][x];
        if (tile.isRevealed() || tile.isBomb()) return; //checks if tile is already revealed or is a bomb

        revealTile(tile);
        if (tile.getBombsNearby() > 0) return; //if tile has a number stop searching in that direction

        // Calls revealZeroFields for all adjacent Tiles
        for (int xPos = x - 1; xPos <= x + 1 ; xPos++) {
            for (int yPos = y - 1; yPos <= y + 1; yPos++) {
                revealZeroFields(xPos, yPos);
            }
        }
    }

    private boolean isNotInBound(int x, int y) { //checks if tile is still in bounds
        return x < 0 || x >= MAX_SIZE || y < 0 || y >= MAX_SIZE;
    }

    private void handleReveal(MouseEvent event) {
        if (!(event.getSource() instanceof Tile)) return; //checks if the object clicked is a tile and returns if not a tile
        if (lost) return;

        Tile tile = (Tile) event.getSource(); //casts object to tile
        int bombsNearBy = tile.getBombsNearby();

        if (event.getButton().equals(MouseButton.SECONDARY) && !tile.isRevealed()) { //right click flags the tile
            tile.setFlagged();
            return;
        }

        if (bombsNearBy == 0 && !tile.isRevealed() && !tile.isFlagged()) { // if opened tile is empty - open surrounding tiles
            revealZeroFields(tile.getxPosition(), tile.getyPosition());
        } else if (bombsNearBy == Tile.BOMB_VALUE && !tile.isFlagged()) { // if opened tile is a bomb - show losing screen

            tile.setRevealed();
            lost = true;
            endScreen();

        } else if (!tile.isFlagged() && !tile.isRevealed()) { //checks for flag or if the tile is already revealed
                revealTile(tile);
            }
        
        if(hasWon()){
            endScreen();
        }
    }

    private void endScreen(){ //prints endScreen depending on outcome

        ImageView imageView = new ImageView();
        if(lost) imageView.setImage(GUIManager.getInstance().getImage("lost"));
        else imageView.setImage(GUIManager.getInstance().getImage("won"));

        //restart and exit buttons
        Button restartButton = new Button();
        restartButton.setText("Restart");
        restartButton.setMinWidth(80);

        restartButton.setOnAction(eventButtonClick -> {
            GUIManager.getInstance().getStage().close();
            GUIManager.getInstance().reset();
            App.newGame();
        });

        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setMinWidth(80);

        exitButton.setOnAction(eventButtonClick -> Platform.exit());

        //position of buttons
        AnchorPane anchorPane = new AnchorPane();

        AnchorPane.setRightAnchor(exitButton,20.0);
        AnchorPane.setBottomAnchor(exitButton, 70.0);

        AnchorPane.setRightAnchor(restartButton,20.0);
        AnchorPane.setBottomAnchor(restartButton, 30.0);

        //adds buttons and image to anchorPane
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(restartButton);
        anchorPane.getChildren().add(exitButton);

        Scene scene = new Scene(anchorPane, 800, 600);

        //delay before loseScreen
        Timeline loseDelay = new Timeline(
                new KeyFrame(Duration.millis(1500),
                        event -> GUIManager.getInstance().getStage().setScene(scene)));
        if(lost)loseDelay.play();
        else GUIManager.getInstance().getStage().setScene(scene);

    }
}

