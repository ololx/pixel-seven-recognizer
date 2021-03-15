package org.pixel.seven.recognizer.drawing;

import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;
import org.pixel.seven.recognizer.drawing.tool.DrawingTool;
import org.pixel.seven.recognizer.drawing.tool.Eraser;
import org.pixel.seven.recognizer.drawing.tool.Filling;
import org.pixel.seven.recognizer.drawing.tool.Pencil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Drawing panel.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 22.02.2021 13:59 <p>
 */
public class DrawingPanel extends JPanel implements DrawingTablet {

    /**
     * The constant TOOLS.
     */
    public static final Map<String, DrawingTool> TOOLS = new HashMap<String, DrawingTool>() {{
        put("pencil", new Pencil(3f, Color.WHITE.getRGB()));
        put("eraser", new Eraser(100f));
        put("filling", new Filling(1f, Color.BLACK.getRGB()));
    }};

    /**
     * The Canvas.
     */
    private DrawingSurface canvas;

    /**
     * The Tool.
     */
    private DrawingTool tool;

    /**
     * Instantiates a new Drawing panel.
     *
     * @param width  the width
     * @param height the height
     */
    public DrawingPanel(int width, int height) {
        this.canvas = new Canvas(width, height, Color.BLACK);
        this.tool = new Pencil(3f, 2f, 5f, Color.WHITE.getRGB());
    }

    /**
     * Paint component.
     *
     * @param g the g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.canvas.getImage(), 0, 0, this.getWidth(), this.getHeight(),null);
        this.setupCursor();
    }

    /**
     * Apply tool.
     */
    @Override
    public void applyTool() {
        this.tool.apply(this.canvas);
        this.repaint();
    }

    /**
     * Sets position.
     *
     * @param mouseX the mouse x
     * @param mouseY the mouse y
     */
    @Override
    public void setPosition(int mouseX, int mouseY) {
        int x = this.canvas.getXScaled(mouseX, this.getWidth());
        int y = this.canvas.getYScaled(mouseY, this.getHeight());
        this.tool.setPosition(DrawingTool.Position.of(x, y));
    }

    /**
     * Gets surface.
     *
     * @return the surface
     */
    @Override
    public DrawingSurface getSurface() {
        return this.canvas;
    }

    /**
     * Sets surface.
     *
     * @param surface the surface
     */
    @Override
    public void setSurface(DrawingSurface surface) {
        this.canvas = surface;
        this.repaint();
    }

    /**
     * Gets tool.
     *
     * @return the tool
     */
    @Override
    public DrawingTool getTool() {
        return this.tool;
    }

    /**
     * Sets tool.
     *
     * @param tool the tool
     */
    @Override
    public void setTool(DrawingTool tool) {
        this.tool = tool;
    }

    /**
     * Sets cursor.
     */
    @Override
    public void setupCursor() {
        Dimension bestCursorSize = Toolkit.getDefaultToolkit().getBestCursorSize(
                (int) (this.getTool().getSize() / this.canvas.getXScaling(this.getWidth())),
                (int) (this.getTool().getSize() / this.canvas.getYScaling(this.getHeight()))
        );
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                this.getCursorIcon(bestCursorSize),
                new Point((bestCursorSize.width - 1) / 2, (bestCursorSize.height - 1) / 2),
                "tool")
        );
    }

    /**
     * Gets cursor icon.
     *
     * @param bestCursorSize the best cursor size
     * @return the cursor icon
     */
    private BufferedImage getCursorIcon(Dimension bestCursorSize) {
        BufferedImage icon = new BufferedImage(
                bestCursorSize.width,
                bestCursorSize.height,
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D graphics = (Graphics2D) icon.getGraphics();
        graphics.setColor(new Color(
                canvas.getBackground().getAlpha(),
                255 - canvas.getBackground().getRed(),
                255 - canvas.getBackground().getGreen(),
                255 - canvas.getBackground().getBlue()
        ));
        graphics.fillOval(
                0,
                0,
                icon.getWidth(),
                icon.getHeight()
        );

        return icon;
    }
}
