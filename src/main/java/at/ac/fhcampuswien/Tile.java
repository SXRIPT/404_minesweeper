package at.ac.fhcampuswien;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Tile {
    private final int bombsNearby;
    private final boolean isBomb;
    private boolean isRevealed = false;
    private boolean isFlagged = false;
    private final int xPosition;
    private final int yPosition;
    private ImageView imageView;
    private Image revealedImage;

    protected static final int BOMB_VALUE = 9;

    public Tile(final int xPosition, final int yPosition, final boolean isBomb, final int bombsNearby) {
        this.isBomb = isBomb;
        this.bombsNearby = bombsNearby;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        setUpTile();
    }

    public void setFlagged() {
        isFlagged = !isFlagged;
    }

    public void setRevealed() {
        isRevealed = true;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public int getBombsNearby() {
        return bombsNearby;
    }

    private void setUpTile() {
        if (isBomb) {
            revealedImage = GUIManager.getInstance().getImage("bomb");
        } else if (bombsNearby == 0) {
            revealedImage = GUIManager.getInstance().getImage("empty");
        } else {
            revealedImage = GUIManager.getInstance().getImage(Integer.toString(bombsNearby));
        }


        imageView = new ImageView(GUIManager.getInstance().getImage("unrevealed"));
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ((ImageView) event.getTarget()).setImage(revealedImage);

                event.consume();
            }
        });
        imageView.setX(xPosition * 32);
        imageView.setY(yPosition * 32);
        GUIManager.getInstance().getAnchorPane().getChildren().add(imageView);
    }
}
