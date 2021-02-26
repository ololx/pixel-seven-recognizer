package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.DrawingFrame;
import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

/**
 * @project pixel-seven-recognizer
 * @created 26.02.2021 16:05
 * <p>
 * @author Alexander A. Kropotin
 */
public abstract class AbstractDrawingTool implements DrawingTool {

    protected Position position;

    protected float size;

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setSize(float size) {
        this.size = size;
    }
}
