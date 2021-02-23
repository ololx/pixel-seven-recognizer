package org.pixel.seven.recognizer.drawing;

import org.pixel.seven.recognizer.image.DigitBufferedImage;
import org.pixel.seven.recognizer.image.processing.DigitAccentuation;
import org.pixel.seven.recognizer.image.processing.DigitScaling;
import org.pixel.seven.recognizer.recognition.SingleLayerPerceptron;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 17.02.2021 14:30
 * <p>
 * @author Alexander A. Kropotin
 */
public class DrawingFrame extends JFrame implements MouseMotionListener, MouseInputListener {

    private BufferedImage image;

    private SingleLayerPerceptron neuro;

    public DrawingFrame(SingleLayerPerceptron neuro, int width, int height) {
        this(width, height);
        this.neuro = neuro;
    }
    public DrawingFrame(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.paint(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }


    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3 ) {
            this.image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
            this.paint();
        } else this.paint(e.getX(), e.getY());
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 3) return;

        int[] pixels = new DigitBufferedImage(image).process(
                new DigitAccentuation(),
                new DigitScaling()
        ).getPixels();
        for (int j = 0; j < pixels.length; j++) {
            pixels[j] = pixels[j] == Color.WHITE.getRGB() ? 1 : 0;
        }

        neuro.start(pixels);

        int color = neuro.getReaction() == 1 ? Color.GREEN.getRGB() : Color.RED.getRGB();
        for (int px = 0; px < 28; px++) {
            for (int py = 0; py < 28; py++) {
                if (image.getRGB(px, py) != Color.WHITE.getRGB()) {
                    image.setRGB(px, py, color);
                }
            }

        }

        this.draw(image, this.getWidth(), this.getHeight());
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.draw(image, this.getWidth(), this.getHeight());
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.draw(image, this.getWidth(), this.getHeight());
    }

    public void init() {
        addMouseMotionListener(this);
        addMouseListener(this);
        setSize(1024, 1024);
        setVisible(true);
    }

    private void paint() {
        int height = this.getHeight();
        int width = this.getWidth();

        this.draw(image, width, height);
    }

    private void paint(int xx, int yy) {
        int height = this.getSize().height;
        int width = this.getSize().width;
        int x = (int) (xx * (1d * image.getWidth() / width));
        int y = (int) (yy * (1d * image.getHeight() / height));


        Graphics g = this.image.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new  BasicStroke(2.0f));
        g2.drawLine(x, y, x, y);

        this.draw(image, width, height);
    }

    private void draw(BufferedImage image, int width, int height) {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, width, height,null);
    }
}
