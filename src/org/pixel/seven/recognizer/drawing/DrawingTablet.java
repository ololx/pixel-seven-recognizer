package org.pixel.seven.recognizer.drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 22.02.2021 13:59
 * <p>
 * @author Alexander A. Kropotin
 */
public class DrawingTablet extends JPanel implements MouseListener, MouseMotionListener {

    public interface DrawingSurface {

        BufferedImage getImage();
    }

    public static class Canvas implements DrawingSurface {

        private BufferedImage image;

        public Canvas(int width, int height) {
            this(width, height, BufferedImage.TYPE_INT_RGB);
        }

        public Canvas(int width, int height, int imageType) {
            this(new  BufferedImage(width, height, imageType));
        }

        public Canvas(BufferedImage image) {
            Objects.requireNonNull(image, "The image couldn't be null");
            this.image = image;
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }
    }

    public interface DrawingTool {

        void apply(DrawingSurface surface);
    }

    public class Pencil implements DrawingTool {

        private Color color;

        private Stroke size;

        private int posX;

        private int posY;

        public Pencil() {
            this(1f, Color.BLACK);
        }

        public Pencil(float size, Color color) {
            this.setSize(size);
            this.setColor(color);
            this.setPosition(0, 0);
        }

        @Override
        public void apply(DrawingSurface surface) {
            Graphics2D graphics = (Graphics2D) surface.getImage() .getGraphics();
            graphics.setStroke(this.size);
            graphics.setColor(this.color);
            graphics.drawLine(posX, posY, posX, posY);
        }

        public void setPosition(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public void setSize(float size) {
            this.size = new BasicStroke(size);
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    private Canvas canvas;

    private Pencil brush;

    public DrawingTablet(int width, int height) {
        this.canvas = new Canvas(width, height);
        this.brush = new Pencil(3f, Color.WHITE);
        Graphics2D d2 = this.canvas.getImage().createGraphics();
        d2.setColor(Color.BLACK);
        d2.fillRect(0, 0, width, height);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.canvas.getImage(), 0, 0, this.getWidth(), this.getHeight(),null);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        this.paint();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.paint();
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

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        this.setBrushPosition(e.getX(), e.getY());
        this.paint();
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.setBrushPosition(e.getX(), e.getY());
    }

    private void paint() {
        this.brush.apply(this.canvas);

        this.repaint();
    }

    private void setBrushPosition(int mouseX, int mouseY) {
        int height = this.getHeight();
        int width = this.getWidth();
        int x = (int) (mouseX * (1d * this.canvas.getImage().getWidth() / width));
        int y = (int) (mouseY * (1d * this.canvas.getImage().getHeight() / height));

        this.brush.setPosition(x, y);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.repaint();
    }
}
