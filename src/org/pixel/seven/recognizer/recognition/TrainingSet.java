package org.pixel.seven.recognizer.recognition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 13.03.2021 15:42
 * <p>
 * @author Alexander A. Kropotin
 */
public class TrainingSet<S> {

    private List<S> samples;

    {
        this.samples = new ArrayList<>();
    }

    public void addSample(S sample) {
        this.samples.add(sample);
    }

    public S getSample(int index) {
        return this.samples.get(index);
    }

    public List<S> getSamples() {
        return this.samples;
    }

    public int getSamplesCount() {
        return this.samples.size();
    }
}
