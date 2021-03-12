package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingPanel;
import org.pixel.seven.recognizer.drawing.DrawingTablet;
import org.pixel.seven.recognizer.recognition.Recognizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.util.Map;

/**
 * @project pixel-seven-recognizer
 * @created 18.02.2021 18:48
 * <p>
 * @author Alexander A. Kropotin
 */
public class MainFrame extends JFrame {

    private final JPanel drawingTablet;

    public MainFrame(JPanel drawingTablet) {
        this.drawingTablet = drawingTablet;
        this.drawingTablet.setBounds(0,0,1024,1024);
        this.drawingTablet.setBackground(Color.BLACK);
        this.drawingTablet.setOpaque(true);
        addComponentListener(new  ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                drawingTablet.setSize(getWidth(), getHeight());
            }
        });

        add(this.drawingTablet);
    }

    public void init() {
        setSize(this.drawingTablet.getWidth(), this.drawingTablet.getHeight());
        setResizable(true);
        setTitle("Recognizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}
