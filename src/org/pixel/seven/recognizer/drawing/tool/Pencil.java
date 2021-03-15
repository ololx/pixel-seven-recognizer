package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

import java.awt.*;

/**
 * The type Pencil.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 26.02.2021 15:40 <p>
 */
public class Pencil extends AbstractColoringTool {

    /**
     * Instantiates a new Pencil.
     */
    public Pencil() {
        this(1f, Color.BLACK.getRGB());
    }

    /**
     * Instantiates a new Pencil.
     *
     * @param size    the size
     * @param minSuze the min suze
     * @param maxSize the max size
     * @param color   the color
     */
    public Pencil(float size, float minSuze, float maxSize, int color) {
        this(size, color);
        this.minSize = minSuze;
        this.maxSize = maxSize;
    }

    /**
     * Instantiates a new Pencil.
     *
     * @param size  the size
     * @param color the color
     */
    public Pencil(float size, int color) {
        this.setSize(size);
        this.setColor(color);
        this.setPosition(new Position());
    }

    /**
     * Apply.
     *
     * @param surface the surface
     */
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
}
