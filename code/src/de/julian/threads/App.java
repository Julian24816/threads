package de.julian.threads;

import javax.swing.*;
import java.awt.*;

public class App {
    private App(Visualizer visualizer) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new BraceletVisualizerComponent(visualizer));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final Pattern pattern = PatternFactory.forString(">\\\\\\///\n\\\\\\///<");
        final ColoredThread[] threads = ColoredThread.forColors(
                Color.YELLOW, Color.WHITE, Color.GREEN, Color.BLACK,
                Color.WHITE, Color.WHITE, Color.GREEN, Color.YELLOW);

        new App(new BasicVisualizer(new BasicBracelet(pattern, threads)));
    }
}
