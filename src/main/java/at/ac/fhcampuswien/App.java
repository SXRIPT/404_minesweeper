package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args){
        //Game game = new Game(16, 40);
        launch();
        //game.start();
        //launch(args);
    }

    public static void newGame() {
        Stage primaryStage = GUIManager.getInstance().getStage();
        new Game(24, 40);
        primaryStage.setTitle("404_Minesweeper");
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage = GUIManager.getInstance().getStage();
        new Game(24, 40);

        primaryStage.setTitle("404_Minesweeper");
        primaryStage.show();
    }
}
