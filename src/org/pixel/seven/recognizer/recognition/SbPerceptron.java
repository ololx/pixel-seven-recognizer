package org.pixel.seven.recognizer.recognition;

/**
 * @project pixel-seven-recognizer
 * @created 19.02.2021 21:20
 * <p>
 * @author Alexander A. Kropotin
 */
public class SbPerceptron implements Neuron {

    /**
     * The array of S-Elements (this.sensors)
     */
    private int[] sensors;

    /**
     * The one A-Elements (associative)
     */
    private int association;

    /**
     * The one R-Element (reactive)
     */
    private int reaction;

    /**
     * The weights of sensors
     * (links between s-elements and a-element)
     */
    private int[] weights;

    /**
     * The activation threshold
     */
    public int limit;

    public SbPerceptron(int size, int limit) {
        this.sensors = new int[size];
        this.weights = new int[size];
        this.limit = limit;
    }

    public int start(int[] input) {
        this.sensors = input;
        this.reaction = this.toAssociate() >= this.limit ? 1 : 0;

        return this.reaction;
    }

    public void decreaseWeights() {
        for (int link = 0; link < this.sensors.length; link++) {
            if (this.sensors[link] == 1) this.weights[link] -= 1;
        }
    }

    public void increaseWeights() {
        for (int i = 0; i < this.sensors.length; i++) {
            if (this.sensors[i] == 1) this.weights[i] += 1;
        }
    }

    public int getReaction() {
        return this.reaction;
    }

    public int[] getWeights() {
        return this.weights;
    }

    public void setSensors(int[] sensors) {
        this.sensors = sensors;
    }

    private int toAssociate () {
        this.association = 0;
        for (int link = 0; link < this.sensors.length; link++) {
            this.association += this.sensors[link] * this.weights[link];
        }

        return this.association;
    }
}
