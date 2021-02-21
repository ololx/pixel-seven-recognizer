package org.pixel.seven.recognizer.image.processor;

import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 22:51
 * <p>
 * @author Alexander A. Kropotin
 */
public interface ImageProcessor<I extends BufferedImage> {

    I apply(I image);
}
