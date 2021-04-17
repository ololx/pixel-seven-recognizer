package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.recognition.nn.NNet;

import java.util.function.Consumer;

/**
 * @project pixel-seven-recognizer
 * @created 17.04.2021 14:33
 * <p>
 * @author Alexander A. Kropotin
 */
public interface NnTrainer<S> {

    void setNetwork(NNet network);

    void setTrainingSet(TrainingSet<S> trainingSet);

    void setConsumer(Consumer<NNetModelSnapshot> consumer);

    boolean retrain(int trainForDigit, double trainTillProbabilityIs);
}
