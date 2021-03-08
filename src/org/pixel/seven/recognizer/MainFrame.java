package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @project pixel-seven-recognizer
 * @created 18.02.2021 18:48
 * <p>
 * @author Alexander A. Kropotin
 */
public class MainFrame extends JFrame {

    public void init(DrawingPanel drawing) {
        JToolBar toolbar = new  JToolBar("Toolbar", JToolBar.VERTICAL);
        toolbar.setBounds(0, 26, 26, 600);

        JButton pencil = new  JButton(new  ImageIcon("src/resources/pencil.png"));
        pencil.setBounds(0, 0, 25, 25);
        pencil.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                drawing.setTool(DrawingPanel.TOOLS.get("pencil"));
            }
        });
        toolbar.add(pencil);

        JButton filling = new  JButton(new  ImageIcon("src/resources/filling.png"));
        filling.setBounds(0, 0, 25, 25);
        filling.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                drawing.setTool(DrawingPanel.TOOLS.get("filling"));
            }
        });
        toolbar.add(filling);

        JButton eraser = new  JButton(new  ImageIcon("src/resources/eraser.png"));
        eraser.setBounds(0, 0, 25, 25);
        eraser.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                drawing.setTool(DrawingPanel.TOOLS.get("eraser"));
            }
        });
        toolbar.add(eraser);

        JPanel menu = new JPanel();
        menu.setBounds(0, 0, 1024, 26);
        JButton retrain = new  JButton("Обучить");
        retrain.setBounds(0, 0, 50, 26);
        retrain.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFileChooser fc = new  JFileChooser();
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    System.out.println("Opening: " + file.getName() + ".");
                } else {
                    System.out.println("Open command cancelled by user.");
                }

                JFileChooser jf = new  JFileChooser();
                jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jf.show();
                File[] input = jf.getSelectedFiles();
                for (File file : input) {
                    System.out.println(file.getAbsoluteFile());
                }
            }
        });
        menu.add(retrain);

        drawing.setBounds(26,26,972,972);
        drawing.setBackground(Color.BLACK);
        drawing.setOpaque(true);

        setSize(1024, 1024);
        setResizable(true);
        setTitle("Recognizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        add(toolbar);
        add(menu);
        add(drawing);

        addComponentListener(new  ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                drawing.setSize(getWidth() - (26 * 2), getHeight() - (26 * 2));
            }
        });
    }
}
