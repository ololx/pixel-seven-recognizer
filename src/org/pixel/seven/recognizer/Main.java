package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingFrame;
import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.image.DigitBufferedImage;
import org.pixel.seven.recognizer.image.processing.DigitAccentuation;
import org.pixel.seven.recognizer.image.processing.DigitScaling;
import org.pixel.seven.recognizer.recognition.SingleLayerPerceptron;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static final int digit = 2;

    public static void main(String[] args) throws IOException, InterruptedException {
        SingleLayerPerceptron neuro = new SingleLayerPerceptron(28 * 28, (summ) ->  1 / (1 + Math.exp(-.25 * summ)), .01d);
        File[] imagesFiles = new File("./input").listFiles();
        int samples = imagesFiles.length;
        DigitBufferedImage[] images = new DigitBufferedImage[samples];
        int[] digits = new int[samples];
        for (int i = 0; i < samples; i++) {
            images[i] = new DigitBufferedImage(ImageIO.read(imagesFiles[i]));
            digits[i] = Integer.parseInt(imagesFiles[i].getName().substring(10, 11));
        }

        int[][] inputs = new int[samples][784];
        for (int i = 0; i < samples; i++) {
            int[] pixels = images[i].process(
                    new DigitAccentuation(),
                    new DigitScaling()
            ).getPixels();

            for (int j = 0; j < pixels.length; j++) {
                inputs[i][j] = pixels[j] == Color.BLACK.getRGB() ? 0 : 1;
            }
        }

        DrawingTablet drawing = new DrawingTablet(28, 28);

        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new  MainFrame().init(drawing);
            }
        });

        double prob = 0;
        for (int i = 0; i < samples; i++) {
            int right = 0;

            for (int j = 0; j < samples; j++) {
                int option = digits[j];
                neuro.proceed(inputs[j]);

                if (option != digit && neuro.getOutput() == 1) {
                    neuro.decreaseWeights();
                } else if (option == digit && neuro.getOutput() != 1) {
                    neuro.increaseWeights();
                } else if (option == digit && neuro.getOutput() == 1 || option != digit && neuro.getOutput() != 1) {
                    right++;
                }

                double[] weights = neuro.getWeights();
                int color = Color.BLACK.getRGB();

                double minW = weights[0], maxW = weights[0];
                for (int ii = 0; ii < weights.length; ii++) {
                    if (weights[ii] < minW) minW = weights[ii];
                    if (weights[ii] > maxW) maxW = weights[ii];
                }

                System.err.println("min = " + minW + ";   max = " + maxW);

                BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
                for (int x = 0; x < 28; x++) {
                    for (int y = 0; y < 28; y++) {
                        if (inputs[j][y + x * 28] != 0) color = Color.WHITE.getRGB();
                        else if (weights[y + x * 28] == 0) color = Color.BLACK.getRGB();
                        else if (weights[y + x * 28] < 0) color = new Color((int) ((255 / (minW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0, 0).getRGB();
                        else if (weights[y + x * 28] > 0) color = new Color(0, (int) ((255 / (maxW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0).getRGB();

                        image.setRGB(y, x, color);
                    }
                }

                drawing.setCanvas(new Canvas(image));
                //Thread.sleep(1);
            }

            prob = (100d / (samples)) * right;
            System.err.println("PR = " + prob);
            if (prob >= 96) break;
        }

        double[] weights = neuro.getWeights();
        int color = Color.BLACK.getRGB();

        double minW = weights[0], maxW = weights[0];
        for (int ii = 0; ii < weights.length; ii++) {
            if (weights[ii] < minW) minW = weights[ii];
            if (weights[ii] > maxW) maxW = weights[ii];
        }

        BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 28; x++) {
            for (int y = 0; y < 28; y++) {
                if (weights[y + x * 28] == 0) color = Color.BLACK.getRGB();
                else if (weights[y + x * 28] < 0) color = new Color((int) ((255 / (minW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0, 0).getRGB();
                else if (weights[y + x * 28] > 0) color = new Color(0, (int) ((255 / (maxW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0).getRGB();

                image.setRGB(y, x, color);
            }
        }

        drawing.setCanvas(new Canvas(image));

	    SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new DrawingFrame(neuro, 28, 28).init();
            }
        });
    }
}
