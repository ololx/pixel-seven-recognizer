package org.pixel.seven.recognizer.recognition;

/**
 * The interface Trainable.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 08.03.2021 17:59 <p>
 */
public interface TrainableRecognizer<S> {

    /**
     * Retrain boolean.
     *
     * @param samples the samples
     * @return the boolean
     */
    boolean retrain(S... samples);
}
