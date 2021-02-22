package org.pixel.seven.recognizer.drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @project pixel-seven-recognizer
 * @created 22.02.2021 13:59
 * <p>
 * @author Alexander A. Kropotin
 */
public class DrawingTablet extends JPanel {

    private BufferedImage image;

    public DrawingTablet(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void paintComponent (Graphics g) {
        Graphics2D graphics = this.image.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0,this);
    }
}
