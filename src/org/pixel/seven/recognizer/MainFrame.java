package org.pixel.seven.recognizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @project pixel-seven-recognizer
 * @created 18.02.2021 18:48
 * <p>
 * @author Alexander A. Kropotin
 */
public class MainFrame extends JFrame {

    public static class Builder {

        private String title;

        private Dimension size;

        private List<JComponent> childs;

        {
            this.childs = new ArrayList<>();
            this.size = new Dimension(0, 0);
        }

        public Builder title(String title) {
            Objects.requireNonNull(title, "The title couldn't be null");
            this.title = title;

            return this;
        }

        public Builder child(JComponent component, int w, int h) {
            return this.child(component, 0, 0, w, h);
        }

        public Builder child(JComponent component, int x, int y, int w, int h) {
            component.setBounds(x,y,w,h);
            return this.child(component);
        }

        public Builder child(JComponent component) {
            this.setSize(component);

            component.setOpaque(true);
            this.childs.add(component);

            return this;
        }

        public MainFrame build() {
            MainFrame main = new MainFrame();
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setResizable(true);
            main.setLayout(null);

            main.setSize(this.size);

            if (title != null) main.setTitle(title);

            childs.forEach(child -> main.add(child));

            main.addComponentListener(new  ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    childs.forEach(child -> child.setSize(main.getWidth(), main.getHeight()));
                }
            });

            return main;
        }

        private void setSize(JComponent component) {
            int boundsWidth = component.getX() + component.getWidth();
            int boundsHeight = component.getY() + component.getHeight();

            this.size.setSize(
                    this.size.getWidth() < boundsWidth ? boundsWidth : this.size.getWidth(),
                    this.size.getHeight() < boundsHeight ? boundsHeight : this.size.getHeight()
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public MainFrame() {
    }

    public void init() {
        setVisible(true);
    }
}
