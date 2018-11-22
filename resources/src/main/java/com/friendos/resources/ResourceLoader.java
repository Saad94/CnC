package com.friendos.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Singleton based Utility class for granting access to Game Resources.
 */
public class ResourceLoader {

    private static Logger LOGGER = Logger.getLogger(ResourceLoader.class.getName());
    private static ResourceLoader resourceLoader = null;
    private static String IMAGE_PATH = "images/";
    private static String AUDIO_PATH = "audio/";

    private ResourceLoader() {}

    public static ResourceLoader getInstance() {
        if (resourceLoader == null) {
            resourceLoader = new ResourceLoader();
        }

        return resourceLoader;
    }

    /**
     * @param resourcePath the path to the resource
     * @return the InputStream to the requested resource.
     */
    public InputStream getResource(String resourcePath) {
        try {
            return this.getClass().getModule().getResourceAsStream(resourcePath);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "The requested resource couldn't be found", e);
            return null;
        }
    }

    /**
     * @param imagePath the path to the image
     * @return the InputStream to the requested resource.
     */
    public InputStream getImage(String imagePath) {
        return getResource(IMAGE_PATH + imagePath);
    }

    /**
     * @param audioPath the path to the image
     * @return the InputStream to the requested resource.
     */
    public InputStream getAudio(String audioPath) {
        return getResource(AUDIO_PATH + audioPath);
    }
}
