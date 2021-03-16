package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Filling.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 01.03.2021 09:46 <p>
 */
public class Filling extends AbstractColoringTool {

    /**
     * Instantiates a new Pencil.
     */
    public Filling() {
        this(1f, Color.BLACK.getRGB());
    }

    /**
     * Instantiates a new Pencil.
     *
     * @param size  the size
     * @param color the color
     */
    public Filling(float size, int color) {
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
        Graphics2D graphics = (Graphics2D) surface.getImage().getGraphics();
        graphics.setStroke(new BasicStroke(this.size));
        graphics.setColor(new Color(this.color));

        this.fill(
                graphics,
                this.position.getX(),
                0,
                surface.getImage().getWidth() - 1,
                this.position.getY(),
                0,
                surface.getImage().getHeight() - 1,
                surface.getImage(),
                surface.getImage().getRGB(this.position.getX(), this.position.getY())
        );
    }

    /**
     * Fill.
     *
     * @param graphics the graphics
     * @param x        the x
     * @param minX     the min x
     * @param maxX     the max x
     * @param y        the y
     * @param minY     the min y
     * @param maxY     the max y
     * @param image    the image
     * @param bclr     the bclr
     */
    private void fill(Graphics2D graphics, int x, int minX, int maxX, int y, int minY, int maxY, BufferedImage image, int bclr) {
        if (x < minX || x > maxX || y < minY || y > maxY) return;
        else if (image.getRGB(x, y) == graphics.getColor().getRGB()) return;
        else if (image.getRGB(x, y) != bclr) return;

        graphics.drawLine(x, y, x, y);

        this.fill(
                graphics,
                x - 1,
                minX,
                maxX,
                y,
                minY,
                maxY,
                image,
                bclr
        );

        this.fill(
                graphics,
                x,
                minX,
                maxX,
                y - 1,
                minY,
                maxY,
                image,
                bclr
        );

        this.fill(
                graphics,
                x + 1,
                minX,
                maxX,
                y,
                minY,
                maxY,
                image,
                bclr
        );

        this.fill(
                graphics,
                x,
                minX,
                maxX,
                y + 1,
                minY,
                maxY,
                image,
                bclr
        );
    }
}
