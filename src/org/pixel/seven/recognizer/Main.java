package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingPanel;
import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.recognition.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args == null || args.length < 2)
            throw new IllegalArgumentException("Required arguments is missing: digit & path to training pictures *.png");

        File[] imagesFiles = new File(args[1]).listFiles();
        //int samplesCount = imagesFiles.length;
        //Sample[] samples = new Sample[samplesCount];
        TrainingSet<Sample> trainingSet = new TrainingSet<>();
        for (File imagesFile : imagesFiles) {
            trainingSet.addSample(
                    Sample.of(
                            ImageIO.read(imagesFile),
                            Integer.parseInt(imagesFile.getName().substring(10, 11))
                    )
            );
        }

        DigitBinaryClassifier neuro = new DigitBinaryClassifier(new Configuration(
                28,
                28,
                Integer.parseInt(args[0])
        ));

        DrawingPanel drawing = new DrawingPanel(28, 28);
        drawing.addMouseListener(new  MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) drawing.applyTool();
                else if (e.getButton() == 3) {
                    drawing.getSurface().changeBackground(Color.BLACK);
                    drawing.getSurface().clear();
                    drawing.repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 3) return;

                RecognitionResult result = neuro.recognize(drawing.getSurface().getImage());
                drawing.getSurface().changeBackground(result.getResult() ? Color.GREEN : Color.RED);
                drawing.repaint();
            }
        });

        drawing.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                drawing.setPosition(e.getX(), e.getY());
                drawing.applyTool();
            }

            public void mouseMoved(MouseEvent e) {
                drawing.setPosition(e.getX(), e.getY());
            }
        });

        drawing.addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                drawing.getTool().changeSizeOn(e.getWheelRotation());
                drawing.setupCursor();
            }
        });

        MainFrame theMainFrame = new  MainFrame(drawing);
        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                theMainFrame.init();
            }
        });

        boolean retrained = neuro.retrain(trainingSet, net -> {
            double[] weights = net.getWeights();
            double minW = weights[0], maxW = weights[0];
            for (int ii = 0; ii < weights.length; ii++) {
                if (weights[ii] < minW) minW = weights[ii];
                if (weights[ii] > maxW) maxW = weights[ii];
            }

            int color = Color.BLACK.getRGB();
            BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 28; x++) {
                for (int y = 0; y < 28; y++) {
                    if (weights[y + x * 28] == 0) color = Color.BLACK.getRGB();
                    else if (weights[y + x * 28] < 0) color = new Color((int) ((255 / (minW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0, 0).getRGB();
                    else if (weights[y + x * 28] > 0) color = new Color(0, (int) ((255 / (maxW * 1000)) * (int) (weights[y + x * 28] * 1000)), 0).getRGB();

                    image.setRGB(y, x, color);
                }
            }

            drawing.setSurface(new Canvas(image));
            theMainFrame.setTitle("The min weight = " + minW + ", the max weight = " + maxW);
        });

        if (retrained) {
            Thread.sleep(2000);
            drawing.getSurface().clear();
            drawing.repaint();
        }
    }
}
