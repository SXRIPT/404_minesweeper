package at.ac.fhcampuswien;

import java.util.Random;

public class Board {
   static final Random RND = new Random();

    private final Tile[][] tiles;
    private final int boardSize;
    private final int bombCount;
    private int revealCount = 0;

    public Board(final int boardSize, final int bombCount) {
        this.boardSize = boardSize;
        this.bombCount = bombCount;
        this.tiles = new Tile[boardSize][boardSize];

        placeRandomBombs();
        fillBoard();
    }

    private void placeRandomBombs() {
        int bombsPlaced = 0;
        while (bombsPlaced < bombCount) {
            int row = RND.nextInt(boardSize);
            int col = RND.nextInt(boardSize);

            // Ensure that a bomb is not placed on a Tile with a bomb
            if (tiles[row][col] == null || !tiles[row][col].isBomb()) {
                tiles[row][col] = new Tile(true, Tile.BOMB_VALUE);
                bombsPlaced++;
            }
        }
    }

    private void fillBoard() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                if (tiles[y][x] == null) {
                    tiles[y][x] = new Tile(false, checkSurroundings(y, x));
                }
            }
        }
    }

    // Checks adjacent fields for bombs
    private int checkSurroundings(final int yPosition, final int xPosition) {
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
    }


    public void printGrid() {
        System.out.print("     ");
        for (int i = 0; i < tiles.length; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        System.out.print("      ");
        System.out.println("-  ".repeat(tiles.length));
    }

    public void printTileCheatBoard() {
        printGrid();
        for (int y = 0; y < boardSize; y++) {
            System.out.printf("%2d |  ", y);
            for (int x = 0; x < boardSize; x++) {
                System.out.print(tiles[y][x].getBombsNearby());

                System.out.print((x + 1) % boardSize == 0 ? System.lineSeparator() : "  ");
            }
        }
    }

    public void printTileBoard() {
        printGrid();
        for (int y = 0; y < boardSize; y++) {
            System.out.printf("%2d |  ", y);
            for (int x = 0; x < boardSize; x++) {
                if (tiles[y][x].isRevealed()) {
                    System.out.print(tiles[y][x].getBombsNearby());
                } else {
                    System.out.print(tiles[y][x].isFlagged() ? "F" : "X");
                }

                System.out.print((x + 1) % boardSize == 0 ? System.lineSeparator() : "  ");
            }
        }
    }
}
