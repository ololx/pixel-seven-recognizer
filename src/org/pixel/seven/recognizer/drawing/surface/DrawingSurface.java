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

    default int getXScaling(int width) {
        return (int) (1d * this.getImage().getWidth() / width);
    }

    default int getYScaling(int height) {
        return (int) (1d * this.getImage().getHeight() / height);
    }
}
