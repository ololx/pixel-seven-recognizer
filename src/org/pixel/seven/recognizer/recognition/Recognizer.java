package org.pixel.seven.recognizer.recognition;

import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 05.03.2021 22:43
 * <p>
 * @author Alexander A. Kropotin
 */
public interface Recognizer<I> {

    void recognize(I input);
}
