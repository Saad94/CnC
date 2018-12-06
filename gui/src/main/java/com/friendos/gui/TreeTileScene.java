package com.friendos.gui;

import com.friendos.resources.ResourceLoader;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.util.*;

import static com.friendos.gui.GUIMain.stage;

class TreeTileScene extends AbstractScene<TreeTileScene> {

    /**
     * The OnClickHandler for the startNewGame button.
     */
    private EventHandler<MouseEvent> startNewGame = mouseEvent -> {
        System.out.println("This should start the game.");
        GUIMain.setCurrentScene(GUIMain.mainMenuScene);
    };

    /**
     * The OnClickHandler for the loadMission button.
     */
    private EventHandler<MouseEvent> loadMission = mouseEvent -> System.out.println("This should load a mission.");

    /**
     * The OnClickHandler for the multiplayerGame button.
     */
    private EventHandler<MouseEvent> multiplayerGame = mouseEvent -> System.out.println("This should start a multiplayer game.");

    /**
     * The OnClickHandler for the exit button.
     */
    private EventHandler<MouseEvent> exit = mouseEvent -> stage.close();

    /**
     * A map between Label text and Label actions.
     */
    private final List<Map.Entry<String, EventHandler<MouseEvent>>> LABELS = Arrays.asList(
            new AbstractMap.SimpleEntry<>("Testing: display CnC", startNewGame),
            new AbstractMap.SimpleEntry<>("Testing: do nothing", loadMission),
            new AbstractMap.SimpleEntry<>("Testing: do nothing", multiplayerGame),
            new AbstractMap.SimpleEntry<>("Exit", exit)
    );

    /**
     * The constructor for this class.
     */
    TreeTileScene() {
        super(0, 0, 1024, 640);

        // Set the objects in this Scene.
        GridPane grid = createMenuGrid(stage);

        //Add all png images
        ArrayList<Image> temperateTileSetImages = addImages();

        //Assign ImageViews
        ArrayList<ImageView> temperateTileSetImageViews = assignImageViews(temperateTileSetImages);


        VBox vbox = new VBox();

        //Include all image views in Hbox
        HBox box = new HBox();
        box.setPadding(new Insets(20,20,20,200));
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().addAll(temperateTileSetImageViews);
        box.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        box.setStyle("-fx-background-color: #000000;");

//        grid.setStyle("-fx-background-color: #000000;");




        vbox.getChildren().addAll(grid,box);
        setScene(new Scene(vbox, getDimensions().getWidth(), getDimensions().getHeight()));

        //===============================

        // Set the stylesheet for this Scene.
        getScene().getStylesheets().add(RESOURCE_LOADER.getStylesheetURL("Tiletest.css").toExternalForm());

        // Set the music file for this Scene.
        setMusic("sounds/Act On Instinct.mp3");

    }

    /**
     * @param stage the stage on which this Scene will be displayed.
     * @return the grid representing the Menu Labels
     */
    private GridPane createMenuGrid(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(75, 25, 25, 25));
        createLabels(stage, gridPane);

        return gridPane;
    }

    /**
     * @param stage the stage on which this Scene will be displayed.
     * @param gridPane the GridPane in which the Labels will live
     */
    private void createLabels(Stage stage, GridPane gridPane) {
        for (int i = 0; i < LABELS.size(); i++) {
            Label label = new Label(LABELS.get(i).getKey());
            label.setOnMouseClicked(LABELS.get(i).getValue());
            label.setPrefSize(getDimensions().getWidth()/3, getDimensions().getHeight()/20);
            gridPane.add(label, 0, i+1);
        }
    }


    private ArrayList<Image> addImages(){

        //Doesn't recognize as directory for some reason
//        String directoryString = RESOURCE_LOADER.getImageURL("main_menu/Temperate tileset").toExternalForm();
//        File directory2 = new File(directoryString);
//        System.out.println(directory2.isDirectory());



        File directory= new File("C:\\Users\\Muhannad\\Desktop\\Personal Coding Projects\\CnC Project [With Saad]\\CnC\\resources\\src\\main\\resources\\images\\main_menu\\Temperate tileset");
        System.out.println(directory.isDirectory());


        //Extract only png's from the folder
        File [] pngFiles = directory.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && file.getName().toLowerCase().endsWith(".png");
            }
        });

        //Convert png's into Images and store in an array
        ArrayList<Image> pngImages = new ArrayList<>();
        for (File f: pngFiles){
            Image image = new Image(f.toURI().toString());
            pngImages.add(image);
        }

        System.out.println(pngFiles.length);
        System.out.println(pngImages.size() + " are the png images!");
        int fileCount= directory.list().length;
        System.out.println("File Count:"+fileCount);

        return pngImages;

    }

    private ArrayList<ImageView> assignImageViews(ArrayList<Image> images){

        ArrayList<ImageView> imageViews = new ArrayList<>();

        for (Image i: images){
            ImageView imageView = new ImageView(i);
            imageViews.add(imageView);
        }
        System.out.println(imageViews.size() + " image views assigned!");

        return imageViews;

    }


}
