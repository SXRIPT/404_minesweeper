package at.ac.fhcampuswien;

import java.util.Scanner;

public class Game {
    Board board = new Board();
    Scanner sc = new Scanner(System.in);
    final int MAX_VALUE = board.getMAX();
    boolean lost = false;
    boolean error = false;
    public void revealCorners(int x, int y) {
        if(y < 0 || y >= MAX_VALUE || x < 0 || x >= MAX_VALUE) return;
        if(board.getBoard()[y][x].getBombsNearby() == 0) return;


        board.getBoard()[y][x].setRevealed();
    }
    public void revealSurroundingZeros(int x, int y) {

        if(y < 0 || y >= MAX_VALUE || x < 0 || x >= MAX_VALUE) return;

        if(board.getBoard()[y][x].getBombsNearby() == 0 && !board.getBoard()[y][x].isRevealed) {
            board.getBoard()[y][x].setRevealed();
            revealCorners(x - 1, y - 1);
            revealCorners(x + 1, y + 1);
            revealCorners(x + 1, y - 1);
            revealCorners(x - 1, y + 1);

            revealSurroundingZeros(x, y + 1);
            revealSurroundingZeros(x, y - 1);
            revealSurroundingZeros(x + 1, y);
            revealSurroundingZeros(x  - 1, y);
        } else {
            board.getBoard()[y][x].setRevealed();
            return;
        }
    }

    public void gameLogic() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("a) Feld aufdecken");
            System.out.println("b) Flage setzen");

            System.out.print("Pick a option: ");
            String option = scanner.nextLine();


            System.out.print("Input X: ");
            int x = scanner.nextInt();

            System.out.print("Input Y: ");
            int y = scanner.nextInt();
            switch (option) {
                case "a":

                    int bombsNearBy = board.getBoard()[y][x].getBombsNearby();

                    if(bombsNearBy == 0 && !board.getBoard()[y][x].isRevealed && !board.getBoard()[y][x].isFlagged) {
                        revealSurroundingZeros(x, y);
                    } else if(bombsNearBy == 9) {
                        System.out.println("You lost");
                        lost = true;
                    } else {
                        if(! board.getBoard()[y][x].isFlagged && !board.getBoard()[y][x].isRevealed) {
                            board.getBoard()[y][x].setRevealed();
                        } else {
                            System.out.println("Tile is flagged or revealed");
                            error = true;
                        }

                    }
                    return;
                case "b":
                    if(!board.getBoard()[y][x].isRevealed) {
                        board.getBoard()[y][x].setFlagged();
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
    public void start() {
        board.printTileCheatBoard();
        System.out.println();
        while (!lost && board.getRevealCount() + board.getBOMB_COUNT() != MAX_VALUE * MAX_VALUE) {
            if(!error) {
                board.printTileBoard();
            } else { error = false; }
            gameLogic();
        }
        if(!lost) {
            System.out.println("YOU WON!");
        }
    }
}
