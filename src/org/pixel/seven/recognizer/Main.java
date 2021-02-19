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

    public static void main(String[] args) throws IOException {
        SbPerceptron neuro = new SbPerceptron(28 * 28, ((28 * 28) / 2)  + 1);

        Set<Integer> sevenIndxs = new HashSet<>();
        int samples = 20_000;
        BufferedImage[] images = new BufferedImage[samples];
        int[] digits = new int[samples];
        File[] imagesFiles = new File("./input").listFiles();
        for (int i = 0; i < samples; i++) {
            images[i] = ImageIO.read(imagesFiles[i]);
            digits[i] = Integer.parseInt(imagesFiles[i].getName().charAt(10) + "");
            if (digits[i] == 3) sevenIndxs.add(i);
        }

        int[][] inputs = new int[samples][784];
        for (int i = 0; i < samples; i++) {
            int[] pixels = new int[28 * 28];
            images[i].getRGB(0, 0, 28, 28, pixels, 0, 28);
            for (int j = 0; j < pixels.length; j++) {
                inputs[i][j] = pixels[j] == Color.BLACK.getRGB() ? 0 : 1;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < samples; j++) {
                int option = digits[j];
                neuro.start(inputs[j]);

                if (option != 3 && neuro.getReaction() == 1) neuro.decreaseWeights();
                else if (option == 3 && neuro.getReaction() != 1) {
                    for (int x = 0; x < 28; x++) {
                        for (int y = 0; y < 28; y++) {
                            System.out.print(inputs[j][x + y * 28] + " ");
                        }

                        System.out.println();
                    }

                    for (int k : sevenIndxs) {
                        neuro.start(inputs[k]);
                        if (neuro.getReaction() != 1) neuro.increaseWeights();
                    }
                }
            }
        }

        int[] weights = neuro.getWeights();
        for (int x = 0; x < 28; x++) {
            for (int y = 0; y < 28; y++) {
                System.err.print(weights[x + y * 28] + " ");
            }

            System.err.println();
        }

	    new DrawingFrame(neuro, 28, 28).init();
    }
}
