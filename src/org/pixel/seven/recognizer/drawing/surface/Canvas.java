package org.pixel.seven.recognizer.drawing.surface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Canvas.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 26.02.2021 15:28 <p>
 */
public class Canvas extends AbstractDrawingSurface {

    /**
     * Instantiates a new Canvas.
     *
     * @param width           the width
     * @param height          the height
     * @param backgroundColor the background color
     */
    public Canvas(int width, int height, Color backgroundColor) {
        this(width, height, BufferedImage.TYPE_INT_RGB, backgroundColor);
    }

    /**
     * Instantiates a new Canvas.
     *
     * @param width           the width
     * @param height          the height
     * @param imageType       the image type
     * @param backgroundColor the background color
     */
    public Canvas(int width, int height, int imageType, Color backgroundColor) {
        super(new BufferedImage(width, height, imageType), backgroundColor);
    }

    /**
     * Instantiates a new Canvas.
     *
     * @param image           the image
     * @param backgroundColor the background color
     */
    public Canvas(BufferedImage image, Color backgroundColor) {
        super(image, backgroundColor);
    }

    /**
     * Instantiates a new Canvas.
     *
     * @param image the image
     */
    public Canvas(BufferedImage image) {
        super(image);
    }
}
