package at.ac.fhcampuswien;

import java.util.Random;

public class Board {
   static final Random RND = new Random();

    private final Tile[][] tiles;
    private final int boardSize;
    private final int bombCount;
    private int revealCount = 0; //count of revealed tiles

    public Board(int boardSize, int bombCount) { //when a new board is initialized bombs are placed and the board gets filled
        this.boardSize = boardSize;
        this.bombCount = bombCount;
        this.tiles = new Tile[boardSize][boardSize];


        placeRandomBombs(); //placing bombs on the board
        fillBoard(); //completing the rest of the board
    }

    private void placeRandomBombs() { //selects random tiles and sets them as bombs
        int bombsPlaced = 0;
        while (bombsPlaced < bombCount) { //places bombs until the bombCount is reached
            int row = RND.nextInt(boardSize);
            int col = RND.nextInt(boardSize);

            // Ensure that a bomb is not placed on a Tile with a bomb
            if (tiles[row][col] == null || !tiles[row][col].isBomb()) {
                tiles[row][col] = new Tile(col, row, true, Tile.BOMB_VALUE); //creates tiles with bombs
                bombsPlaced++;
            }
        }
    }

    private void fillBoard() { //creates tiles where no bombs are placed
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                if (tiles[y][x] == null) {
                    tiles[y][x] = new Tile(x, y,false, checkSurroundings(y, x));
                }
            }
        }
    }

    // Checks adjacent fields for bombs and returns int with bombCount
    private int checkSurroundings(int yPosition, int xPosition) {
        int count = 0;
        // If the Tile itself is a bomb it should not be overwritten
        if (tiles[yPosition][xPosition] != null && tiles[yPosition][xPosition].isBomb()) {
            count = Tile.BOMB_VALUE;
        } else {
            for (int y = yPosition - 1; y <= yPosition + 1; y++) {
                for (int x = xPosition - 1; x <= xPosition + 1; x++) {
                    //  Make sure not to look outside the grid and that the tile is a bomb
                    if (y >= 0  && y < boardSize && x >= 0 && x < boardSize
                            && tiles[y][x] != null && tiles[y][x].isBomb()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public Tile[][] getBoard() {
        return tiles;
    }

    public int getRevealCount() {
        return revealCount;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void increaseRevealCount() {
        this.revealCount++;
    } //increases count of revealed tiles

}
