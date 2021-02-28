package org.pixel.seven.recognizer.drawing.tool;

/**
 * The type Abstract coloring tool.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 28.02.2021 12:11 <p>
 */
public abstract class AbstractColoringTool extends AbstractDrawingTool implements ColoringTool {

    /**
     * The Color.
     */
    protected int color;

    /**
     * Sets color.
     *
     * @param color the color
     */
    @Override
    public void setColor(int color) {
        this.color = color;
    }
}
