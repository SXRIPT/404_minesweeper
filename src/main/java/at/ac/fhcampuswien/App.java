package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class App extends Application {



    public static void main(String[] args){
        //Game game = new Game(16, 40);
        launch();
        //game.start();
        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = GUIManager.getInstance().getStage();
        Game game = new Game(16, 40);

        primaryStage.show();
    }
}
