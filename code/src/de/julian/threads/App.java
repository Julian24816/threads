package de.julian.threads;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final Pattern pattern = PatternFactory.forString(">\\\\\\///\n\\\\\\///<");
        final ColoredThread[] threads = ColoredThread.forColors(
                Color.YELLOW, Color.WHITE, Color.GREEN, Color.BLACK,
                Color.WHITE, Color.WHITE, Color.GREEN, Color.YELLOW);

        final BasicBracelet bracelet = new BasicBracelet(pattern, threads);
        final BasicVisualizer visualizer = new BasicVisualizer(bracelet);
        final BraceletVisualizerComponent braceletVisualizerComponent = new BraceletVisualizerComponent(visualizer);
        frame.getContentPane().add(braceletVisualizerComponent);

        frame.pack();
        frame.setVisible(true);
    }
}
