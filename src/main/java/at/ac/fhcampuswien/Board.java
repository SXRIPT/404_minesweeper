package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int[][] board; // sollte mit eine Konstanten Klasse ergänzt/geändert
    private final int MAX = 16;
    private final int MIN = 0;

    Board() { // Konstruktor ohne Parameter
        this.board = new int[MAX][MAX]; //
        randomBombs();
        calculateBoard();
    }

    /*
        9  0  0
        0  9  0
        0  0  0


        i = 0 => row = 0, col = 0 => board[row][col]
        i = 1 => row = 0, col = 0 => board[0][0]
        i = 2 => row = 1; col = 1=> board[1][1]
     */
    private void randomBombs() {
        Random rand = new Random();
        int i = 0; // Anzahl der Bomben
        while(i < 40) {
            // row random
            int row = rand.nextInt(MAX); //
            // col random
            int col = rand.nextInt(MAX); //

            if (board[row][col] != 9) { // falls es sich eine Zahl wiederholt, werden trotzdem 40 Zahlen
                board[row][col] = 9;
                i++;
            }
        }
    }

    private void calculateBoard(){

        for(int i = 0; i < MAX ; i++){
            for(int j = 0; j < MAX; j++){
                board[i][j] = checkSurroundings(i,j);
            }
        }


    }
    private int checkSurroundings(int i, int j){                //Überprüft die anliegenden Felder auf Bomben
        int count = 0;
        if(board[i][j] == 9){                                   //Falls das Feld selber ein Bombe ist soll sie natürlich nicht überschrieben werden
            count = 9;
        }
        else {
            for (int x = i - 1; x <= i + 1; x++) {
                for (int y = j - 1; y <= j + 1; y++) {
                    if (x >= 0  && x < MAX && y >= 0 && y < MAX && board[x][y] == 9) {             //1.Teil stellt sicher das nicht außerhalb der Spielfelds geschaut wird, der 2.Teil ob es eine Bombe ist
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void showBoard(){                                        //zeigt den befüllten Array in der Konsole für Probe Zwecke
        for(int i = 0; i < MAX;i++){
            for(int j = 0; j < MAX;j++){
                System.out.print(board[i][j] + "  ");
                if((j + 1)  % 16 == 0){
                    System.out.println("");
                }
            }
        }
    }
    public int getMAX(){ // returns the max value
        return MAX;
    }

    public boolean isBomb(int i,int j){ // returns a boolean if the field is a bomb return true else return false
        if(board[i][j] == 9){
            return true;
        }else {
            return false;
        }

    }


}
