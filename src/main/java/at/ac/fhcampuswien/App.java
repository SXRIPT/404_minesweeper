package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class App extends Application {

    private static int boardSize;
    private static int bombCount;
    public static void main(String[] args){
        //Game game = new Game(16, 40);
        launch();
        //game.start();
        //launch(args);
    }

    public static void newGame(int size, int count) {
        bombCount = count;
        boardSize = size;
        Stage primaryStage = GUIManager.getInstance().getStage();
        new Game(boardSize, bombCount);
        primaryStage.setTitle("404_Minesweeper");
        primaryStage.getIcons().add(new Image("file:src/main/resources/graphics/bomb.png"));
        primaryStage.show();
        GUIManager.createLabel();
    }

    public static void newGame() {
        Stage primaryStage = GUIManager.getInstance().getStage();
        new Game(boardSize, bombCount);
        primaryStage.setTitle("404_Minesweeper");
        primaryStage.getIcons().add(new Image("file:src/main/resources/graphics/bomb.png"));
        primaryStage.show();
    }
    public static int getBombCount(){
        return bombCount;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage = GUIManager.getInstance().getStage();

        // new Game(24, 40);
        GUIManager.startUp();
        primaryStage.setTitle("404_Minesweeper");
        primaryStage.getIcons().add(new Image("file:src/main/resources/graphics/bomb.png"));
        primaryStage.show();
    }









}
