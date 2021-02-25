package org.pixel.seven.recognizer.image.processing;

import java.awt.image.BufferedImage;

/**
 * The interface Image processing.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 21.02.2021 22:51 <p>
 */
public interface ImageProcessing<I extends BufferedImage> {

    /**
     * Apply .
     *
     * @param image the image
     * @return the
     */
    I apply(I image);
}
