package at.ac.fhcampuswien;

import java.util.Random;

public class Board {
    private int[][] board; // trebuie modificat cu o clasa de contante
    private final int MAX = 16;
    private final int MIN = 0;
    Board() { // constructor fara parametru
        this.board = new int[16][16]; // matrix 16x16
    }

    /*
        9  0  0
        0  9  0
        0  0  0


        i = 0 => row = 0, col = 0 => board[row][col]
        i = 1 => row = 0, col = 0 => board[0][0]
        i = 2 => row = 1; col = 1=> board[1][1]
     */
    public void randomBombs() {
        Random rand = new Random();
        int i = 0;
        while(i < 40) {
            // O linie random
            int row = rand.nextInt((MAX - MIN) + 1) + MIN; // numar random intre min si max
            // o coloana random
            int col = rand.nextInt((MAX - MIN) + 1) + MIN; // numar random intre min si max

            if (board[row][col] != 9) { // asigura unicitate
                board[row][col] = 9;
                i++;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
