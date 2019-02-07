package de.julian.threads;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Visualizer visualizer = new BasicVisualizer();
        visualizer.setBracelet(new BasicBracelet(PatternFactory.forString("\\\\\\\\///"), getThreads()));
        frame.getContentPane().add(visualizer);

        frame.pack();
        frame.setVisible(true);
    }

    private static ColoredThread[] getThreads() {
        return new ColoredThread[]{
                new ColoredThread(Color.ORANGE),
                new ColoredThread(Color.CYAN),
                new ColoredThread(Color.BLUE),
                new ColoredThread(Color.MAGENTA),
                new ColoredThread(Color.MAGENTA),
                new ColoredThread(Color.BLUE),
                new ColoredThread(Color.CYAN),
                new ColoredThread(Color.ORANGE)
        };
    }
}
