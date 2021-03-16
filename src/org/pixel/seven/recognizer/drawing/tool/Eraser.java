package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

import java.awt.*;

/**
 * The type Eraser.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 01.03.2021 15:22 <p>
 */
public class Eraser extends AbstractDrawingTool {

    /**
     * Instantiates a new Eraser.
     *
     * @param size the size
     */
    public Eraser(float size) {
        this.size = size;
    }

    /**
     * Apply.
     *
     * @param surface the surface
     */
    @Override
    public void apply(DrawingSurface surface) {
        Graphics2D graphics = (Graphics2D) surface.getImage().getGraphics();
        graphics.setStroke(new BasicStroke(this.size));
        graphics.setColor(surface.getBackground());
        graphics.drawLine(
                this.position.getX(),
                this.position.getY(),
                this.position.getX(),
                this.position.getY()
        );
    }
}
