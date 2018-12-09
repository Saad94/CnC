package com.friendos.gui.scenes;

import com.friendos.gui.GUIMain;
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

public class MainMenuScene extends AbstractScene<MainMenuScene> {

    /**
     * The OnClickHandler for the startNewGame button.
     */
    private EventHandler<MouseEvent> startNewGame = mouseEvent -> System.out.println("This should start the game.");

    /**
     * The OnClickHandler for the loadMission button.
     */
    private EventHandler<MouseEvent> loadMission = mouseEvent -> {
        System.out.println("This should load a mission.");
        GUIMain.setCurrentScene(GUIMain.pokemonScene);
    };

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
            new AbstractMap.SimpleEntry<>("Testing: do nothing", startNewGame),
            new AbstractMap.SimpleEntry<>("Testing: display pokemon", loadMission),
            new AbstractMap.SimpleEntry<>("Testing: do nothing", multiplayerGame),
            new AbstractMap.SimpleEntry<>("Exit", exit)
    );

    /**
     * The constructor for this class.
     */
    public MainMenuScene() {
        super(0, 0, 1024, 640);

        // Set the objects in this Scene.
        GridPane grid = createMenuGrid(stage);
        setScene(new Scene(grid, getDimensions().getWidth(), getDimensions().getHeight()));

        // Set the stylesheet for this Scene.
        getScene().getStylesheets().add(RESOURCE_LOADER.getStylesheetURL("main_menu.css").toExternalForm());

        // Set the music file for this Scene.
        setMusic("sounds/Born To Be A Winner.wav");
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
        createLabels(gridPane);

        return gridPane;
    }

    /**
     * @param gridPane the GridPane in which the Labels will live
     */
    private void createLabels(GridPane gridPane) {
        for (int i = 0; i < LABELS.size(); i++) {
            Label label = new Label(LABELS.get(i).getKey());
            label.setOnMouseClicked(LABELS.get(i).getValue());
            label.setPrefSize(getDimensions().getWidth()/3, getDimensions().getHeight()/20);
            gridPane.add(label, 0, i+1);
        }
    }
}
