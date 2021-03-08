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
     * @param input the input
     * @return the double
     */
    double proceed(int[] input);

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
     * Decrease weights.
     */
    void decreaseWeights();

    /**
     * Increase weights.
     */
    void increaseWeights();

    /**
     * Gets output.
     *
     * @return the output
     */
    double getOutput();
}
