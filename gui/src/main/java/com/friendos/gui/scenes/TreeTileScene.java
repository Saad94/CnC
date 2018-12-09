package com.friendos.gui.scenes;

import com.friendos.gui.utils.AnimationBuilder;
import com.friendos.gui.utils.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class TreeTileScene extends AbstractScene<TreeTileScene> {

    /**
     * The constructor for this class.
     */
    public TreeTileScene() {
        super(0, 0, 1024, 640);

//        //Add all png images
//        ArrayList<Image> temperateTileSetImages = addImages();

//        //Assign ImageViews
//        ArrayList<ImageView> temperateTileSetImageViews = assignImageViews(temperateTileSetImages);

//
//        VBox vbox = new VBox();
//
//        //Include all image views in Hbox
//        HBox box = new HBox();
//        box.setPadding(new Insets(20,20,20,200));
//        box.setAlignment(Pos.BOTTOM_CENTER);
////        box.getChildren().addAll(temperateTileSetImageViews);
//        box.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
//        box.setStyle("-fx-background-color: #000000;");

//        grid.setStyle("-fx-background-color: #000000;");

        ImageView imageView = AnimationBuilder.buildImageViewForAnimation("test.png", 48, 48);
        ImageView imageView2 = AnimationBuilder.buildImageViewForAnimation("test2.png", 48, 48);
        imageView2.setX(48);
        Group group = new Group();
        group.getChildren().addAll(imageView, imageView2);

        AnimationBuilder.buildAndPlayAnimation(imageView, 8000, 55, 40, 5, 48, 48);
        AnimationBuilder.buildAndPlayAnimation(imageView2, 8000, 55, 40, 5, 48, 48);

        setScene(new Scene(group, getDimensions().getWidth(), getDimensions().getHeight()));

        //===============================

        // Set the stylesheet for this Scene.
        getScene().getStylesheets().add(RESOURCE_LOADER.getStylesheetURL("Tiletest.css").toExternalForm());

        // Set the music file for this Scene.
        setMusic("sounds/Act On Instinct.mp3");
    }

    private ArrayList<Image> addImages() {

        File directory = RESOURCE_LOADER.getImageDirectory("main_menu/Temperate tileset");

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
