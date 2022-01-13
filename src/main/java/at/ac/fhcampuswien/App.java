package at.ac.fhcampuswien;

public class App /*extends Application*/ {

    public static void main(String[] args){
        Game game = new Game(16, 40);
        game.start();
        //launch(args);
    }
}
