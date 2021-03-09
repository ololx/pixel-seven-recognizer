package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingPanel;
import org.pixel.seven.recognizer.drawing.tool.ColoringTool;
import org.pixel.seven.recognizer.drawing.tool.DrawingTool;
import org.pixel.seven.recognizer.recognition.Configuration;
import org.pixel.seven.recognizer.recognition.DigitBinaryClassifier;
import org.pixel.seven.recognizer.recognition.RecognitionResult;
import org.pixel.seven.recognizer.recognition.Sample;
import org.pixel.seven.recognizer.recognition.nn.Neuron;
import org.pixel.seven.recognizer.recognition.nn.SingleLayerPerceptron;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                2
        ));

        DrawingPanel drawing = new DrawingPanel(28, 28);
        drawing.addMouseListener(new  MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) drawing.applyTool(e.getX(), e.getY(), DrawingPanel.TOOLS.get("pencil"));
                else if (e.getButton() == 3) drawing.applyTool(e.getX(), e.getY(), DrawingPanel.TOOLS.get("eraser"));
            }

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 3) return;

                RecognitionResult result = neuro.recognize(drawing.getSurface().getImage());

                ColoringTool tool = (ColoringTool) DrawingPanel.TOOLS.get("filling");
                if (result.getResult()) tool.setColor(Color.GREEN.getRGB());
                else tool.setColor(Color.RED.getRGB());

                drawing.applyTool(0, 0, (DrawingTool) tool);
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

        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                new  MainFrame().init(drawing);
            }
        });

        neuro.retrain(drawing, samples);
    }
}
