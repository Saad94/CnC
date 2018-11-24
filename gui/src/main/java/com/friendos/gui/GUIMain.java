package com.friendos.gui;

import com.friendos.resources.ResourceLoader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class GUIMain extends Application {

    private final ResourceLoader RESOURCE_LOADER = ResourceLoader.getInstance();
    private final Rectangle2D DIMENSIONS = new Rectangle2D(0, 0, 1024, 640);

    /**
     * Defining an interface for onClickHandlers so we can simplify the act
     * of mapping Label texts with the actions to be performed when clicked.
     */
    private interface OnClickHandler {
        EventHandler<MouseEvent> onClick(Stage stage);
    }

    /**
     * The OnClickHandler for the startNewGame button.
     */
    private OnClickHandler startNewGame = stage -> mouseEvent -> System.out.println("This should start the game.");

    /**
     * The OnClickHandler for the loadMission button.
     */
    private OnClickHandler loadMission = stage -> mouseEvent -> System.out.println("This should load a mission.");

    /**
     * The OnClickHandler for the multiplayerGame button.
     */
    private OnClickHandler multiplayerGame = stage -> mouseEvent -> System.out.println("This should start a multiplayer game.");

    /**
     * The OnClickHandler for the exit button.
     */
    private OnClickHandler exit = stage -> mouseEvent -> stage.close();

    /**
     * A map between Label text and Label actions.
     */
    private final List<Map.Entry<String, OnClickHandler>> LABELS = Arrays.asList(
            new AbstractMap.SimpleEntry<>("Start New Game", startNewGame),
            new AbstractMap.SimpleEntry<>("Load Mission", loadMission),
            new AbstractMap.SimpleEntry<>("Multiplayer Game", multiplayerGame),
            new AbstractMap.SimpleEntry<>("Exit", exit)
    );

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, DIMENSIONS.getWidth(), DIMENSIONS.getHeight());
        scene.getStylesheets().add(RESOURCE_LOADER.getResourceURL("stylesheets/main_menu.css").toExternalForm());

        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(75, 25, 25, 25));

        for (int i = 0; i < LABELS.size(); i++) {
            Label label = new Label(LABELS.get(i).getKey());
            label.setOnMouseClicked(LABELS.get(i).getValue().onClick(primaryStage));
            label.setPrefSize(DIMENSIONS.getWidth()/3, DIMENSIONS.getHeight()/20);
            grid.add(label, 0, i+1);
        }

        primaryStage.setTitle("JavaFX on JDK 11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}