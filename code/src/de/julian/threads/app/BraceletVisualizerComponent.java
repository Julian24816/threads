package de.julian.threads.app;

import de.julian.threads.Visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class BraceletVisualizerComponent extends JComponent {
    private final Visualizer visualizer;
    private int offsetX;

    BraceletVisualizerComponent(Visualizer visualizer) {
        this.visualizer = visualizer;
        setPreferredSize(new Dimension(visualizer.getWidth(), visualizer.getHeight()));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                offsetX = computeOffsetXFromBraceletWidth();
            }
        });
    }

    private int computeOffsetXFromBraceletWidth() {
        return (getSize().width - visualizer.getWidth()) / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        visualizer.paintOn(g, offsetX, 0);
    }
}