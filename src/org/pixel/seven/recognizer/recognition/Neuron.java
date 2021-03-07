package org.pixel.seven.recognizer.recognition;

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
        default double calculate(int[] inputs, double[] weights) {
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

    enum ActivationFunctions {

        SIGMOID(association ->  1 / (1 + Math.exp(-.25 * association))),
        HYPERBOLIC_TANGENT(association -> Math.tan(association / 1));

        private ActivationFunction activationFunction;

        ActivationFunctions(ActivationFunction activationFunction) {
            this.activationFunction = activationFunction;
        }

        public ActivationFunction getActivationFunction() {
            return this.activationFunction;
        }
    }
}
