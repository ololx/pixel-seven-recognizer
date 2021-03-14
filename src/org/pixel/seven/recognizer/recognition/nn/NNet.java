package org.pixel.seven.recognizer.recognition.nn;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The interface N net.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 08.03.2021 09:46 <p>
 */
public interface NNet {

    double proceed();

    void setInput(int[] input);

    double getOutput();

    double[] getWeights();

    void decreaseWeights();

    void increaseWeights();
}
