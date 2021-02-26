package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

import java.awt.*;

/**
 * @project pixel-seven-recognizer
 * @created 26.02.2021 15:40
 * <p>
 * @author Alexander A. Kropotin
 */
public class Pencil extends AbstractDrawingTool implements ColoringTool {

    private int color;

    public Pencil() {
        this(1f, Color.BLACK);
    }

    public Pencil(float size, Color color) {
        this.setSize(size);
        this.setColor(color);
        this.setPosition(new Position());
    }

    @Override
    public void apply(DrawingSurface surface) {
        Graphics2D graphics = (Graphics2D) surface.getImage() .getGraphics();
        graphics.setStroke(new BasicStroke(this.size));
        graphics.setColor(new Color(this.color));
        graphics.drawLine(
                this.position.getX(),
                this.position.getY(),
                this.position.getX(),
                this.position.getY()
        );
    }

    public void setColor(Color color) {
        this.setColor(color.getRGB());
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }
}
