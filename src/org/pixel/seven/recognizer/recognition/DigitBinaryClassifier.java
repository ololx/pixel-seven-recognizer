package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.recognition.nn.NNet;
import org.pixel.seven.recognizer.recognition.nn.SingleLayerPerceptron;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 05.03.2021 20:24
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitBinaryClassifier implements Recognizer<BufferedImage>, TrainableRecognizer<Sample> {

    private NNet network;

    private Configuration cfg;

    public DigitBinaryClassifier(Configuration cfg) {
        Objects.requireNonNull(cfg, "The configuration couldn't be null");
        this.cfg = cfg;

        this.network = new SingleLayerPerceptron(
                this.cfg.getInputSize(),
                this.cfg.getActivationFunction(),
                this.cfg.getTrainingSpeed()
        );
    }

    /**
     * Retrain boolean.
     *
     * @param samples the samples
     * @return the boolean
     */
    @Override
    public boolean retrain(Sample... samples) {
        return false;
    }

    /**
     * Recognize.
     *
     * @param input the input
     */
    @Override
    public void recognize(BufferedImage input) {
        return;
    }
}
