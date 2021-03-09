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
     * @param samples the samples
     * @return the boolean
     */
    @Override
    public boolean retrain(DrawingTablet observer, Sample... samples) {
        int samplesCount = samples.length;
        double probability = 0, maxProbability = 0, right = 0;
        do {
            maxProbability = probability;
            right = 0;
            for (Sample sample : samples) {
                int[] inputPixels = new DigitBufferedImage(sample.getSample())
                        .process(
                                new DigitAccentuation(),
                                new DigitScaling()
                        ).getPixels();
                int value = sample.getValue();

                this.network.proceed(inputPixels);

                if (value != this.cfg.getRecognitionDigit() && this.network.getOutput() == 1) {
                    this.network.decreaseWeights();
                } else if (value == this.cfg.getRecognitionDigit() && this.network.getOutput() != 1) {
                    this.network.increaseWeights();
                } else if (value == this.cfg.getRecognitionDigit() && this.network.getOutput() == 1
                        || value != this.cfg.getRecognitionDigit() && this.network.getOutput() != 1) {
                    right++;
                }

                double[] weights = this.network.getWeights();
                double minW = weights[0], maxW = weights[0];
                for (int ii = 0; ii < weights.length; ii++) {
                    if (weights[ii] < minW) minW = weights[ii];
                    if (weights[ii] > maxW) maxW = weights[ii];
                }

                System.err.println("min = " + minW + ";   max = " + maxW);

                //FIXME:: Заменить на обсервера потом
                int color = Color.BLACK.getRGB();
                BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
                for (int x = 0; x < 28; x++) {
                    for (int y = 0; y < 28; y++) {
                        if (weights[y + x * 28] == 0) color = Color.BLACK.getRGB();
                        else if (weights[y + x * 28] < 0) color = new Color((int) ((255 / (minW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0, 0).getRGB();
                        else if (weights[y + x * 28] > 0) color = new Color(0, (int) ((255 / (maxW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0).getRGB();

                        image.setRGB(y, x, color);
                    }
                }

                observer.setSurface(new Canvas(image));
            }

            probability = (100d / (samplesCount)) * right;
            System.err.println("PR = " + probability);
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
        this.network.proceed(processedSample.getPixels());

        return new RecognitionResult(
                input,
                processedSample,
                this.network.getOutput()
        );
    }
}
