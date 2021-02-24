package org.pixel.seven.recognizer.recognition;

/**
 * @project pixel-seven-recognizer
 * @created 19.02.2021 21:20
 * <p>
 * @author Alexander A. Kropotin
 */
public class SingleLayerPerceptron implements Neuron {

    /**
     * The default weight inc/dec step value
     */
    public static final double DEFAUL_EDUCATION_SPEED = .01d;

    /**
     * The default weight inc/dec step value
     */
    public static final Activation DEFAUL_ACTIVATION_FUNCTION = (summ) ->  1 / (1 + Math.exp(-summ));

    /**
     * The array of S-Elements (this.sensors)
     */
    private int[] sensors;

    /**
     * The one A-Elements (associative)
     */
    private double association;

    /**
     * The one R-Element (reactive)
     */
    private int reaction;

    /**
     * The weights of sensors
     * (links between s-elements and a-element)
     */
    private double[] weights;

    /**
     * The activation threshold
     */
    private Activation activation;

    /**
     * The weight inc/dec step value
     */
    private double speed;

    public SingleLayerPerceptron(int size) {
        this(size, DEFAUL_ACTIVATION_FUNCTION, DEFAUL_EDUCATION_SPEED);
    }

    public SingleLayerPerceptron(int size, Activation activation) {
        this(size, activation, DEFAUL_EDUCATION_SPEED);
    }

    public SingleLayerPerceptron(int size, Activation activation, double speed) {
        this.sensors = new int[size];
        this.weights = new double[size];
        this.activation = activation;
        this.speed = speed;
    }

    public int start(int[] input) {
        this.sensors = input;
        this.reaction = (int) (this.associate());

        return this.reaction;
    }

    public void decreaseWeights() {
        for (int link = 0; link < this.sensors.length; link++) {
            if (this.sensors[link] == 1) this.weights[link] -= this.speed;
        }
    }

    public void increaseWeights() {
        for (int link = 0; link < this.sensors.length; link++) {
            if (this.sensors[link] == 1) this.weights[link] += this.speed;
        }
    }

    public int getReaction() {
        return this.reaction;
    }

    public double[] getWeights() {
        return this.weights;
    }

    public void setSensors(int[] sensors) {
        this.sensors = sensors;
    }

    private double associate() {
        this.association = this.getActivationFunction(this.getWeightedSumm());

        return this.association;
    }

    private double getWeightedSumm() {
        double summ = 0;
        for (int link = 0; link < this.sensors.length; link++) {
            summ += this.sensors[link] * this.weights[link];
        }

        return summ;
    }

    private double getActivationFunction(double summ) {
        return this.activation.apply(summ);
    }
}
