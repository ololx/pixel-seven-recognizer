package org.pixel.seven.recognizer.drawing;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;
import org.pixel.seven.recognizer.drawing.tool.DrawingTool;

/**
 * The interface Drawing tablet.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 08.03.2021 09:32 <p>
 */
public interface DrawingTablet {

    /**
     * Apply tool.
     */
    void applyTool();

    /**
     * Sets position.
     *
     * @param mouseX the mouse x
     * @param mouseY the mouse y
     */
    void setPosition(int mouseX, int mouseY);

    /**
     * Gets surface.
     *
     * @return the surface
     */
    DrawingSurface getSurface();

    /**
     * Sets surface.
     *
     * @param surface the surface
     */
    void setSurface(DrawingSurface surface);

    /**
     * Gets tool.
     *
     * @return the tool
     */
    DrawingTool getTool();

    /**
     * Sets tool.
     *
     * @param tool the tool
     */
    void setTool(DrawingTool tool);

    /**
     * Sets cursor.
     */
    void setupCursor();
}
