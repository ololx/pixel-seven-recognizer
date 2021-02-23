package org.pixel.seven.recognizer.image;

import org.pixel.seven.recognizer.image.processing.ImageProcessing;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 21:53
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitBufferedImage<I extends BufferedImage> implements ProcessedImage<I> {

    private I image;

    public DigitBufferedImage(I image) {
        Objects.requireNonNull(image);
        this.image = image;
    }

    @Override
    public DigitBufferedImage process(ImageProcessing... presets) {
        for (ImageProcessing preset : presets) {
            this.image = (I) preset.apply(this.image);
        }
        return this;
    }

    @Override
    public int[] getPixels() {
        int[] pixels = new int[this.image.getWidth() * this.image.getHeight()];
        return this.image.getRGB(
                0,
                0,
                this.image.getWidth(),
                this.image.getHeight(),
                new int[this.image.getWidth() * this.image.getHeight()],
                0,
                this.image.getWidth()
        );
    }

    @Override
    public I getImage() {
        return this.image;
    }
}
