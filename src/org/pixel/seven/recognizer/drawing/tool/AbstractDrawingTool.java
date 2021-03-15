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
     * The Max size.
     */
    protected float maxSize = 1;

    /**
     * The Min size.
     */
    protected float minSize = 100;

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
     * Gets size.
     *
     * @return the size
     */
    @Override
    public float getSize() {
        return this.size;
    }

    /**
     * Change step.
     *
     * @param value the value
     */
    @Override
    public void changeSizeOn(float value) {
        this.setSize(this.getSize() + value);
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    @Override
    public void setSize(float size) {
        this.size = size >= this.minSize && size <= this.maxSize ? size : this.size;
    }
}
