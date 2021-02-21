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
package org.pixel.seven.recognizer.image;

import org.pixel.seven.recognizer.image.processor.ImageProcessor;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 21:53
 * <p>
 * @author Alexander A. Kropotin
 */
public class DigitBufferedImage implements DigitImage {

    public static final int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_BYTE_BINARY;

    public static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK.getRGB();

    private BufferedImage image;

    public DigitBufferedImage(BufferedImage image) {
        Objects.requireNonNull(image);
        this.image = image;
    }

    public DigitBufferedImage process(ImageProcessor processor) {
        this.image = processor.apply(this.image);

        return this;
    }

    public int[] getImageRGB() {
        int[] pixels = new int[this.image.getWidth() * this.image.getHeight()];

        return this.image.getRGB(0, 0, this.image.getWidth(), this.image.getHeight(), pixels, 0, this.image.getWidth());
    }
}
