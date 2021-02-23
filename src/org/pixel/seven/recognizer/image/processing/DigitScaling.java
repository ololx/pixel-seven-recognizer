package org.pixel.seven.recognizer.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 22:56
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitScaling<I extends BufferedImage> implements ImageProcessing<I> {

    public static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK.getRGB();

    private class Coordinates {

        int minX = Integer.MAX_VALUE;

        int maxX = 0;

        int minY = Integer.MAX_VALUE;

        int maxY = 0;
    }

    private int backgroundColor;

    public DigitScaling() {
        this(DEFAULT_BACKGROUND_COLOR);
    }

    public DigitScaling(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public I apply(I image) {
        return this.getSubImage(image);
    }

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

    private I getSubImage(I image) {
        Coordinates subImageCoordinates = this.getImageCoordinates(image);
        if (subImageCoordinates.minX == 0
                || subImageCoordinates.minY == 0
                || subImageCoordinates.maxX == image.getWidth()
                || subImageCoordinates.maxY == image.getHeight()) return image;

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
