package at.ac.fhcampuswien;

public class Tile {
    private final int bombsNearby;
    private final boolean isBomb;
    private boolean isRevealed = false;
    private boolean isFlagged = false;

    private static final int BOMB_VALUE = 9;

    public Tile(final boolean isBomb, final int bombsNearby) {
        this.isBomb = isBomb;
        this.bombsNearby = bombsNearby;
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

    public int getNumericValue() {
        return isBomb ? BOMB_VALUE : bombsNearby;
    }
}
