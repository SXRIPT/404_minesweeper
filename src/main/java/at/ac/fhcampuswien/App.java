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
        //Setting the Scene object


        /*
        for (int i = 0; i < 20; i++) {
            ImageView iv = new ImageView(GUIManager.getInstance().getImage("unrevealed"));
            iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    ((ImageView) event.getTarget()).setImage(GUIManager.getInstance().getImage("empty"));

                    event.consume();
                }
            });
            iv.setX((i % 16) *32);
            iv.setY( (i/16) * 32);
            GUIManager.getInstance().getAnchorPane().getChildren().add(iv);
        }

         */

        /*
        Group root = new Group(tile);
        Scene scene = new Scene(root, 595, 370);
        primaryStage.setTitle("Displaying Image");
        primaryStage.setScene(GUIManager.getInstance().getStage().getScene());

         */
        primaryStage.show();
    }
}
