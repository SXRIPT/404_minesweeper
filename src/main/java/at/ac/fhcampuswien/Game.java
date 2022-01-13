package at.ac.fhcampuswien;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    // Directions for all adjacent fields of a Tile
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private final Board board;
    private final int MAX_SIZE;
    private boolean lost = false;
    private boolean error = false;

    public Game(final int boardSize, final int bombCount) {
        this.board = new Board(boardSize, bombCount);
        MAX_SIZE = boardSize;
    }

    public void start() {
        board.printTileCheatBoard();
        System.out.println();
        while (!lost && hasNotWon()) {
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

    private boolean hasNotWon() { return board.getRevealCount() + board.getBombCount() != MAX_SIZE * MAX_SIZE; }

    private void revealTile(final Tile tile) {
        if (!tile.isRevealed())  board.increaseRevealCount();
        tile.setRevealed();
    }

    private void revealZeroFields(final int x, final int y) {
        if (isNotInBound(x, y)) return;

        Tile tile = board.getBoard()[y][x];
        if (tile.isRevealed() || tile.isBomb()) return;

        revealTile(tile);
        if (tile.getBombsNearby() > 0) return;

        // Calls revealZeroFields for all adjacent Tiles
        Arrays.stream(DIRECTIONS).forEach(direction -> revealZeroFields(x + direction[0], y + direction[1]));
    }

    private boolean isNotInBound(final int x, final int y) {
        return x < 0 || x >= MAX_SIZE || y < 0 || y >= MAX_SIZE;
    }

    private void handleReveal(final Tile tile, final int x, final int y) {
        int bombsNearBy = tile.getBombsNearby();

        if (bombsNearBy == 0 && !tile.isRevealed() && !tile.isFlagged()) {
            revealZeroFields(x, y);
        } else if (bombsNearBy == Tile.BOMB_VALUE && !tile.isFlagged()) {
            printLoseScreen();
            lost = true;
        } else {
            if (!tile.isFlagged() && !tile.isRevealed()) {
                revealTile(tile);
            } else {
                System.out.println("Tile is flagged or revealed");
                error = true;
            }
        }
    }

    private int inputValidation(final String displayText) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(displayText);
        int number;
        while (true) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Input is not a valid number");
                System.out.print(displayText);
            } else {
                number = scanner.nextInt();
                if (number < 0 || number >= MAX_SIZE) {
                    System.out.println("Input is too big or too small");
                    System.out.print(displayText);
                } else {
                    break;
                }
            }
        }
        return number;
    }


    private void gameLogic() {
        Scanner scanner = new Scanner(System.in);
        List<String> options = Arrays.asList("a", "b");
        while (true) {

            System.out.println("a) Feld aufdecken");
            System.out.println("b) Flag setzen");
            String option = "";

            while (!options.contains(option)) {
                System.out.print("Pick a option: ");
                if (scanner.hasNextLine()) option = scanner.nextLine();
            }

            int x = inputValidation("Input X: ");
            int y = inputValidation("Input Y: ");


            Tile tile = board.getBoard()[y][x];
            switch (option) {
                case "a":
                    handleReveal(tile, x, y);
                    return;
                case "b":
                    if (!tile.isRevealed()) {
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

