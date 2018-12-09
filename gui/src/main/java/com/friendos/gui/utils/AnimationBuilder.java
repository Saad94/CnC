package com.friendos.gui.utils;

import com.friendos.resources.ResourceLoader;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * A helper class to simplify the process of creating Animations.
 */
public class AnimationBuilder {

    private static final ResourceLoader RESOURCE_LOADER = ResourceLoader.getInstance();

    /**
     * Creates and returns an {@link ImageView} object intended for use in an Animation.
     * Applies a Viewport to the ImageView to only display a part of it.
     * @param imagePath the path to the Image file
     * @param frameWidth the width of a frame in the Animation
     * @param frameHeight the height of a frame in the Animation
     * @return an ImageView with ViewPort applied
     */
    public static ImageView buildImageViewForAnimation(String imagePath, int frameWidth, int frameHeight) {
        ImageView imageView = new ImageView(RESOURCE_LOADER.getImageURL(imagePath).toExternalForm());
        imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        return imageView;
    }

    /**
     * Creates and returns a {@link SpriteAnimation} object using the given parameters.
     *
     * @param imageView the ImageView to animate
     * @param duration the time required for the animation to complete one loop
     * @param numberOfFrames the number of frames in the animation
     * @param frameLoopIndex generally 0, unless after the first time the animation runs you want it to only repeat
     *                       part of itself
     * @param columns the number of columns in the spritesheet
     * @param frameWidth the width of each sprite in the sheet
     * @param frameHeight the height of each sprite in the sheet
     * @return a {@link SpriteAnimation} object
     */
    public static Animation buildAnimation(ImageView imageView, int duration, int numberOfFrames, int frameLoopIndex,
                                                  int columns, int frameWidth, int frameHeight) {
        return new SpriteAnimation(
                imageView,
                Duration.millis(duration),
                numberOfFrames, frameLoopIndex,
                columns, frameWidth,
                frameHeight
        );
    }

    /**
     * Creates a SpriteAnimation as in {@link AnimationBuilder#buildAnimation(ImageView, int, int, int, int, int, int)}
     * Starts playing the animation indefinitely before returning it.
     */
    public static Animation buildAndPlayAnimation(ImageView imageView, int duration, int numberOfFrames, int frameLoopIndex,
                                           int columns, int frameWidth, int frameHeight) {
        Animation animation = buildAnimation(imageView, duration, numberOfFrames, frameLoopIndex, columns, frameWidth, frameHeight);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        return animation;
    }
}
