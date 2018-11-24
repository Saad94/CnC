package com.friendos.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Singleton based Utility class for granting access to Game Resources.
 */
public class ResourceLoader {

    private static final Logger LOGGER = Logger.getLogger(ResourceLoader.class.getName());
    private static final String IMAGE_PATH = "images/";
    private static final String AUDIO_PATH = "audio/";
    private static ResourceLoader resourceLoader = null;

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
    public InputStream getResourceStream(String resourcePath) {
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath);

        if (resourceStream == null) {
            throw new ResourceNotFoundException(String.format("The Resource at <%s> couldn't be found.", resourcePath));
        }

        return resourceStream;
    }

    /**
     * @param imagePath the path to the image
     * @return the InputStream to the requested resource.
     */
    public InputStream getImageStream(String imagePath) {
        return getResourceStream(IMAGE_PATH + imagePath);
    }

    /**
     * @param audioPath the path to the image
     * @return the InputStream to the requested resource.
     */
    public InputStream getAudioStream(String audioPath) {
        return getResourceStream(AUDIO_PATH + audioPath);
    }

    /**
     * @param resourcePath the path to the resource
     * @return the URL to the requested resource.
     */
    public URL getResourceURL(String resourcePath) {
        URL resource = this.getClass().getClassLoader().getResource(resourcePath);

        if (resource == null) {
            throw new ResourceNotFoundException(String.format("The Resource at <%s> couldn't be found.", resourcePath));
        }

        return resource;
    }

    /**
     * @param imagePath the path to the image
     * @return the URL to the requested resource.
     */
    public URL getImageURL(String imagePath) {
        return getResourceURL(IMAGE_PATH + imagePath);
    }

    /**
     * @param audioPath the path to the image
     * @return the URL to the requested resource.
     */
    public URL getAudioURL(String audioPath) {
        return getResourceURL(AUDIO_PATH + audioPath);
    }
}
