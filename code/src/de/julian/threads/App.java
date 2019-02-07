package de.julian.threads;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Visualizer visualizer = new BasicVisualizer();
        visualizer.setBracelet(new BasicBracelet(PatternFactory.forString(">\\\\\\///\n\\\\\\///<"), getThreads()));
        frame.getContentPane().add(visualizer);

        frame.pack();
        frame.setVisible(true);
    }

    private static ColoredThread[] getThreads() {
        return new ColoredThread[]{
                new ColoredThread(Color.YELLOW),
                new ColoredThread(Color.WHITE),
                new ColoredThread(Color.GREEN),
                new ColoredThread(Color.BLACK),
                new ColoredThread(Color.WHITE),
                new ColoredThread(Color.WHITE),
                new ColoredThread(Color.GREEN),
                new ColoredThread(Color.YELLOW)
        };
    }
}
