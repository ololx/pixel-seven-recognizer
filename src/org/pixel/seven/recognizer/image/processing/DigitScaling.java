package org.pixel.seven.recognizer.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Digit scaling.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 21.02.2021 22:56 <p>
 */
public class DigitScaling<I extends BufferedImage> implements ImageProcessing<I> {

    /**
     * The constant DEFAULT_BACKGROUND_COLOR.
     */
    public static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK.getRGB();

    /**
     * The type Coordinates.
     */
    private class Coordinates {

        /**
         * The Min x.
         */
        int minX = Integer.MAX_VALUE;

        /**
         * The Max x.
         */
        int maxX = 0;

        /**
         * The Min y.
         */
        int minY = Integer.MAX_VALUE;

        /**
         * The Max y.
         */
        int maxY = 0;
    }

    /**
     * The Background color.
     */
    private int backgroundColor;

    /**
     * Instantiates a new Digit scaling.
     */
    public DigitScaling() {
        this(DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * Instantiates a new Digit scaling.
     *
     * @param backgroundColor the background color
     */
    public DigitScaling(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Apply .
     *
     * @param image the image
     * @return the
     */
    @Override
    public I apply(I image) {
        return this.getSubImage(image);
    }

    /**
     * Gets image coordinates.
     *
     * @param image the image
     * @return the image coordinates
     */
    private Coordinates getImageCoordinates(I image) {
        Coordinates coordinates = new Coordinates();
        for (int currentX = 0; currentX < image.getWidth(); currentX++) {
            for (int currentY = 0; currentY < image.getHeight(); currentY++) {
                if (image.getRGB(currentX, currentY) == backgroundColor) continue;

                if (coordinates.minX > currentX) coordinates.minX = currentX;
                if (coordinates.maxX < currentX) coordinates.maxX = currentX;
                if (coordinates.minY > currentY) coordinates.minY = currentY;
                if (coordinates.maxY < currentY) coordinates.maxY = currentY;
            }
        }

        return coordinates;
    }

    /**
     * Gets sub image.
     *
     * @param image the image
     * @return the sub image
     */
    private I getSubImage(I image) {
        Coordinates subImageCoordinates = this.getImageCoordinates(image);
        if (subImageCoordinates.minX == 0
                && subImageCoordinates.minY == 0
                && subImageCoordinates.maxX == image.getWidth()
                && subImageCoordinates.maxY == image.getHeight()) return image;

        BufferedImage subImage = image.getSubimage(
                subImageCoordinates.minX,
                subImageCoordinates.minY,
                subImageCoordinates.maxX - subImageCoordinates.minX,
                subImageCoordinates.maxY - subImageCoordinates.minY
        );

        BufferedImage resized = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //produces a balanced resizing (fast and decent quality)
        g.drawImage(
                subImage,
                0, 0, resized.getWidth(), resized.getHeight(),
                0, 0, subImage.getWidth(), subImage.getHeight(),
                null
        );
        g.dispose();

        return (I) resized;
    }
}
