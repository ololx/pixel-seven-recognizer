package org.pixel.seven.recognizer.recognition.nn;

/**
 * The interface N net.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 08.03.2021 09:46 <p>
 */
public interface NNet {

    /**
     * Proceed double.
     *
     * @return the double
     */
    double proceed();

    /**
     * Sets input.
     *
     * @param input the input
     */
    void setInput(int[] input);

    /**
     * Gets output.
     *
     * @return the output
     */
    double getOutput();

    /**
     * Get weights double [ ].
     *
     * @return the double [ ]
     */
    double[] getWeights();

    /**
     * Decrease weights.
     */
    void decreaseWeights();

    /**
     * Increase weights.
     */
    void increaseWeights();
}
