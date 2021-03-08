package org.pixel.seven.recognizer.recognition.nn;

/**
 * The type Single layer perceptron.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 19.02.2021 21:20 <p>
 */
public class SingleLayerPerceptron implements NNet, Neuron {

    /**
     * The type Binary adder.
     */
    private class BinaryAdder implements Adder {
    }

    /**
     * The array of S-Elements (sensors)
     */
    private int[] sensors;

    /**
     * The weights of sensors
     * (links between s-elements and a-element)
     */
    private double[] weights;

    /**
     * The one A-Elements (associative)
     */
    private Adder adder;

    /**
     * The activation threshold
     */
    private ActivationFunction activation;

    /**
     * The one R-Element (reactive)
     */
    private double output;

    /**
     * The weight inc/dec step value
     */
    private double speed;

    /**
     * Instantiates a new Single layer perceptron.
     *
     * @param size       the size
     * @param activation the activation
     * @param speed      the speed
     */
    public SingleLayerPerceptron(int size, ActivationFunction activation, double speed) {
        this.adder = new BinaryAdder();
        this.sensors = new int[size];
        this.weights = new double[size];
        this.activation = activation;
        this.speed = speed;
    }

    /**
     * Proceed double.
     *
     * @param input the input
     * @return the double
     */
    @Override
    public double proceed(int[] input) {
        this.setInput(input);
        this.output = this.activation.calculate(this.adder.calculate(this.sensors, this.weights));

        return this.output;
    }

    /**
     * Decrease weights.
     */
    @Override
    public void decreaseWeights() {
        for (int link = 0; link < this.sensors.length; link++) {
            if (this.sensors[link] == 1) this.weights[link] -= this.speed;
        }
    }

    /**
     * Increase weights.
     */
    @Override
    public void increaseWeights() {
        for (int link = 0; link < this.sensors.length; link++) {
            if (this.sensors[link] == 1) this.weights[link] += this.speed;
        }
    }

    /**
     * Gets output.
     *
     * @return the output
     */
    @Override
    public double getOutput() {
        return this.output;
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
     * Sets sensors.
     *
     * @param input the sensors
     */
    private void setInput(int[] input) {
        this.sensors = input;
    }
}
