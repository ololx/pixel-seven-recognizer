/**
 * Copyright 2021 the project pixel-seven-recognizer authors
 * and the original author or authors annotated by {@author}
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pixel.seven.recognizer.image.processor;

import org.pixel.seven.recognizer.image.DigitBufferedImage;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 22:56
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitProcessor<I extends BufferedImage> implements ImageProcessor<I> {

    private class Coordinates {

        int minX = Integer.MAX_VALUE;

        int maxX = 0;

        int minY = Integer.MAX_VALUE;

        int maxY = 0;
    }

    private int backgroundColor;

    public DigitProcessor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public I apply(I image) {
        return this.getSubImage(image);
    }

    private Coordinates getImageCoordinates(I image) {
        Coordinates coordinates = new Coordinates();
        for (int currentX = 0; currentX < image.getWidth(); currentX++) {
            for (int currentY = 0; currentY < image.getHeight(); currentY++) {
                if (image.getRGB(currentX, currentY) == backgroundColor) continue;

                if (coordinates.minX > currentX) coordinates.minX = currentX;
                if (coordinates.maxX < currentX) coordinates.maxX = currentX;
                if (coordinates.minY > currentY) coordinates.minY = currentY;
                if (coordinates.maxY < currentY) coordinates.maxY = currentY;
            }
        }

        return coordinates;
    }

    private I getSubImage(I image) {
        Coordinates subImageCoordinates = this.getImageCoordinates(image);
        if (subImageCoordinates.minX == 0
                || subImageCoordinates.minY == 0
                || subImageCoordinates.maxX == image.getWidth()
                || subImageCoordinates.maxY == image.getHeight()) return image;

        BufferedImage resized = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = resized.createGraphics();
        BufferedImage subImage = image.getSubimage(
                subImageCoordinates.minX,
                subImageCoordinates.minY,
                subImageCoordinates.maxX - subImageCoordinates.minX,
                subImageCoordinates.maxY - subImageCoordinates.minY
        );
        g.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_BASE);
        g.drawImage(
                subImage,
                0, 0, resized.getWidth(), resized.getHeight(),
                0, 0, subImage.getWidth(), subImage.getHeight(),
                null
        );
        g.dispose();

        return (I) resized;
    }
}
