package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.recognition.nn.Neuron;

import java.util.Objects;

/**
 * The type Configuration.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 05.03.2021 23:00 <p>
 */
public class Configuration {

    /**
     * The default weight inc/dec step value
     */
    public static final double DEFAULT_TRAINING_SPEED = .01d;

    /**
     * The default weight inc/dec step value
     */
    public static final Neuron.ActivationFunction DEFAULT_ACTIVATION_FUNCTION = Neuron.ActivationFunctions.SIGMOID.getActivationFunction();

    /**
     * The Sample width.
     */
    private int sampleWidth;

    /**
     * The Sample height.
     */
    private int sampleHeight;

    /**
     * The Activation.
     */
    private Neuron.ActivationFunction activation;

    /**
     * The Training speed.
     */
    private double trainingSpeed;

    /**
     * The Recognition digit.
     */
    private int recognitionDigit;

    /**
     * The Model path.
     */
    private String modelPath;

    /**
     * Instantiates a new Configuration.
     *
     * @param sampleWidth      the sample width
     * @param sampleHeight     the sample height
     * @param recognitionDigit the recognition digit
     */
    public Configuration(int sampleWidth, int sampleHeight, int recognitionDigit) {
        this(sampleWidth, sampleHeight, recognitionDigit, DEFAULT_ACTIVATION_FUNCTION, DEFAULT_TRAINING_SPEED, null);
    }

    /**
     * Instantiates a new Configuration.
     *
     * @param sampleWidth        the sample width
     * @param sampleHeight       the sample height
     * @param recognitionDigit   the recognition digit
     * @param activationFunction the activation function
     * @param trainingSpeed      the training speed
     * @param digitModelPath     the digit model path
     */
    public Configuration(int sampleWidth,
                         int sampleHeight,
                         int recognitionDigit,
                         Neuron.ActivationFunction activationFunction,
                         double trainingSpeed,
                         String digitModelPath) {
        Objects.requireNonNull(activationFunction, "The activation function couldn't be null");
        this.activation = activationFunction;
        this.trainingSpeed = trainingSpeed;
        this.sampleWidth = sampleWidth;
        this.sampleHeight = sampleHeight;
        this.recognitionDigit = recognitionDigit;

        this.setModelPath(digitModelPath);
    }

    /**
     * Gets recognition digit.
     *
     * @return the recognition digit
     */
    public int getRecognitionDigit() {
        return this.recognitionDigit;
    }

    /**
     * Gets sample width.
     *
     * @return the sample width
     */
    public int getSampleWidth() {
        return this.sampleWidth;
    }

    /**
     * Gets sample height.
     *
     * @return the sample height
     */
    public int getSampleHeight() {
        return this.sampleHeight;
    }

    /**
     * Gets training speed.
     *
     * @return the training speed
     */
    public double getTrainingSpeed() {
        return this.trainingSpeed;
    }

    /**
     * Gets activation function.
     *
     * @return the activation function
     */
    public Neuron.ActivationFunction getActivationFunction() {
        return this.activation;
    }

    /**
     * Gets model path.
     *
     * @return the model path
     */
    public String getModelPath() {
        return this.modelPath;
    }

    /**
     * Sets model path.
     *
     * @param modelPath the model path
     */
    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    /**
     * Is model path presents boolean.
     *
     * @return the boolean
     */
    public boolean isModelPathPresents() {
        return this.modelPath != null;
    }

    /**
     * Gets input size.
     *
     * @return the input size
     */
    public int getInputSize() {
        return this.sampleWidth * this.sampleHeight;
    }
}
