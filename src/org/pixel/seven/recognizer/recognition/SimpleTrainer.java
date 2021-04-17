package org.pixel.seven.recognizer.recognition;

import org.pixel.seven.recognizer.image.DigitBufferedImage;
import org.pixel.seven.recognizer.image.processing.DigitAccentuation;
import org.pixel.seven.recognizer.image.processing.DigitScaling;
import org.pixel.seven.recognizer.recognition.nn.NNet;

import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * @project pixel-seven-recognizer
 * @created 17.04.2021 14:35
 * <p>
 * @author Alexander A. Kropotin
 */
public class SimpleTrainer implements NnTrainer<Sample> {

    private static final Logger LOG = Logger.getLogger(SimpleTrainer.class.getName());

    private NNet network;

    private TrainingSet<Sample> samples = new TrainingSet<>();

    private Consumer<NNetModelSnapshot> consumer = model -> LOG.info(model.toString());

    public SimpleTrainer(TrainingSet<Sample> trainingSet, Consumer<NNetModelSnapshot> consumer) {
        this(trainingSet, consumer, null);
    }

    public SimpleTrainer(TrainingSet<Sample> trainingSet, Consumer<NNetModelSnapshot> consumer, NNet network) {
        this.setNetwork(network);
        this.setTrainingSet(trainingSet);
        this.setConsumer(consumer);
    }

    public void setNetwork(NNet network) {
        if (network != null) this.network = network;
    }

    public void setTrainingSet(TrainingSet<Sample> trainingSet) {
        if (trainingSet != null) this.samples = trainingSet;
    }

    public void setConsumer(Consumer<NNetModelSnapshot> consumer) {
        if (consumer != null) this.consumer = consumer;
    }

    public boolean retrain(int trainForDigit, double trainTillProbabilityIs) {
        if (this.network == null || this.samples.getSamplesCount() < 1) {
            LOG.warning(String.format(
                    "The model is %s and training set consists of %s.",
                    this.network,
                    this.samples.getSamplesCount()
            ));

            return false;
        }

        double probability = 0, maxProbability = 0, right = 0;
        do {
            maxProbability = probability;
            right = 0;
            int iteration = 0;
            for (Sample sample : this.samples.getSamples()) {
                iteration++;
                int[] inputPixels = new DigitBufferedImage(sample.getSample())
                        .process(
                                new DigitAccentuation(),
                                new DigitScaling()
                        ).getPixels();
                int value = sample.getValue();
                this.network.setInput(inputPixels);

                this.network.proceed();
                if (value != trainForDigit && this.network.getOutput() == 1) {
                    this.network.decreaseWeights();
                } else if (value == trainForDigit && this.network.getOutput() != 1) {
                    this.network.increaseWeights();
                } else if (value == trainForDigit && this.network.getOutput() == 1
                        || value != trainForDigit && this.network.getOutput() != 1) {
                    right++;
                }

                double iterationProbability = iteration > 0 ? (100d / iteration) * right : 0;
                consumer.accept(NNetModelSnapshot.of(this.network.getWeights(), iterationProbability));
            }

            probability = (100d / this.samples.getSamplesCount()) * right;
            LOG.info("PR = " + probability);
        } while (probability < trainTillProbabilityIs && maxProbability <= probability);

        return true;
    }
}
