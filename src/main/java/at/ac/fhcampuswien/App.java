package at.ac.fhcampuswien;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.util.ArrayList;

public class App /*extends Application*/{

    private ArrayList<Tile> Tileboard = new ArrayList<Tile>();

    public static void main(String[] args){
        Board board = new Board();
        board.showBoard();
        //launch(args);
    }




    public void fillTileBoard(Board btt){

            for(int i = 0; i < btt.getMAX();i++){
                for(int j = 0; j < btt.getMAX();j++){
                    Tileboard.add(new Tile(i,j,btt.isBomb(i,j)));

                }
            }

    }

    /*  @Override
        public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Hello JavaFX!");
        btn.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.show();
    }*/
}
