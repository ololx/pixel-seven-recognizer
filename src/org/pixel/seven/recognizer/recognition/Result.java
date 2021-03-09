package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.image.DigitBufferedImage;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 08.03.2021 18:06
 * <p>
 * @author Alexander A. Kropotin
 */
public class Result {

    private double resultValue;

    private BufferedImage originSample;

    private DigitBufferedImage processedSample;

    public Result(BufferedImage originSample, DigitBufferedImage processedSample, double resultValue) {
        Objects.requireNonNull(originSample, "The origin sample couldn't be null");
        Objects.requireNonNull(processedSample, "The processed sample couldn't be null");

        this.originSample = originSample;
        this.processedSample = processedSample;
        this.resultValue = resultValue;
    }

    public double getResultValue() {
        return this.resultValue;
    }

    public BufferedImage getOriginSample() {
        return this.originSample;
    }

    public DigitBufferedImage getProcessedSample() {
        return this.processedSample;
    }
}
