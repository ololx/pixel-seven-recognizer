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

import org.pixel.seven.recognizer.drawing.DrawingFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @project pixel-seven-recognizer
 * @created 18.02.2021 18:48
 * <p>
 * @author Alexander A. Kropotin
 */
public class MainFrame extends JFrame {

    public void init() {
        setSize(1366, 768);
        setResizable(true);

        setTitle("Recognizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);

        setVisible(true);

        DrawingFrame drawing = new DrawingFrame(28, 28);
        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        drawing.init();

        JLabel question = new JLabel("Please draw the 7");
        question.setBackground(Color.BLUE);

        JLabel answer = new JLabel("(x)(y)");
        answer.setBackground(Color.BLUE);

        JButton yesButton = new JButton("Yes");
        yesButton.setBackground(Color.GREEN);

        JButton noButton = new JButton("No");
        noButton.setBackground(Color.RED);

        mainPanel.add(question);
        mainPanel.add(drawing);
        mainPanel.add(answer);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }
}
