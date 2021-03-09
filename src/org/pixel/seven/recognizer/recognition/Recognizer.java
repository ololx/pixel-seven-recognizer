package org.pixel.seven.recognizer.recognition;

/**
 * The interface Recognizer.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 05.03.2021 22:43 <p>
 */
public interface Recognizer<I> {

    /**
     * Recognize.
     *
     * @param input the input
     * @return
     */
    Result recognize(I input);
}
