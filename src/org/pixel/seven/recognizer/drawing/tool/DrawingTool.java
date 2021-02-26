package org.pixel.seven.recognizer.drawing.tool;

import org.pixel.seven.recognizer.drawing.surface.DrawingSurface;

/**
 * @project pixel-seven-recognizer
 * @created 26.02.2021 15:26
 * <p>
 * @author Alexander A. Kropotin
 */
public interface DrawingTool {

    class Position {

        public static Position of(int x, int y) {
            return new Position(x, y);
        }

        private int x;

        private int y;

        public Position() {
            this(0, 0);
        }

        public Position(int x, int y) {
            this.setX(x);
            this.setY(y);
        }

        public int getX() {
            return this.x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    void setPosition(Position position);

    void setSize(float size);

    void apply(DrawingSurface surface);
}
