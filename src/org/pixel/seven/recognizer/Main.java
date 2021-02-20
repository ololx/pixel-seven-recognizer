package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingFrame;
import org.pixel.seven.recognizer.recognition.SbPerceptron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static final int digit = 7;

    public static void main(String[] args) throws IOException {
        SbPerceptron neuro = new SbPerceptron(28 * 28, ((28 * 28) / 100) * 51);

        File[] imagesFiles = new File("./input").listFiles();
        int samples = imagesFiles.length;
        BufferedImage[] images = new BufferedImage[samples];
        int[] digits = new int[samples];
        for (int i = 0; i < samples; i++) {
            images[i] = ImageIO.read(imagesFiles[i]);
            digits[i] = Integer.parseInt(imagesFiles[i].getName().charAt(10) + "");
        }

        int[][] inputs = new int[samples][784];
        for (int i = 0; i < samples; i++) {
            int[] pixels = new int[28 * 28];
            images[i].getRGB(0, 0, 28, 28, pixels, 0, 28);
            for (int j = 0; j < pixels.length; j++) {
                inputs[i][j] = pixels[j] == Color.BLACK.getRGB() ? 0 : 1;
            }
        }

        double prob = 0;
        for (int i = 0; i < samples; i++) {
            int right = 0;

            for (int j = 0; j < samples; j++) {
                int option = digits[j];
                neuro.start(inputs[j]);

                if (option != digit && neuro.getReaction() == 1) {
                    neuro.decreaseWeights();
                } else if (option == digit && neuro.getReaction() != 1) {
                    neuro.increaseWeights();
                } else if (option == digit && neuro.getReaction() == 1 || option != digit && neuro.getReaction() != 1) {
                    right++;
                }
            }

            prob = (100d / (samples)) * right;
            System.err.println("PR = " + prob);
            if (prob >= 94) break;
        }

	    new DrawingFrame(neuro, 28, 28).init();
    }
}
