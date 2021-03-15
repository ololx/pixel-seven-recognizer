package org.pixel.seven.recognizer.recognition.nn;

/**
 * The interface Neuron.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 19.02.2021 21:20 <p>
 */
public interface Neuron {

    /**
     * The interface Adder.
     */
    interface Adder {

        /**
         * Calculate double.
         *
         * @param inputs  the inputs
         * @param weights the weights
         * @return the double
         */
        static double calculate(double[] inputs, double[] weights) {
            double amount = 0;
            for (int link = 0; link < inputs.length; link++) {
                amount += inputs[link] * weights[link];
            }

            return amount;
        }
    }

    /**
     * The interface Activation function.
     */
    interface ActivationFunction {

        /**
         * Calculate double.
         *
         * @param association the association
         * @return the double
         */
        double calculate(double association);
    }

    /**
     * The enum Activation functions.
     */
    enum ActivationFunctions {

        /**
         * Sigmoid activation functions.
         */
        SIGMOID(association ->  1 / (1 + Math.exp(-0.25d * association))),
        /**
         * Hyperbolic tangent activation functions.
         */
        HYPERBOLIC_TANGENT(association -> Math.tan(association / 1));

        /**
         * The Activation function.
         */
        private ActivationFunction activationFunction;

        /**
         * Instantiates a new Activation functions.
         *
         * @param activationFunction the activation function
         */
        ActivationFunctions(ActivationFunction activationFunction) {
            this.activationFunction = activationFunction;
        }

        /**
         * Gets activation function.
         *
         * @return the activation function
         */
        public ActivationFunction getActivationFunction() {
            return this.activationFunction;
        }
    }

    /**
     * Proceed double.
     *
     * @param input the input
     * @return the double
     */
    double proceed(double[] input);

    /**
     * Get weights double [ ].
     *
     * @return the double [ ]
     */
    double[] getWeights() ;

    /**
     * Sets weights.
     *
     * @param weights the weights
     */
    void setWeights(double[] weights);

    /**
     * Increase weight.
     *
     * @param index the index
     * @param value the value
     */
    void increaseWeight(int index, double value);

    /**
     * Decrease weight.
     *
     * @param index the index
     * @param value the value
     */
    void decreaseWeight(int index, double value);
}
