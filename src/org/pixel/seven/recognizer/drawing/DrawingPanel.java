package org.pixel.seven.recognizer.drawing;

import org.pixel.seven.recognizer.drawing.surface.Canvas;
import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;
import org.pixel.seven.recognizer.drawing.tool.DrawingTool;
import org.pixel.seven.recognizer.drawing.tool.Eraser;
import org.pixel.seven.recognizer.drawing.tool.Filling;
import org.pixel.seven.recognizer.drawing.tool.Pencil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @project pixel-seven-recognizer
 * @created 22.02.2021 13:59
 * <p>
 * @author Alexander A. Kropotin
 */
public class DrawingPanel extends JPanel implements DrawingTablet {

    public static final Map<String, DrawingTool> TOOLS = new HashMap<String, DrawingTool>() {{
        put("pencil", new Pencil(3f, Color.WHITE.getRGB()));
        put("eraser", new Eraser(100f));
        put("filling", new Filling(1f, Color.BLACK.getRGB()));
    }};

    private DrawingSurface canvas;

    private DrawingTool tool;

    public DrawingPanel(int width, int height) {
        this.canvas = new Canvas(width, height, Color.BLACK);
        this.tool = TOOLS.get("pencil");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.canvas.getImage(), 0, 0, this.getWidth(), this.getHeight(),null);
    }

    public void applyTool() {
        this.tool.apply(this.canvas);
        this.repaint();
    }

    public void applyTool(int mouseX, int mouseY, DrawingTool tool) {
        this.setTool(tool);
        this.setPosition(mouseX, mouseY);
        this.tool.apply(this.canvas);
        this.repaint();
    }

    public DrawingTool setPosition(int mouseX, int mouseY, DrawingTool tool) {
        int x = this.canvas.getXScaled(mouseX, this.getWidth());
        int y = this.canvas.getYScaled(mouseY, this.getHeight());
        tool.setPosition(DrawingTool.Position.of(x, y));

        return tool;
    }

    public void setPosition(int mouseX, int mouseY) {
        int x = this.canvas.getXScaled(mouseX, this.getWidth());
        int y = this.canvas.getYScaled(mouseY, this.getHeight());
        this.tool.setPosition(DrawingTool.Position.of(x, y));
    }

    @Override
    public DrawingSurface getSurface() {
        return this.canvas;
    }

    @Override
    public void setSurface(DrawingSurface surface) {
        this.canvas = surface;
        this.repaint();
    }

    @Override
    public DrawingTool getTool() {
        return this.tool;
    }

    @Override
    public void setTool(DrawingTool tool) {
        this.tool = tool;
    }
}
