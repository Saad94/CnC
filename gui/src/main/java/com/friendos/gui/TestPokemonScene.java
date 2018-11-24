package com.friendos.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.friendos.gui.GUIMain.stage;

class TestPokemonScene extends AbstractScene<TestPokemonScene> {

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
    TestPokemonScene() {
        super(0, 0, 1024, 640);

        // Set the objects in this Scene.
        GridPane grid = createMenuGrid(stage);
        setScene(new Scene(grid, getDimensions().getWidth(), getDimensions().getHeight()));

        // Set the stylesheet for this Scene.
        getScene().getStylesheets().add(RESOURCE_LOADER.getStylesheetURL("test_pokemon.css").toExternalForm());

        // Set the music file for this Scene.
        setMusic("sounds/Unbeatable.wav");
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
}
