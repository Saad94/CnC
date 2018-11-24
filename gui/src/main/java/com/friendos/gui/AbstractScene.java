package com.friendos.gui;

import com.friendos.resources.ResourceLoader;
import com.google.common.base.MoreObjects;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

import java.net.URL;

abstract class AbstractScene<T> {

    final ResourceLoader RESOURCE_LOADER = ResourceLoader.getInstance();
    private final Rectangle2D dimensions;
    private URL music;
    private Scene scene;

    AbstractScene(int x, int y, int w, int h) {
        dimensions = new Rectangle2D(x, y, w, h);
    }

    /**
     * Getter method in case the dimensions of the Scene are required by some other class.
     * @return the dimensions of the Scene.
     */
    public Rectangle2D getDimensions() {
        return dimensions;
    }

    public URL getMusic() {
        return music;
    }

    public void setMusic(URL music) {
        this.music = music;
    }

    public void setMusic(String music) {
        this.music = RESOURCE_LOADER.getAudioURL(music);
    }

    public T music(URL music) {
        setMusic(music);
        return (T) this;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public T scene(Scene scene) {
        setScene(scene);
        return (T) this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("RESOURCE_LOADER", RESOURCE_LOADER)
                .add("dimensions", dimensions)
                .add("music", music)
                .add("scene", scene)
                .toString();
    }
}
