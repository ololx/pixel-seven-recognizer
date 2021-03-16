package org.pixel.seven.recognizer.recognition.nn;

import java.util.Objects;

/**
 * The type Artificial neuron.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 12.03.2021 18:01 <p>
 */
public class ArtificialNeuron implements Neuron {

    /**
     * The Weights.
     */
    private double[] weights;

    /**
     * The Activation function.
     */
    private ActivationFunction activationFunction;

    /**
     * Instantiates a new Artificial neuron.
     *
     * @param size               the size
     * @param activationFunction the activation function
     */
    public ArtificialNeuron(int size, ActivationFunction activationFunction) {
        this.weights = new double[size];

        Objects.requireNonNull(activationFunction, "The activation function couldn't be null");
        this.activationFunction = activationFunction;
    }

    /**
     * Proceed double.
     *
     * @param input the input
     * @return the double
     */
    @Override
    public double proceed(double[] input) {
        return this.activationFunction.calculate(Adder.calculate(input, this.weights));
    }

    /**
     * Get weights double [ ].
     *
     * @return the double [ ]
     */
    @Override
    public double[] getWeights() {
        return this.weights;
    }

    /**
     * Sets weights.
     *
     * @param weights the weights
     */
    @Override
    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    /**
     * Increase weight.
     *
     * @param index the index
     * @param value the value
     */
    @Override
    public void increaseWeight(int index, double value) {
        Objects.checkIndex(index, this.weights.length);
        this.weights[index] += value;
    }

    /**
     * Decrease weight.
     *
     * @param index the index
     * @param value the value
     */
    @Override
    public void decreaseWeight(int index, double value) {
        Objects.checkIndex(index, this.weights.length);
        this.weights[index] -= value;
    }
}
