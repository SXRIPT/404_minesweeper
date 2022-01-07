package at.ac.fhcampuswien;

import java.util.ArrayList;

public class App /*extends Application*/{

    private ArrayList<Tile> Tileboard = new ArrayList<Tile>();

    public static void main(String[] args){
        Board board = new Board();
        //board.printIntBoard();
        //System.out.println();
        board.printTileBoard();
        //launch(args);
    }


    //TODO habe die entsprechende Funktionalität in die Board-Klasse ausgelagert - Florian
    /*
    public void fillTileBoard(Board btt){

            for(int i = 0; i < btt.getMAX();i++){
                for(int j = 0; j < btt.getMAX();j++){
                    Tileboard.add(new Tile(i,j,btt.isBomb(i,j)));

                }
            }

    }
     */

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
