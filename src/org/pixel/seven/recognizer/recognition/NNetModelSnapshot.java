package org.pixel.seven.recognizer.recognition;

/**
 * The type N net model snapshot.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 14.03.2021 17:01 <p>
 */
public class NNetModelSnapshot {

    /**
     * Of n net model snapshot.
     *
     * @param weights     the weights
     * @param probability the probability
     * @return the n net model snapshot
     */
    public static NNetModelSnapshot of(double[] weights, double probability) {
        return new NNetModelSnapshot(weights, probability);
    }

    /**
     * The Probability.
     */
    private double probability;

    /**
     * The Weights.
     */
    private double[] weights;

    /**
     * The Max weight.
     */
    private double maxWeight;

    /**
     * The Min weight.
     */
    private double minWeight;

    /**
     * Instantiates a new N net model snapshot.
     *
     * @param weights     the weights
     * @param probability the probability
     */
    public NNetModelSnapshot(double[] weights, double probability) {
        this.weights = weights;
        this.setExtremumWeights();
        this.probability = probability;
    }

    /**
     * Gets probability.
     *
     * @return the probability
     */
    public double getProbability() {
        return this.probability;
    }

    /**
     * Get weights double [ ].
     *
     * @return the double [ ]
     */
    public double[] getWeights() {
        return this.weights;
    }

    /**
     * Gets max weight.
     *
     * @return the max weight
     */
    public double getMaxWeight() {
        return this.maxWeight;
    }

    /**
     * Gets min weight.
     *
     * @return the min weight
     */
    public double getMinWeight() {
        return this.minWeight;
    }

    /**
     * Sets extremum weights.
     */
    private void setExtremumWeights() {
        this.minWeight = this.weights[0];
        this.maxWeight = this.weights[0];

        for (int weightIndex = 0; weightIndex < this.weights.length; weightIndex++) {
            if (weights[weightIndex] < this.minWeight) this.minWeight = weights[weightIndex];
            if (weights[weightIndex] > this.maxWeight) this.maxWeight = weights[weightIndex];
        }
    }
}
