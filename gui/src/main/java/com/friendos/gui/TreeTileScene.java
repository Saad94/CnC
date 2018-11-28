package com.friendos.gui;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        //===============================
//        Rectangle rect = new Rectangle (100, 40, 100, 100);
//        rect.setArcHeight(50);
//        rect.setArcWidth(50);
//        rect.setFill(Color.VIOLET);
//
//        final Duration SEC_2 = Duration.millis(2000);
//        final Duration SEC_3 = Duration.millis(3000);
//
//        PauseTransition pt = new PauseTransition(Duration.millis(1000));
//        FadeTransition ft = new FadeTransition(SEC_3);
//        ft.setFromValue(1.0f);
//        ft.setToValue(0.3f);
//        ft.setCycleCount(2);
//        ft.setAutoReverse(true);
//        TranslateTransition tt = new TranslateTransition(SEC_2);
//        tt.setFromX(-100f);
//        tt.setToX(100f);
//        tt.setCycleCount(2);
//        tt.setAutoReverse(true);
//        RotateTransition rt = new RotateTransition(SEC_3);
//        rt.setByAngle(180f);
//        rt.setCycleCount(4);
//        rt.setAutoReverse(true);
//        ScaleTransition st = new ScaleTransition(SEC_2);
//        st.setByX(1.5f);
//        st.setByY(1.5f);
//        st.setCycleCount(2);
//        st.setAutoReverse(true);

        ImageView[] slides = new ImageView[4];

        Image image1 = new Image("../../../../resources/src/main/resources/images/main_menu/split20000.png");
        Image image2 = new Image("../../../../resources/src/main/resources/images/main_menu/split20001.png");
        Image image3 = new Image("../../../../resources/src/main/resources/images/main_menu/split20002.png");
        Image image4 = new Image("../../../../resources/src/main/resources/images/main_menu/split20003.png");

        slides[0] = new ImageView(image1);
        slides[1] = new ImageView(image2);
        slides[2] = new ImageView(image3);
        slides[3] = new ImageView(image4);


        HBox root = new HBox();

        root.setAlignment(Pos.TOP_CENTER);
        SequentialTransition seqT = new SequentialTransition ();
        for (ImageView slide : slides) {

            SequentialTransition sequentialTransition = new SequentialTransition();

            FadeTransition fadeIn = getFadeTransition(slide, 0.0, 1.0, 2000);
            PauseTransition stayOn = new PauseTransition(Duration.millis(2000));
            FadeTransition fadeOut = getFadeTransition(slide, 1.0, 0.0, 2000);

            sequentialTransition.getChildren().addAll(fadeIn, stayOn, fadeOut);
            slide.setOpacity(0);
            root.getChildren().add(slide);
            seqT.getChildren().add(sequentialTransition);

        }
        seqT.play();



        root.setPadding(new Insets(75, 25, 25, 25));
        root.getChildren().addAll(grid);
        setScene(new Scene(root, getDimensions().getWidth(), getDimensions().getHeight()));

        //===============================

        // Set the stylesheet for this Scene.
        getScene().getStylesheets().add(RESOURCE_LOADER.getStylesheetURL("Tiletest.css").toExternalForm());

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

    public FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

        FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
        ft.setFromValue(fromValue);
        ft.setToValue(toValue);

        return ft;

    }


}
