package com.friendos.models;

import com.friendos.resources.ResourceLoader;
import com.google.common.base.MoreObjects;
import javafx.scene.image.ImageView;

public class Tile {

    private static final ResourceLoader RESOURCE_LOADER = ResourceLoader.getInstance();
    private int x;
    private int y;
    private TileType type;
    private ImageView imageView;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.imageView = new ImageView(RESOURCE_LOADER.getImageURL(type.getImage()).toExternalForm());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("x", x)
                .add("y", y)
                .add("type", type)
                .add("imageView", imageView)
                .toString();
    }
}
