package org.pixel.seven.recognizer.recognition;

/**
 * @project pixel-seven-recognizer
 * @created 05.03.2021 23:00
 * <p>
 * @author Alexander A. Kropotin
 */
public class Configuration {

    private int sampleWidth = 28;

    private int sampleHeight = 28;

    private String modelPath;

    public Configuration(int sampleWidth, int sampleHeight) {
        this(sampleWidth, sampleHeight, null);
    }

    public Configuration(int sampleWidth, int sampleHeight, String digitModelPath) {
        this.setSampleWidth(sampleWidth);
        this.setSampleHeight(sampleHeight);
        this.setModelPath(digitModelPath);
    }

    public int getSampleWidth() {
        return this.sampleWidth;
    }

    public void setSampleWidth(int width) {
        this.sampleWidth = width;
    }

    public int getSampleHeight() {
        return this.sampleHeight;
    }

    public void setSampleHeight(int height) {
        this.sampleHeight = height;
    }

    public String getModelPath() {
        return this.modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
