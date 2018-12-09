package com.friendos.gui.utils;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int numberOfFrames;
    private final int frameLoopIndex;
    private final int columns;
    private final int frameWidth;
    private final int frameHeight;
    private int lastIndex;
    private boolean firstLoop = true;

    public SpriteAnimation(ImageView imageView, Duration duration, int numberOfFrames, int frameLoopIndex, int columns, int frameWidth, int frameHeight) {
        this.imageView      = imageView;
        this.numberOfFrames = numberOfFrames;
        this.frameLoopIndex = frameLoopIndex;
        this.columns        = columns;
        this.frameWidth     = frameWidth;
        this.frameHeight    = frameHeight;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double k) {
        int index;

        if (firstLoop) {
            index = Math.min((int) Math.floor(k * numberOfFrames), numberOfFrames - 1);

            if (k == 1.0) {
                firstLoop = false;
            }
        } else {
            int leftOverFrames = numberOfFrames - frameLoopIndex;
            index = frameLoopIndex + (int)(k * leftOverFrames * numberOfFrames / leftOverFrames) % leftOverFrames;
        }

        if (index != lastIndex) {
            final int x = (index % columns) * frameWidth;
            final int y = (index / columns) * frameHeight;
            imageView.setViewport(new Rectangle2D(x, y, frameWidth, frameHeight));
            lastIndex = index;
        }
    }
}