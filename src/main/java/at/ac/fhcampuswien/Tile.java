package at.ac.fhcampuswien;

public class Tile {
    private int xAchse; // x position of the tiles
    private int yAchse; // y position of the tiles
    private int bombsNearby; // number if bombs nearby
    boolean isBomb; // shows if the tile is a bomb tile

    public Tile(int xAchse, int yAchse, boolean isBomb) { // constructor which needs x psoition, y position and if the tile is a bomb;
        this.xAchse = xAchse;
        this.yAchse = yAchse;
        this.isBomb = isBomb;
    }

    public int getxAchse() {
        return xAchse;
    }

    public int getyAchse() {
        return yAchse;
    }

    public int getBombsNearby() {
        return bombsNearby;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setxAchse(int xAchse) {
        this.xAchse = xAchse;
    }

    public void setyAchse(int yAchse) {
        this.yAchse = yAchse;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setBombsNearby(int bombsNearby) {
        this.bombsNearby = bombsNearby;
    }
}
