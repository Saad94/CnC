package com.friendos.gui;

import com.friendos.resources.ResourceLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GUIMain extends Application {

    private final ResourceLoader RESOURCE_LOADER = ResourceLoader.getInstance();

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Image image = new Image(RESOURCE_LOADER.getImage("main_menu/main_menu_background.png"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 1024, 640);

        primaryStage.setTitle("JavaFX on JDK 11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}