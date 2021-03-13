package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.image.DigitBufferedImage;
import org.pixel.seven.recognizer.image.processing.DigitAccentuation;
import org.pixel.seven.recognizer.image.processing.DigitScaling;
import org.pixel.seven.recognizer.recognition.nn.NNet;
import org.pixel.seven.recognizer.recognition.nn.SingleLayerPerceptron;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * @project pixel-seven-recognizer
 * @created 05.03.2021 20:24
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitBinaryClassifier implements Recognizer<BufferedImage, Sample> {

    private Logger log;

    private NNet network;

    private Configuration cfg;

    {
        this.log = Logger.getLogger(this.getClass().getName());
    }

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
     * @return the boolean
     */
    @Override
    public boolean retrain(TrainingSet<Sample> trainingSet, Consumer<NNet> consumer) {
        double probability = 0, maxProbability = 0, right = 0;
        do {
            maxProbability = probability;
            right = 0;
            for (Sample sample : trainingSet.getSamples()) {
                int[] inputPixels = new DigitBufferedImage(sample.getSample())
                        .process(
                                new DigitAccentuation(),
                                new DigitScaling()
                        ).getPixels();
                int value = sample.getValue();
                this.network.setInput(inputPixels);

                this.network.proceed();
                if (value != this.cfg.getRecognitionDigit() && this.network.getOutput() == 1) {
                    this.network.decreaseWeights();
                } else if (value == this.cfg.getRecognitionDigit() && this.network.getOutput() != 1) {
                    this.network.increaseWeights();
                } else if (value == this.cfg.getRecognitionDigit() && this.network.getOutput() == 1
                        || value != this.cfg.getRecognitionDigit() && this.network.getOutput() != 1) {
                    right++;
                }

                consumer.accept(this.network);
            }

            probability = (100d / trainingSet.getSamplesCount()) * right;
            log.info("PR = " + probability);
        } while (probability < 96 && maxProbability < probability);

        return true;
    }

    /**
     * Recognize.
     *
     * @param input the input
     * @return
     */
    @Override
    public RecognitionResult recognize(BufferedImage input) {
        DigitBufferedImage processedSample = new DigitBufferedImage(input).process(
                new DigitAccentuation(),
                new DigitScaling()
        );
        this.network.setInput(processedSample.getPixels());
        this.network.proceed();

        return new RecognitionResult(
                input,
                processedSample,
                this.network.getOutput()
        );
    }
}
