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

    private final DrawingTablet drawingTablet;

    private Recognizer recognizer;

    public MainFrame(DrawingTablet drawingTablet) {
        this.drawingTablet = drawingTablet;
    }

    public void init(DrawingPanel drawing) {
        drawing.setBounds(0,0,1024,1024);
        drawing.setBackground(Color.BLACK);
        drawing.setOpaque(true);

        setSize(drawing.getWidth(), drawing.getHeight());
        setResizable(true);
        setTitle("Recognizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        add(drawing);

        addComponentListener(new  ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                drawing.setSize(getWidth(), getHeight());
            }
        });
    }
}
