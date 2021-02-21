package org.pixel.seven.recognizer.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 21.02.2021 14:15
 * <p>
 * @author Alexander A. Kropotin
 */
public interface ImageUtils {

    static int[] cutEmpty(BufferedImage image) {
        int minX = Integer.MAX_VALUE,
                maxX = 0,
                minY = Integer.MAX_VALUE,
                maxY = 0;

        for (int x = 0; x < image.getWidth(); x ++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) != Color.BLACK.getRGB()) {
                    if (minX > x) minX = x;
                    if (maxX < x) maxX = x;
                    if (minY > y) minY = y;
                    if (maxY < y) maxY = y;
                }
            }
        }

        //System.err.println(minX + "|" + maxX + "|" + minY + "|" + maxY);

        BufferedImage resized = new BufferedImage(28, 28, image.getType());
        Graphics2D g = resized.createGraphics();
        BufferedImage subImage = image.getSubimage(minX, minY, maxX - minX, maxY - minY);
        g.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_BASE);
        g.drawImage(subImage, 0, 0, resized.getWidth(), resized.getHeight(), 0, 0, subImage.getWidth(),
                subImage.getHeight(), null);
        g.dispose();

        int[] pixels = new int[28 * 28];

        return resized.getRGB(0, 0, 28, 28, pixels, 0, 28);
    }
}
