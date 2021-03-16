package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingPanel;
import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.recognition.*;
import org.pixel.seven.recognizer.recognition.nn.NNet;
import org.pixel.seven.recognizer.recognition.nn.Neuron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The type Main.
 */
public class Main {

    private static final Logger LOG = Logger.getAnonymousLogger();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args == null || args.length < 2)
            throw new IllegalArgumentException("Required arguments is missing: digit & path to training pictures *.png");

        LOG.info("Init recognizer ...");
        Recognizer recognizer = DigitBinaryClassifier.of(
                28,
                28,
                Integer.parseInt(args[0]),
                Neuron.ActivationFunctions.SIGMOID.getActivationFunction(),
                .01d
        );
        DrawingPanel drawing = getDrawingPanel(recognizer);
        MainFrame mainFrame = MainFrame.builder()
                .child(drawing, 0, 0, 700, 700)
                .title("Recognizer in process of loading data set.")
                .build();

        LOG.info("Loading the training data set ...");
        TrainingSet<Sample> trainingSet = loadTrainingData(args[1]);
        LOG.info("The training data set was loaded.\nInit retrain process ...");
        mainFrame.init();
        boolean retrained = recognizer.retrain(
                trainingSet,
                model -> showRetrain(drawing, mainFrame, (NNetModelSnapshot) model)
        );

        LOG.info("The retrain process was completed. Enjoy =)");
        if (retrained) Thread.sleep(2000);

        mainFrame.setTitle("Recognizer is ready to use. Please, draw the "
                + recognizer.getConfiguration().getRecognitionDigit());
        drawing.getSurface().clear();
        drawing.repaint();
    }

    /**
     * Load training data training set.
     *
     * @param samplesDir the samples dir
     * @return the training set
     * @throws IOException the io exception
     */
    private static TrainingSet<Sample> loadTrainingData(String samplesDir) throws IOException {
        File trainingDir = new File(samplesDir);
        if (!trainingDir.exists()) throw new IllegalArgumentException("The directory with training pictures is not exists");

        File[] imagesFiles = trainingDir.listFiles();
        TrainingSet<Sample> trainingSet = new TrainingSet<>();
        for (File imagesFile : imagesFiles) {
            trainingSet.addSample(
                    Sample.of(
                            ImageIO.read(imagesFile),
                            Integer.parseInt(imagesFile.getName().substring(10, 11))
                    )
            );
        }

        return trainingSet;
    }

    /**
     * Gets drawing panel.
     *
     * @param recognizer the recognizer
     * @return the drawing panel
     */
    private static DrawingPanel getDrawingPanel(Recognizer recognizer) {
        DrawingPanel drawing = new DrawingPanel(28, 28);
        drawing.setupCursor();
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

                RecognitionResult result = recognizer.recognize(drawing.getSurface().getImage());
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

        return drawing;
    }

    /**
     * Show retrain.
     *
     * @param drawingTablet   the drawing tablet
     * @param mainFrame       the main frame
     * @param recognizerModel the recognizer model
     */
    public static void showRetrain(DrawingTablet drawingTablet, Frame mainFrame, NNetModelSnapshot recognizerModel) {
        int color = Color.BLACK.getRGB();
        BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 28; x++) {
            for (int y = 0; y < 28; y++) {
                if (recognizerModel.getWeights()[y + x * 28] == 0) color = Color.BLACK.getRGB();
                else if (recognizerModel.getWeights()[y + x * 28] < 0)
                    color = new Color((int) ((255 / (recognizerModel.getMinWeight() * 1000))
                            * (int) (recognizerModel.getWeights()[y + x * 28] * 1000)), 0, 0).getRGB();
                else if (recognizerModel.getWeights()[y + x * 28] > 0)
                    color = new Color(0, (int) ((255 / (recognizerModel.getMaxWeight() * 1000))
                            * (int) (recognizerModel.getWeights()[y + x * 28] * 1000)), 0).getRGB();

                image.setRGB(y, x, color);
            }
        }

        drawingTablet.setSurface(new Canvas(image));
        mainFrame.setTitle(
                String.format("Recognizer in training. Current probability = %s, with min weight = %s and max weight = %s",
                        String.format("%.0f", recognizerModel.getProbability()) + "%",
                        String.format("%.2f", recognizerModel.getMinWeight()),
                        String.format("%.2f", recognizerModel.getMaxWeight())
                )
        );
    }
}
