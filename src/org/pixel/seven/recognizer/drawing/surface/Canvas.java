package org.pixel.seven.recognizer.drawing.surface;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 26.02.2021 15:28
 * <p>
 * @author Alexander A. Kropotin
 */
public class Canvas implements DrawingSurface {

    private BufferedImage image;

    private int background;

    public Canvas(int width, int height) {
        this(width, height, BufferedImage.TYPE_INT_RGB, 0);
    }
    public Canvas(int width, int height, int backgroundColor) {
        this(width, height, BufferedImage.TYPE_INT_RGB, backgroundColor);
    }

    public Canvas(int width, int height, int imageType, int backgroundColor) {
        this(new  BufferedImage(width, height, imageType), backgroundColor);
    }

    public Canvas(BufferedImage image) {
        this(image, 0);
    }
    public Canvas(BufferedImage image, int backgroundColor) {
        Objects.requireNonNull(image, "The image couldn't be null");
        this.image = image;
        this.background = backgroundColor;
    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    public int getBackground() {
        return this.background;
    }
}
