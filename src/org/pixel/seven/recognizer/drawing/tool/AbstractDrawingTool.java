package org.pixel.seven.recognizer.drawing.tool;

/**
 * The type Abstract drawing tool.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 26.02.2021 16:05 <p>
 */
public abstract class AbstractDrawingTool implements DrawingTool {

    /**
     * The Position.
     */
    protected Position position;

    /**
     * The Size.
     */
    protected float size;

    /**
     * Sets position.
     *
     * @param position the position
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    @Override
    public void setSize(float size) {
        this.size = size;
    }
}
