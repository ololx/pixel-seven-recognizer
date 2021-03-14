package org.pixel.seven.recognizer.recognition.nn;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Single layer perceptron.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 19.02.2021 21:20 <p>
 */
public class SingleLayerPerceptron implements NNet {

    /**
     * The array of S-Elements (Sensors)
     */
    private double[] sElements;

    private double output;

    /**
     * The one R-Element (reactive)
     */
    private ArtificialNeuron rElement;

    /**
     * The training speed - weight inc/dec step value
     */
    private double speed;

    /**
     * Instantiates a new Single layer perceptron.
     *
     * @param size       the size
     * @param activation the activation
     * @param speed      the speed
     */
    public SingleLayerPerceptron(int size, Neuron.ActivationFunction activation, double speed) {
        this.sElements = new double[size];
        this.rElement = new ArtificialNeuron(size, activation);
        this.speed = speed;
    }

    /**
     * Proceed double.
     *
     * @return the double
     */
    @Override
    public double proceed() {
        this.output = this.rElement.proceed(this.sElements);

        return output;
    }

    @Override
    public void setInput(int[] input) {
        for (int index = 0; index < input.length; index++) {
            if (input[index] >= 1) this.sElements[index] = 1;
            else this.sElements[index] = 0;
        }
    }

    @Override
    public double getOutput() {
        return this.output;
    }

    @Override
    public double[] getWeights() {
        return this.rElement.getWeights();
    }

    /**
     * Decrease weights.
     */
    @Override
    public void decreaseWeights() {
        for (int link = 0; link < this.sElements.length; link++) {
            if (this.sElements[link] == 1) this.rElement.decreaseWeight(link, this.speed);
        }
    }

    /**
     * Increase weights.
     */
    @Override
    public void increaseWeights() {
        for (int link = 0; link < this.sElements.length; link++) {
            if (this.sElements[link] == 1) this.rElement.increaseWeight(link, this.speed);
        }
    }
}
