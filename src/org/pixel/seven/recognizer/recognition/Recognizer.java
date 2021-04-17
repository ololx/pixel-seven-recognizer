package org.pixel.seven.recognizer.recognition;

import java.util.function.Consumer;

/**
 * The interface Recognizer.
 *
 * @param <I> the type parameter
 * @param <S> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 05.03.2021 22:43 <p>
 */
public interface Recognizer<I, S> {

    /**
     * Recognize.
     *
     * @param input the input
     * @return recognition result
     */
    RecognitionResult recognize(I input);

    /**
     * Retrain boolean.
     *
     * @return the boolean
     */
    boolean retrain(NnTrainer<S> trainer);

    /**
     * Gets configuration.
     *
     * @return the configuration
     */
    Configuration getConfiguration();
}
