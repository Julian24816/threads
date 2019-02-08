package de.julian.threads.app;

import de.julian.threads.BasicBracelet;
import de.julian.threads.ColoredThread;
import de.julian.threads.Pattern;
import de.julian.threads.Visualizer;
import de.julian.threads.patterns.PatternFactory;
import de.julian.threads.visualizer.BasicVisualizer;

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
