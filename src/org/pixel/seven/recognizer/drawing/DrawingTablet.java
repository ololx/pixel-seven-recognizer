package org.pixel.seven.recognizer.drawing;

import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.drawing.tool.DrawingTool;
import org.pixel.seven.recognizer.drawing.tool.Eraser;
import org.pixel.seven.recognizer.drawing.tool.Filling;
import org.pixel.seven.recognizer.drawing.tool.Pencil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Dimension2D;

/**
 * @project pixel-seven-recognizer
 * @created 22.02.2021 13:59
 * <p>
 * @author Alexander A. Kropotin
 */
public class DrawingTablet extends JPanel implements MouseListener, MouseMotionListener {

    private Canvas canvas;

    private DrawingTool brush;

    private DrawingTool filling;

    public DrawingTablet(int width, int height) {
        this.canvas = new Canvas(width, height);
        this.brush = new Eraser(3f);
        this.filling = new Filling(1f, Color.RED.getRGB());
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
        if (e.getButton() == 3) {
            this.setPosition(e.getX(), e.getY(), this.filling);
            this.filling.apply(this.canvas);
            this.repaint();
        } else this.paint();
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
        //this.paint();
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

        this.brush.setPosition(DrawingTool.Position.of(x, y));
    }

    private DrawingTool setPosition(int mouseX, int mouseY, DrawingTool tool) {
        int height = this.getHeight();
        int width = this.getWidth();
        int x = (int) (mouseX * (1d * this.canvas.getImage().getWidth() / width));
        int y = (int) (mouseY * (1d * this.canvas.getImage().getHeight() / height));

        tool.setPosition(DrawingTool.Position.of(x, y));

        return tool;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.repaint();
    }
}
