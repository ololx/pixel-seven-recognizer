package org.pixel.seven.recognizer.drawing;

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

    public DrawingFrame(JFrame main, int width, int height) {
        this(width, height);
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
        this.paint(e.getX(), e.getY());
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.paint(e.getX(), e.getY());
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void init() {
        addMouseMotionListener(this);
        setSize(500, 500);
        setVisible(true);
        this.paint();
    }

    private void paint() {
        int height = this.getSize().height;
        int width = this.getSize().width;
        this.draw(image, width, height);
    }

    private void paint(int x, int y) {
        int height = this.getSize().height;
        int width = this.getSize().width;

        image.setRGB((int) (x * (1d * image.getWidth() / width)), (int) (y * (1d * image.getHeight() / height)), Color.WHITE.getRGB());
        this.draw(image, width, height);
    }

    private void draw(BufferedImage image, int width, int height) {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, width, height,null);
    }
}
