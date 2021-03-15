package org.pixel.seven.recognizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Main frame.
 *
 * @author Alexander A. Kropotin
 * @project pixel -seven-recognizer
 * @created 18.02.2021 18:48 <p>
 */
public class MainFrame extends JFrame {

    /**
     * The type Builder.
     */
    public static class Builder {

        /**
         * The Title.
         */
        private String title;

        /**
         * The Size.
         */
        private Dimension size;

        /**
         * The Childs.
         */
        private List<JComponent> childs;

        {
            this.childs = new ArrayList<>();
            this.size = new Dimension(0, 0);
        }

        /**
         * Title builder.
         *
         * @param title the title
         * @return the builder
         */
        public Builder title(String title) {
            Objects.requireNonNull(title, "The title couldn't be null");
            this.title = title;

            return this;
        }

        /**
         * Child builder.
         *
         * @param component the component
         * @param w         the w
         * @param h         the h
         * @return the builder
         */
        public Builder child(JComponent component, int w, int h) {
            return this.child(component, 0, 0, w, h);
        }

        /**
         * Child builder.
         *
         * @param component the component
         * @param x         the x
         * @param y         the y
         * @param w         the w
         * @param h         the h
         * @return the builder
         */
        public Builder child(JComponent component, int x, int y, int w, int h) {
            component.setBounds(x,y,w,h);
            return this.child(component);
        }

        /**
         * Child builder.
         *
         * @param component the component
         * @return the builder
         */
        public Builder child(JComponent component) {
            this.setSize(component);

            component.setOpaque(true);
            this.childs.add(component);

            return this;
        }

        /**
         * Build main frame.
         *
         * @return the main frame
         */
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

        /**
         * Sets size.
         *
         * @param component the component
         */
        private void setSize(JComponent component) {
            int boundsWidth = component.getX() + component.getWidth();
            int boundsHeight = component.getY() + component.getHeight();

            this.size.setSize(
                    this.size.getWidth() < boundsWidth ? boundsWidth : this.size.getWidth(),
                    this.size.getHeight() < boundsHeight ? boundsHeight : this.size.getHeight()
            );
        }
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Init.
     */
    public void init() {
        SwingUtilities.invokeLater(new  Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }
}
