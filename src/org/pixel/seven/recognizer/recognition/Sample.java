package org.pixel.seven.recognizer.recognition;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The type Sample.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 08.03.2021 12:11 <p>
 */
public class Sample {

    /**
     * Of sample.
     *
     * @param sample        the sample
     * @param expectedValue the expected value
     * @return the sample
     */
    public static Sample of(BufferedImage sample, int expectedValue) {
        return new Sample(sample, expectedValue);
    }

    /**
     * The Value.
     */
    private int value;

    /**
     * The Sample.
     */
    private BufferedImage sample;

    /**
     * Instantiates a new Sample.
     *
     * @param sample the sample
     * @param value  the value
     */
    public Sample(BufferedImage sample, int value) {
        Objects.requireNonNull(sample, "The sample couldn't be null");
        this.sample = sample;

        if (value < 0 || value > 9)
            throw new IllegalArgumentException("The expected value couldn't be less than 0 or more than 9");
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Gets sample.
     *
     * @return the sample
     */
    public BufferedImage getSample() {
        return this.sample;
    }
}