package org.pixel.seven.recognizer.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Digit accentuation.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 23.02.2021 20:24 <p>
 */
public class DigitAccentuation<I extends BufferedImage> implements ImageProcessing<I> {

    /**
     * The constant DEFAULT_IMAGE_TYPE.
     */
    public static final int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_BYTE_BINARY;

    /**
     * Apply .
     *
     * @param image the image
     * @return the
     */
    @Override
    public I apply(I image) {
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                DEFAULT_IMAGE_TYPE
        );

        Graphics2D graphics = result.createGraphics();
        graphics.drawImage(image, 0, 0, Color.WHITE, null);
        graphics.dispose();

        return (I) result;
    }
}
