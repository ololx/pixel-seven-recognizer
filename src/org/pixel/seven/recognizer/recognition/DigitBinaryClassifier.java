package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.image.DigitBufferedImage;
import org.pixel.seven.recognizer.image.processing.DigitAccentuation;
import org.pixel.seven.recognizer.image.processing.DigitScaling;
import org.pixel.seven.recognizer.recognition.nn.NNet;
import org.pixel.seven.recognizer.recognition.nn.Neuron;
import org.pixel.seven.recognizer.recognition.nn.SingleLayerPerceptron;

import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * The type Digit binary classifier.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 05.03.2021 20:24 <p>
 */
public class DigitBinaryClassifier implements Recognizer<BufferedImage, Sample> {

    /**
     * Of digit binary classifier.
     *
     * @param cfg the cfg
     * @return the digit binary classifier
     */
    public static DigitBinaryClassifier of(Configuration cfg) {
        return new DigitBinaryClassifier(cfg);
    }

    /**
     * Of digit binary classifier.
     *
     * @param sampleWidth        the sample width
     * @param sampleHeight       the sample height
     * @param recognitionDigit   the recognition digit
     * @param activationFunction the activation function
     * @param trainingSpeed      the training speed
     * @return the digit binary classifier
     */
    public static DigitBinaryClassifier of(int sampleWidth,
                                           int sampleHeight,
                                           int recognitionDigit,
                                           Neuron.ActivationFunction activationFunction,
                                           double trainingSpeed,
                                           double trainForProbability) {
        return new DigitBinaryClassifier(new Configuration(
                sampleWidth,
                sampleHeight,
                recognitionDigit,
                activationFunction,
                trainingSpeed,
                trainForProbability
        ));
    }

    /**
     * The Log.
     */
    private Logger log;

    /**
     * The Network.
     */
    private NNet network;

    /**
     * The Cfg.
     */
    private Configuration cfg;

    {
        this.log = Logger.getLogger(this.getClass().getName());
    }

    /**
     * Instantiates a new Digit binary classifier.
     *
     * @param cfg the cfg
     */
    public DigitBinaryClassifier(Configuration cfg) {
        Objects.requireNonNull(cfg, "The configuration couldn't be null");
        this.cfg = cfg;

        this.network = new SingleLayerPerceptron(
                this.cfg.getInputSize(),
                this.cfg.getActivationFunction(),
                this.cfg.getTrainingSpeed()
        );
    }

    @Override
    public boolean retrain(NnTrainer<Sample> trainer) {
        trainer.setNetwork(this.network);
        boolean result = trainer.retrain(this.cfg.getRecognitionDigit(), getConfiguration().getAimProbability());

        return result;
    }

    /**
     * Recognize.
     *
     * @param input the input
     * @return recognition result
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

    /**
     * Gets configuration.
     *
     * @return the configuration
     */
    @Override
    public Configuration getConfiguration() {
        return this.cfg;
    }
}
