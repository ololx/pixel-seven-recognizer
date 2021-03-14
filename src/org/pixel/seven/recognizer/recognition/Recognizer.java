package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.recognition.nn.NNet;

import java.util.function.Consumer;

/**
 * The interface Recognizer.
 *
 * @param <I> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 05.03.2021 22:43 <p>
 */
public interface Recognizer<I, S> {

    /**
     * Recognize.
     *
     * @param input the input
     * @return
     */
    RecognitionResult recognize(I input);

    /**
     * Retrain boolean.
     *
     * @return the boolean
     */
    boolean retrain(TrainingSet<S> trainingSet, Consumer<NNetModelSnapshot> consumer);
}
