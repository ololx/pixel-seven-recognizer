package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

/**
 * The interface Drawing tool.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 26.02.2021 15:26 <p>
 */
public interface DrawingTool {

    /**
     * The type Position.
     */
    class Position {

        /**
         * Of position.
         *
         * @param x the x
         * @param y the y
         * @return the position
         */
        public static Position of(int x, int y) {
            return new Position(x, y);
        }

        /**
         * The X.
         */
        private int x;

        /**
         * The Y.
         */
        private int y;

        /**
         * Instantiates a new Position.
         */
        public Position() {
            this(0, 0);
        }

        /**
         * Instantiates a new Position.
         *
         * @param x the x
         * @param y the y
         */
        public Position(int x, int y) {
            this.setX(x);
            this.setY(y);
        }

        /**
         * Gets x.
         *
         * @return the x
         */
        public int getX() {
            return this.x;
        }

        /**
         * Sets x.
         *
         * @param x the x
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Gets y.
         *
         * @return the y
         */
        public int getY() {
            return this.y;
        }

        /**
         * Sets y.
         *
         * @param y the y
         */
        public void setY(int y) {
            this.y = y;
        }
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    void setPosition(Position position);

    /**
     * Gets size.
     *
     * @return the size
     */
    float getSize();

    /**
     * Sets size.
     *
     * @param size the size
     */
    void setSize(float size);

    /**
     * Change value.
     *
     * @param value the value
     */
    void changeSizeOn(float value);

    /**
     * Apply.
     *
     * @param surface the surface
     */
    void apply(DrawingSurface surface);
}
