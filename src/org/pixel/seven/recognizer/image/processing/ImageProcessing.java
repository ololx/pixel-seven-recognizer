package org.pixel.seven.recognizer.image.processing;

import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 22:51
 * <p>
 * @author Alexander A. Kropotin
 */
public interface ImageProcessing<I extends BufferedImage> {

    I apply(I image);
}
