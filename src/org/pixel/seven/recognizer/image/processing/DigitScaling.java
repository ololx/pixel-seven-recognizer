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
        private int x1;

        /**
         * The Max x.
         */
        private int x2;

        /**
         * The Min y.
         */
        private int y1;

        /**
         * The Max y.
         */
        private int y2;

        /**
         * Instantiates a new Coordinates.
         *
         * @param width  the width
         * @param height the height
         */
        public Coordinates(int width, int height) {
            this(0, 0, width, height);
        }

        /**
         * Instantiates a new Coordinates.
         *
         * @param x      the x
         * @param y      the y
         * @param width  the width
         * @param height the height
         */
        public Coordinates(int x, int y, int width, int height) {
            this.x1 = width;
            this.y1 = height;
            this.x2 = x;
            this.y2 = y;
        }

        /**
         * Gets x 1.
         *
         * @return the x 1
         */
        public int getX1() {
            return this.x1;
        }

        /**
         * Sets x 1.
         *
         * @param value the value
         */
        public void setX1(int value) {
            this.x1 = value;
        }

        /**
         * Gets x 2.
         *
         * @return the x 2
         */
        public int getX2() {
            return this.x2;
        }

        /**
         * Sets x 2.
         *
         * @param value the value
         */
        public void setX2(int value) {
            this.x2 = value;
        }

        /**
         * Gets y 1.
         *
         * @return the y 1
         */
        public int getY1() {
            return this.y1;
        }

        /**
         * Sets y 1.
         *
         * @param value the value
         */
        public void setY1(int value) {
            this.y1 = value;
        }

        /**
         * Gets y 2.
         *
         * @return the y 2
         */
        public int getY2() {
            return this.y2;
        }

        /**
         * Sets y 2.
         *
         * @param value the value
         */
        public void setY2(int value) {
            this.y2 = value;
        }

        /**
         * Gets min x.
         *
         * @return the min x
         */
        public int getMinX() {
            return Math.min(this.x1, this.x2);
        }

        /**
         * Gets max x.
         *
         * @return the max x
         */
        public int getMaxX() {
            return Math.max(this.x1, this.x2);
        }

        /**
         * Gets min y.
         *
         * @return the min y
         */
        public int getMinY() {
            return Math.min(this.y1, this.y2);
        }

        /**
         * Gets max y.
         *
         * @return the max y
         */
        public int getMaxY() {
            return Math.max(this.y1, this.y2);
        }

        /**
         * Gets width.
         *
         * @return the width
         */
        public int getWidth() {
            int width = this.getMaxX() - this.getMinX();
            return width == 0 ? 1 : width;
        }

        /**
         * Gets height.
         *
         * @return the height
         */
        public int getHeight() {
            int height = this.getMaxY() - this.getMinY();
            return height == 0 ? 1 : height;
        }

        /**
         * To string string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return String.format("x1 = %d, y1 = %d, x2 = %d, y2 = %d", x1, y1, x2, y2);
        }
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
        Coordinates coordinates = new Coordinates(image.getWidth(), image.getHeight());
        for (int currentX = 0; currentX < image.getWidth(); currentX++) {
            for (int currentY = 0; currentY < image.getHeight(); currentY++) {
                if (image.getRGB(currentX, currentY) == backgroundColor) continue;

                if (coordinates.getX1() > currentX) coordinates.setX1(currentX);
                if (coordinates.getX2() < currentX) coordinates.setX2(currentX);
                if (coordinates.getY1() > currentY) coordinates.setY1(currentY);
                if (coordinates.getY2() < currentY) coordinates.setY2(currentY);
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
        Coordinates imageCoords = this.getImageCoordinates(image);
        if (imageCoords.getWidth() == image.getWidth() && imageCoords.getHeight() == image.getHeight()) return image;

        BufferedImage subImage = image.getSubimage(
                imageCoords.getMinX(),
                imageCoords.getMinY(),
                imageCoords.getWidth(),
                imageCoords.getHeight()
        );

        BufferedImage resized = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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
