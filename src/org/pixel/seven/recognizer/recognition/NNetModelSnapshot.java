package org.pixel.seven.recognizer.recognition;

/**
 * @project pixel-seven-recognizer
 * @created 14.03.2021 17:01
 * <p>
 * @author Alexander A. Kropotin
 */
public class NNetModelSnapshot {

    public static NNetModelSnapshot of(double[] weights, double probability) {
        return new NNetModelSnapshot(weights, probability);
    }

    private double probability;

    private double[] weights;

    private double maxWeight;

    private double minWeight;

    public NNetModelSnapshot(double[] weights, double probability) {
        this.weights = weights;
        this.setExtremumWeights();
        this.probability = probability;
    }

    public double getProbability() {
        return this.probability;
    }

    public double[] getWeights() {
        return this.weights;
    }

    public double getMaxWeight() {
        return this.maxWeight;
    }

    public double getMinWeight() {
        return this.minWeight;
    }

    private void setExtremumWeights() {
        this.minWeight = this.weights[0];
        this.maxWeight = this.weights[0];

        for (int weightIndex = 0; weightIndex < this.weights.length; weightIndex++) {
            if (weights[weightIndex] < this.minWeight) this.minWeight = weights[weightIndex];
            if (weights[weightIndex] > this.maxWeight) this.maxWeight = weights[weightIndex];
        }
    }
}
