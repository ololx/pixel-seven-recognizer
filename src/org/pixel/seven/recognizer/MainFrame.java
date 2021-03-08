package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @project pixel-seven-recognizer
 * @created 18.02.2021 18:48
 * <p>
 * @author Alexander A. Kropotin
 */
public class MainFrame extends JFrame {

    public void init(DrawingPanel drawing) {
        JToolBar toolbar = new  JToolBar("Toolbar", JToolBar.VERTICAL);
        toolbar.setBounds(0, 0, 25, 600);

        JButton pencil = new  JButton(new  ImageIcon("src/resources/pencil.png"));
        pencil.setBounds(0, 0, 25, 100);
        pencil.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                drawing.setTool(DrawingPanel.TOOLS.get("pencil"));
            }
        });
        toolbar.add(pencil);

        JButton filling = new  JButton(new  ImageIcon("src/resources/filling.png"));
        filling.setBounds(0, 0, 25, 100);
        filling.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                drawing.setTool(DrawingPanel.TOOLS.get("filling"));
            }
        });
        toolbar.add(filling);

        JButton eraser = new  JButton(new  ImageIcon("src/resources/eraser.png"));
        eraser.setBounds(0, 0, 25, 100);
        eraser.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                drawing.setTool(DrawingPanel.TOOLS.get("eraser"));
            }
        });
        toolbar.add(eraser);


        drawing.setBounds(25,0,625,600);
        drawing.setBackground(Color.BLACK);
        drawing.setOpaque(true);

        setSize(1024, 1024);
        setResizable(true);
        setTitle("Recognizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        add(toolbar);
        add(drawing);

        setBackground(Color.BLACK);
    }
}
