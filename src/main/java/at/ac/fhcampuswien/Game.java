package at.ac.fhcampuswien;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final int MAX_SIZE;
    private boolean lost = false;
    private boolean error = false;

    private static final int BOMB_VALUE = 9;

    public Game(final int boardSize, final int bombCount) {
        this.board = new Board(boardSize, bombCount);
        MAX_SIZE = boardSize;
    }

    public void start() {
        board.printTileCheatBoard();
        System.out.println();
        while (!lost && board.getRevealCount() + board.getBombCount() != MAX_SIZE * MAX_SIZE) {
            if (!error) {
                board.printTileBoard();
            } else {
                error = false;
            }
            gameLogic();
        }
        if (!lost) {
            printWinScreen();
        }
    }

    private void revealTile(final int x, final int y) {
        if (!board.getBoard()[y][x].isRevealed())  board.increaseRevealCount();
        board.getBoard()[y][x].setRevealed();
    }

    private void revealCorners(final int x, final int y) {
        if (y < 0 || y >= MAX_SIZE || x < 0 || x >= MAX_SIZE) return;
        if (board.getBoard()[y][x].getBombsNearby() == 0) return;

        revealTile(x, y);
    }
    private void revealSurroundingZeros(final int x, final int y) {
        if (y < 0 || y >= MAX_SIZE || x < 0 || x >= MAX_SIZE) return;

        Tile tile = board.getBoard()[y][x];

        if (tile.getBombsNearby() == 0 && !tile.isRevealed()) {
            revealTile(x, y);

            revealCorners(x - 1, y - 1);
            revealCorners(x + 1, y + 1);
            revealCorners(x + 1, y - 1);
            revealCorners(x - 1, y + 1);

            revealSurroundingZeros(x, y + 1);
            revealSurroundingZeros(x, y - 1);
            revealSurroundingZeros(x + 1, y);
            revealSurroundingZeros(x  - 1, y);
        } else {
            revealTile(x, y);
        }
    }

    private void handleReveal(final Tile tile, final int x, final int y) {
        int bombsNearBy = tile.getBombsNearby();

        if (bombsNearBy == 0 && !tile.isRevealed() && !tile.isFlagged()) {
            revealSurroundingZeros(x, y);
        } else if (bombsNearBy == BOMB_VALUE) {
            printLoseScreen();
            lost = true;
        } else {
            if (!tile.isFlagged() && !tile.isRevealed()) {
                revealTile(x, y);
            } else {
                System.out.println("Tile is flagged or revealed");
                error = true;
            }
        }
    }

    private void gameLogic() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("a) Feld aufdecken");
            System.out.println("b) Flag setzen");

            System.out.print("Pick a option: ");
            String option = scanner.nextLine();

            System.out.print("Input X: ");
            int x = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Input Y: ");
            int y = scanner.nextInt();
            scanner.nextLine();

            Tile tile = board.getBoard()[y][x];
            switch (option) {
                case "a":
                    handleReveal(tile, x, y);
                    return;
                case "b":
                    if(!tile.isRevealed()) {
                        tile.setFlagged();
                    } else {
                        System.out.println("Tile is revealed");
                        error = true;
                    }
                    return;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }

    private void printWinScreen() {
        System.out.println(" __      __                         __       __   ______   __    __        __ \n" +
                "/  \\    /  |                       /  |  _  /  | /      \\ /  \\  /  |      /  |\n" +
                "$$  \\  /$$/______   __    __       $$ | / \\ $$ |/$$$$$$  |$$  \\ $$ |      $$ |\n" +
                " $$  \\/$$//      \\ /  |  /  |      $$ |/$  \\$$ |$$ |  $$ |$$$  \\$$ |      $$ |\n" +
                "  $$  $$//$$$$$$  |$$ |  $$ |      $$ /$$$  $$ |$$ |  $$ |$$$$  $$ |      $$ |\n" +
                "   $$$$/ $$ |  $$ |$$ |  $$ |      $$ $$/$$ $$ |$$ |  $$ |$$ $$ $$ |      $$/ \n" +
                "    $$ | $$ \\__$$ |$$ \\__$$ |      $$$$/  $$$$ |$$ \\__$$ |$$ |$$$$ |       __ \n" +
                "    $$ | $$    $$/ $$    $$/       $$$/    $$$ |$$    $$/ $$ | $$$ |      /  |\n" +
                "    $$/   $$$$$$/   $$$$$$/        $$/      $$/  $$$$$$/  $$/   $$/       $$/ \n" +
                "                                                                              \n" +
                "                                                                              \n" +
                "                                                                              ");
    }

    private void printLoseScreen() {
        System.out.println(" __      __                         __         ______    ______   ________        __ \n" +
                "/  \\    /  |                       /  |       /      \\  /      \\ /        |      /  |\n" +
                "$$  \\  /$$/______   __    __       $$ |      /$$$$$$  |/$$$$$$  |$$$$$$$$/       $$ |\n" +
                " $$  \\/$$//      \\ /  |  /  |      $$ |      $$ |  $$ |$$ \\__$$/    $$ |         $$ |\n" +
                "  $$  $$//$$$$$$  |$$ |  $$ |      $$ |      $$ |  $$ |$$      \\    $$ |         $$ |\n" +
                "   $$$$/ $$ |  $$ |$$ |  $$ |      $$ |      $$ |  $$ | $$$$$$  |   $$ |         $$/ \n" +
                "    $$ | $$ \\__$$ |$$ \\__$$ |      $$ |_____ $$ \\__$$ |/  \\__$$ |   $$ |          __ \n" +
                "    $$ | $$    $$/ $$    $$/       $$       |$$    $$/ $$    $$/    $$ |         /  |\n" +
                "    $$/   $$$$$$/   $$$$$$/        $$$$$$$$/  $$$$$$/   $$$$$$/     $$/          $$/ \n" +
                "                                                                                     \n" +
                "                                                                                     \n" +
                "                                                                                     ");
    }
}

