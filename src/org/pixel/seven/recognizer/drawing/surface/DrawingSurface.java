package org.pixel.seven.recognizer.drawing.surface;

import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 26.02.2021 15:24
 * <p>
 * @author Alexander A. Kropotin
 */
public interface DrawingSurface {

    BufferedImage getImage();

    int getBackground();

    default int getXScaled(int x, int width) {
        return (int) (x * getWidthScaling(width));
    }

    default int getYScaled(int y, int height) {
        return (int) (y * getHeigthScale(height));
    }

    default double getWidthScaling(int width) {
        return 1d * this.getImage().getWidth() / width;
    }

    default double getHeigthScale(int height) {
        return 1d * this.getImage().getHeight() / height;
    }
}
