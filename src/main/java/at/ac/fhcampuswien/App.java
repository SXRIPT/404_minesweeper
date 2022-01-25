package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class App extends Application {

    private static int boardSize;
    private static int bombCount;
    public static void main(String[] args){ //launches game
        launch();
    }

    public static void newGame(int size, int count) {
        bombCount = count;
        boardSize = size;
        Stage primaryStage = GUIManager.getInstance().getStage();
        new Game(boardSize, bombCount);
        //sets title and icon for gameWindow
        primaryStage.setTitle("404_Minesweeper");
        primaryStage.getIcons().add(new Image("file:src/main/resources/graphics/bomb.png"));
        primaryStage.show();
    }

    public static void newGame() { //if newGame is called from restart button restart with same size and difficulty
        newGame(boardSize,bombCount);
    }
    public static int getBombCount(){
        return bombCount;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage = GUIManager.getInstance().getStage();

        GUIManager.startUp();
        //sets title and icon for size and difficulty screen
        primaryStage.setTitle("404_Minesweeper");
        primaryStage.getIcons().add(new Image("file:src/main/resources/graphics/bomb.png"));
        primaryStage.show();
    }
}
