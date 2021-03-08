package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.drawing.DrawingPanel;
import org.pixel.seven.recognizer.image.DigitBufferedImage;
import org.pixel.seven.recognizer.image.processing.DigitAccentuation;
import org.pixel.seven.recognizer.image.processing.DigitScaling;
import org.pixel.seven.recognizer.recognition.Configuration;
import org.pixel.seven.recognizer.recognition.DigitBinaryClassifier;
import org.pixel.seven.recognizer.recognition.Sample;
import org.pixel.seven.recognizer.recognition.nn.Neuron;
import org.pixel.seven.recognizer.recognition.nn.SingleLayerPerceptron;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static final int digit = 2;

    public static void main(String[] args) throws IOException, InterruptedException {
        File[] imagesFiles = new File("./input").listFiles();
        int samplesCount = imagesFiles.length;
        Sample[] samples = new Sample[samplesCount];
        for (int i = 0; i < samplesCount; i++) {
            samples[i] = Sample.of(
                    ImageIO.read(imagesFiles[i]),
                    Integer.parseInt(imagesFiles[i].getName().substring(10, 11))
            );
        }

        DigitBinaryClassifier neuro = new DigitBinaryClassifier(new Configuration(
                28,
                28,
                Neuron.ActivationFunctions.SIGMOID.getActivationFunction(),
                0.01d,
                null
        ));
        DrawingPanel drawing = new DrawingPanel(28, 28);
        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new  MainFrame().init(drawing);
                new DrawingFrame(new SingleLayerPerceptron(
                        28 * 28,
                        Neuron.ActivationFunctions.SIGMOID.getActivationFunction(),
                        .01d), 28, 28).init();
            }
        });

        neuro.retrain(drawing, samples);
    }
}
