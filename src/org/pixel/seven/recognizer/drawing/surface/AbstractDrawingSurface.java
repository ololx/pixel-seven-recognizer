package org.pixel.seven.recognizer.drawing.surface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The type Abstract drawing surface.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 10.03.2021 13:21 <p>
 */
public class AbstractDrawingSurface implements DrawingSurface {

    /**
     * The Image.
     */
    protected BufferedImage image;

    /**
     * The Background.
     */
    protected Color background;

    /**
     * Instantiates a new Abstract drawing surface.
     *
     * @param image           the image
     * @param backgroundColor the background color
     */
    public AbstractDrawingSurface(BufferedImage image, Color backgroundColor) {
        Objects.requireNonNull(image, "The image couldn't be null");
        this.image = image;

        Objects.requireNonNull(image, "The background color couldn't be null");
        this.background = backgroundColor;
    }

    /**
     * Instantiates a new Abstract drawing surface.
     *
     * @param image the image
     */
    public AbstractDrawingSurface(BufferedImage image) {
        Objects.requireNonNull(image, "The image couldn't be null");
        this.image = image;

        this.background = ((Graphics2D) image.getGraphics()).getBackground();
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    @Override
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Color getBackground() {
        return this.background;
    }

    /**
     * Change background.
     *
     * @param backgroundColor the background color
     */
    @Override
    public void changeBackground(Color backgroundColor) {
        for (int pixelX = 0; pixelX < this.image.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < this.image.getHeight(); pixelY++) {
                if (this.image.getRGB(pixelX, pixelY) != this.background.getRGB()) continue;

                this.image.setRGB(pixelX, pixelY, backgroundColor.getRGB());
            }
        }

        this.background = backgroundColor;
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        Graphics2D graphics = (Graphics2D) this.image.getGraphics();
        graphics.setColor(this.background);
        graphics.fillRect(
                0,
                0,
                this.image.getWidth(),
                this.image.getHeight()
        );
    }
}
