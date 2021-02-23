package org.pixel.seven.recognizer.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 23.02.2021 20:24
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitAccentuation<I extends BufferedImage> implements ImageProcessing<I> {

    public static final int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_BYTE_BINARY;

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
