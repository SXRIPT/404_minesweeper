package at.ac.fhcampuswien;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class GUIManager {
    private static int width;
    private static int height;
    private static int boardSize;
    private static int bombCount;
    private AnchorPane anchorPane;
    private Stage stage;
    private Scene scene;
    private static GUIManager instance;
    private HashMap<String, Image> graphics; //images will be saved in HashMap
    public static Label flagCount = new Label(); //public flagCount for access of the text



    /**
     * private constructor, only to be called by the getInstance method when it itself is called the first time
     * -> Singleton class - we don't want more than one GUIManager object to exist at any time.
     */
    private GUIManager() {
        //loading images
        graphics = new HashMap<>();
        try {
            loadImages();
        } catch (FileNotFoundException e) {
            System.out.println("One or more graphics failed to load!");
            e.printStackTrace();
        }
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane, width, height);
        stage = new Stage();
        stage.setScene(scene);
        //icon for Application
        stage.getIcons().add(new Image("file:src/main/resources/graphics/bomb.png"));
    }

    private void loadImages() throws FileNotFoundException { //loads all images
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
        stream = new FileInputStream("src/main/resources/graphics/won.png");
        graphics.put("won", new Image(stream));
        stream = new FileInputStream("src/main/resources/graphics/lost.png");
        graphics.put("lost", new Image(stream));
        for (int i = 1; i < 8; i++) {
            stream = new FileInputStream("src/main/resources/graphics/" + i + ".png");
            graphics.put(Integer.toString(i), new Image(stream));
        }
    }

    public static GUIManager getInstance(){ //if no GUIManager exists - one will be created
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void reset() { //reset for restart button
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane, width, height);
        stage = new Stage();
        stage.setScene(scene);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public Image getImage(String name) {
        if (graphics.containsKey(name)) {
            return graphics.get(name);
        }
        else {
            return graphics.get("x"); // if graphic is missing - red x will be displayed
        }
    }
    public static void startUp(){ //startup screen - choose size and difficulty
        Button startButton = new Button();
        startButton.setText("Start");

        Label sizeLabel = new Label();
        sizeLabel.setText("Size of gameboard:");

        Label difficultyLabel = new Label();
        difficultyLabel.setText("Difficulty:");

        ChoiceBox difficultyCB = new ChoiceBox(FXCollections.observableArrayList("easy", "normal", "hard"));
        difficultyCB.setValue("normal"); //default setting
        ChoiceBox sizeCB = new ChoiceBox(FXCollections.observableArrayList("small", "medium", "large"));
        sizeCB.setValue("medium"); //default setting

        startButton.setOnAction(eventButtonClick -> { //defines boardSize and bombCount

                if(sizeCB.getValue() == "small"){
                    setBoardSize(10);
                    setWidth(10*32);
                    setHeight(10*32+20);
                }
                else if(sizeCB.getValue() == "large"){
                    setBoardSize(23);
                    setWidth(23*32);
                    setHeight(23*32+20);
                }
                else{
                    setBoardSize(18);
                    setWidth(18*32);
                    setHeight(18*32+20);
                }

                if (sizeCB.getValue() == "small"){
                    if(difficultyCB.getValue() == "easy") setBombCount(12);
                    else if(difficultyCB.getValue() == "hard") setBombCount(20);
                    else setBombCount(15);
                } else if (sizeCB.getValue() == "large"){
                    if(difficultyCB.getValue() == "easy") setBombCount(66);
                    else if(difficultyCB.getValue() == "hard") setBombCount(105);
                    else setBombCount(79);
                }else{
                    if(difficultyCB.getValue() == "easy") setBombCount(40);
                    else if(difficultyCB.getValue() == "hard") setBombCount(65);
                    else setBombCount(49);
                }

            GUIManager.getInstance().getStage().close();
            GUIManager.getInstance().reset();
            App.newGame(boardSize, bombCount);
        });
        //position of buttons and labels
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setRightAnchor(startButton,20.0);
        AnchorPane.setLeftAnchor(startButton, 20.0);
        AnchorPane.setTopAnchor(startButton, 5.0);
        AnchorPane.setRightAnchor(difficultyCB,20.0);
        AnchorPane.setLeftAnchor(difficultyCB, 20.0);
        AnchorPane.setTopAnchor(difficultyCB, 65.0);
        AnchorPane.setRightAnchor(sizeCB,20.0);
        AnchorPane.setLeftAnchor(sizeCB, 20.0);
        AnchorPane.setTopAnchor(sizeCB, 125.0);
        AnchorPane.setTopAnchor(difficultyLabel,40.0);
        AnchorPane.setTopAnchor(sizeLabel,100.0);
        AnchorPane.setLeftAnchor(difficultyLabel, 20.0);
        AnchorPane.setLeftAnchor(sizeLabel,20.0);

        //adds buttons and labels to anchorPane
        anchorPane.getChildren().add(startButton);
        anchorPane.getChildren().add(sizeCB);
        anchorPane.getChildren().add(difficultyCB);
        anchorPane.getChildren().add(difficultyLabel);
        anchorPane.getChildren().add(sizeLabel);

        Scene scene = new Scene(anchorPane, 288, 288);
        GUIManager.getInstance().getStage().setScene(scene);
    }

    public static void setBoardSize(int size){
        boardSize = size;
    }
    public static void setBombCount(int count) {
        bombCount = count;
    }
    public static void plusLabel(){ //adds 1 to flagCount label
        int a = Integer.parseInt(flagCount.getText());
        a ++;
        flagCount.setText(String.valueOf(a));
    }
    public static void minusLabel(){ //subtracts 1 from flagCount label
        int a = Integer.parseInt(flagCount.getText());
        a --;
        flagCount.setText(String.valueOf(a));
    }

    public static void createLabel(){ //creates flagCount label

        Label flagCountText = new Label();
        flagCountText.setText("Number of remaining flags:");
        int initialBombs = App.getBombCount();
        flagCount.setText(String.valueOf(initialBombs));

        AnchorPane.setLeftAnchor(flagCount,210.0);

        GUIManager.getInstance().getAnchorPane().getChildren().add(flagCountText);
        GUIManager.getInstance().getAnchorPane().getChildren().add(flagCount);
    }
    public static  void setWidth(int a){
        width = a;
    } //sets stageWidth to boardWidth
    public static void setHeight(int a){
        height = a;
    }// sets stageHeight to boardHeight


}
