package org.pixel.seven.recognizer.image;

import org.pixel.seven.recognizer.image.processing.ImageProcessing;

import java.awt.*;

/**
 * The interface Processed image.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 21.02.2021 22:05 <p>
 */
public interface ProcessedImage<I extends Image> {

    /**
     * Process processed image.
     *
     * @param presets the presets
     * @return the processed image
     */
    ProcessedImage process(ImageProcessing... presets);

    /**
     * Get pixels int [ ].
     *
     * @return the int [ ]
     */
    int[] getPixels();

    /**
     * Gets image.
     *
     * @return the image
     */
    I getImage();
}
