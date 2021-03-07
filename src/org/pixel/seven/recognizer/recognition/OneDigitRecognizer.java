package org.pixel.seven.recognizer.recognition;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 05.03.2021 20:24
 * <p>
 * @author Alexander A. Kropotin
 */
public class OneDigitRecognizer implements Recognizer<BufferedImage> {

    private Configuration configuration;

    public OneDigitRecognizer(Configuration configuration) {
        Objects.requireNonNull(configuration, "The configuration couldn't be null");
        this.configuration = configuration;
    }

    @Override
    public void recognize(BufferedImage input) {
        return;
    }
}
