package at.ac.fhcampuswien;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class GUIManager {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private AnchorPane anchorPane;
    private Stage stage;
    private Scene scene;
    private static GUIManager instance;
    private HashMap<String, Image> graphics;

    /**
     * private constructor, only to be called by the getInstance method when it itself is called the first time
     * -> Singleton class - we don't want mor than one GUIManager object to exist at any time.
     */
    private GUIManager() {
        graphics = new HashMap<>();
        try {
            loadImages();
        } catch (FileNotFoundException e) {
            System.out.println("One or more graphics failed to load!");
            e.printStackTrace();
        }
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(scene);
    }

    private void loadImages() throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/graphics/unrevealed.png");
        graphics.put("unrevealed", new Image(stream));
        stream = new FileInputStream("src/main/resources/graphics/empty.png");
        graphics.put("empty", new Image(stream));
        stream = new FileInputStream("src/main/resources/graphics/bomb.png");
        graphics.put("bomb", new Image(stream));
        stream = new FileInputStream("src/main/resources/graphics/flag.png");
        graphics.put("flag", new Image(stream));
        stream = new FileInputStream("src/main/resources/graphics/x.png");
        graphics.put("x", new Image(stream));
        for (int i = 1; i < 8; i++) {
            stream = new FileInputStream("src/main/resources/graphics/" + i + ".png");
            graphics.put(Integer.toString(i), new Image(stream));
        }
    }

    public static GUIManager getInstance(){
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public Image getImage(String name) {
        if (graphics.containsKey(name)) {
            return graphics.get(name);
        }
        else {
            return graphics.get("x");
        }
    }
}
