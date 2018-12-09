package com.friendos.gui;

import com.friendos.gui.scenes.AbstractScene;
import com.friendos.gui.scenes.MainMenuScene;
import com.friendos.gui.scenes.TestPokemonScene;
import com.friendos.gui.scenes.TreeTileScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GUIMain extends Application {

    public static Stage stage;
    public static MainMenuScene mainMenuScene;
    public static TestPokemonScene pokemonScene;
    public static TreeTileScene treeScene;

    private static AbstractScene currentScene;
    private static MediaPlayer mediaPlayer;

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Setup the Stage
        setupStage(primaryStage);

        // Create all the Scenes
        mainMenuScene = new MainMenuScene();
        pokemonScene = new TestPokemonScene();
        treeScene = new TreeTileScene();

        // Set the current Scene
        setCurrentScene(treeScene);

        stage.setTitle("JavaFX on JDK 11");
        stage.show();
    }

    private void setupStage(Stage primaryStage) {

        // Store the Stage so we can access it later.
        stage = primaryStage;

        // If the game window is closed, terminate the application.
        stage.setOnCloseRequest(event -> Platform.exit());
    }

    /**
     * Changes the current running Scene.
     * @param abstractScene the new Scene to be displayed.
     */
    public static void setCurrentScene(AbstractScene abstractScene) {
        currentScene = abstractScene;

        // Sets the music for the new currentScene to play.
        changeMediaPlayer();

        // Displays the new currentScene on the Stage.
        stage.setScene(currentScene.getScene());
    }

    /**
     * Setup the MediaPlayer to stop playing whatever it already
     * is and instead play the music from the currentScene.
     */
    private static void changeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (currentScene.getMusic() != null) {
            mediaPlayer = new MediaPlayer(new Media(currentScene.getMusic().toString()));
            mediaPlayer.setVolume(0.8);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }
}