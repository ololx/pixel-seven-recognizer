package org.pixel.seven.recognizer.recognition;

/**
 * @project pixel-seven-recognizer
 * @created 19.02.2021 21:20
 * <p>
 * @author Alexander A. Kropotin
 */
public interface Neuron {

    @FunctionalInterface
    interface Activation {
        double apply(double association);
    }
}
