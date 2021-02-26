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
package org.pixel.seven.recognizer;

import org.pixel.seven.recognizer.drawing.DrawingTablet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @project pixel-seven-recognizer
 * @created 18.02.2021 18:48
 * <p>
 * @author Alexander A. Kropotin
 */
public class MainFrame extends JFrame {

    public void init(DrawingTablet drawing) {
        JToolBar toolbar = new  JToolBar("Toolbar", JToolBar.VERTICAL);
        toolbar.setBounds(0, 0, 100, 600);
        drawing.setBounds(100,0,700,600);
        drawing.setBackground(Color.BLACK);
        //drawing.setOpaque(true);
        //drawing.setSize(600, 600);

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
