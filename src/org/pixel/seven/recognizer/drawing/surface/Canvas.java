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

    public Canvas(int width, int height) {
        this(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public Canvas(int width, int height, int imageType) {
        this(new  BufferedImage(width, height, imageType));
    }

    public Canvas(BufferedImage image) {
        Objects.requireNonNull(image, "The image couldn't be null");
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return this.image;
    }
}
