package org.pixel.seven.recognizer.image;

import org.pixel.seven.recognizer.image.processing.ImageProcessing;

import java.awt.*;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 22:05
 * <p>
 * @author Alexander A. Kropotin
 */
public interface ProcessedImage<I extends Image> {

    ProcessedImage process(ImageProcessing... presets);

    int[] getPixels();

    I getImage();
}
