package org.pixel.seven.recognizer.recognition;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Training set.
 *
 * @param <S> the type parameter
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 13.03.2021 15:42 <p>
 */
public class TrainingSet<S> {

    /**
     * The Samples.
     */
    private List<S> samples;

    {
        this.samples = new ArrayList<>();
    }

    /**
     * Add sample.
     *
     * @param sample the sample
     */
    public void addSample(S sample) {
        this.samples.add(sample);
    }

    /**
     * Gets sample.
     *
     * @param index the index
     * @return the sample
     */
    public S getSample(int index) {
        return this.samples.get(index);
    }

    /**
     * Gets samples.
     *
     * @return the samples
     */
    public List<S> getSamples() {
        return this.samples;
    }

    /**
     * Gets samples count.
     *
     * @return the samples count
     */
    public int getSamplesCount() {
        return this.samples.size();
    }
}
