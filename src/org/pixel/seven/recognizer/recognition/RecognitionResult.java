package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.image.DigitBufferedImage;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * The type Recognition result.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 08.03.2021 18:06 <p>
 */
public class RecognitionResult {

    /**
     * The Result value.
     */
    private double resultValue;

    /**
     * The Origin sample.
     */
    private BufferedImage originSample;

    /**
     * The Processed sample.
     */
    private DigitBufferedImage processedSample;

    /**
     * Instantiates a new Recognition result.
     *
     * @param originSample    the origin sample
     * @param processedSample the processed sample
     * @param resultValue     the result value
     */
    public RecognitionResult(BufferedImage originSample, DigitBufferedImage processedSample, double resultValue) {
        Objects.requireNonNull(originSample, "The origin sample couldn't be null");
        Objects.requireNonNull(processedSample, "The processed sample couldn't be null");

        this.originSample = originSample;
        this.processedSample = processedSample;
        this.resultValue = resultValue;
    }

    /**
     * Gets result value.
     *
     * @return the result value
     */
    public double getResultValue() {
        return this.resultValue;
    }

    /**
     * Gets origin sample.
     *
     * @return the origin sample
     */
    public BufferedImage getOriginSample() {
        return this.originSample;
    }

    /**
     * Gets processed sample.
     *
     * @return the processed sample
     */
    public DigitBufferedImage getProcessedSample() {
        return this.processedSample;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public boolean getResult() {
        return this.resultValue == 1 ? true : false;
    }
}
