package at.ac.fhcampuswien;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView {
    private final int bombsNearby;
    private final boolean isBomb;
    private boolean isRevealed = false;
    private boolean isFlagged = false;
    private final int xPosition;
    private final int yPosition;
    private Image revealedImage;
    protected static final int BOMB_VALUE = 9;

    public Tile(final int xPosition, final int yPosition, final boolean isBomb, final int bombsNearby) {
        this.isBomb = isBomb;
        this.bombsNearby = bombsNearby;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        setUpTile();
    }

    public void setFlagged() { //gets called when flag is set or unset / changes flag status / updates GUI flag counter
        isFlagged = !isFlagged;
        if (isFlagged) {
            this.setImage(GUIManager.getInstance().getImage("flag"));
            GUIManager.minusLabel();
        } else {
            this.setImage(GUIManager.getInstance().getImage("unrevealed"));
            GUIManager.plusLabel();
        }
    }

    public void setRevealed() { //reveals a tile when clicked on / changes image to revealed image
        isRevealed = true;
        this.setImage(revealedImage);
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

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    private void setUpTile() { //sets up different tile images and hides them
        if (isBomb) {
            revealedImage = GUIManager.getInstance().getImage("bomb");
        } else if (bombsNearby == 0) {
            revealedImage = GUIManager.getInstance().getImage("empty");
        } else {
            revealedImage = GUIManager.getInstance().getImage(Integer.toString(bombsNearby));
        }

        this.setImage(GUIManager.getInstance().getImage("unrevealed")); //hides tile images

        //defines position of tile
        this.setX(xPosition * 32);
        this.setY((yPosition * 32)+20); //+20 additional space for flag counter

        GUIManager.getInstance().getAnchorPane().getChildren().add(this); //adds tile to anchorpane
    }
}
