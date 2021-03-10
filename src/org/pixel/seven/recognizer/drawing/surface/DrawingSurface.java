package org.pixel.seven.recognizer.drawing.surface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The interface Drawing surface.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 26.02.2021 15:24 <p>
 */
public interface DrawingSurface {

    /**
     * Gets image.
     *
     * @return the image
     */
    BufferedImage getImage();

    /**
     * Gets background.
     *
     * @return the background
     */
    Color getBackground();

    /**
     * Change background.
     *
     * @param backgroundColor the background color
     */
    void changeBackground(Color backgroundColor);

    /**
     * Clear.
     */
    void clear();

    /**
     * Gets x scaled.
     *
     * @param x     the x
     * @param width the width
     * @return the x scaled
     */
    default int getXScaled(int x, int width) {
        return (int) (x * getXScaling(width));
    }

    /**
     * Gets y scaled.
     *
     * @param y      the y
     * @param height the height
     * @return the y scaled
     */
    default int getYScaled(int y, int height) {
        return (int) (y * getYScaling(height));
    }

    /**
     * Gets x scaling.
     *
     * @param width the width
     * @return the x scaling
     */
    default double getXScaling(int width) {
        return 1d * this.getImage().getWidth() / width;
    }

    /**
     * Gets y scaling.
     *
     * @param height the height
     * @return the y scaling
     */
    default double getYScaling(int height) {
        return 1d * this.getImage().getHeight() / height;
    }
}
