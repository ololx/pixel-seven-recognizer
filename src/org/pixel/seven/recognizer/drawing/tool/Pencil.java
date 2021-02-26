package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

import java.awt.*;

/**
 * @project pixel-seven-recognizer
 * @created 26.02.2021 15:40
 * <p>
 * @author Alexander A. Kropotin
 */
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
