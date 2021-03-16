package org.pixel.seven.recognizer.image;

import org.pixel.seven.recognizer.image.processing.ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The type Digit buffered image.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 21.02.2021 21:53 <p>
 */
public class DigitBufferedImage<I extends BufferedImage> implements ProcessedImage<I> {

    /**
     * The Image.
     */
    private I image;

    /**
     * Instantiates a new Digit buffered image.
     *
     * @param image the image
     */
    public DigitBufferedImage(I image) {
        Objects.requireNonNull(image);
        this.image = image;
    }

    /**
     * Process digit buffered image.
     *
     * @param presets the presets
     * @return the digit buffered image
     */
    @Override
    public DigitBufferedImage process(ImageProcessing... presets) {
        for (ImageProcessing preset : presets) {
            this.image = (I) preset.apply(this.image);
        }

        return this;
    }

    /**
     * Get pixels int [ ].
     *
     * @return the int [ ]
     */
    @Override
    public int[] getPixels() {
        int[] pixels = this.image.getRGB(
                0,
                0,
                this.image.getWidth(),
                this.image.getHeight(),
                new int[this.image.getWidth() * this.image.getHeight()],
                0,
                this.image.getWidth()
        );

        for (int pixel = 0; pixel < pixels.length; pixel++) {
            pixels[pixel] = pixels[pixel] == Color.BLACK.getRGB() ? 0 : 1;
        }

        return pixels;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    @Override
    public I getImage() {
        return this.image;
    }
}
