package de.julian.threads;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Visualizer visualizer = new BasicVisualizer();
        visualizer.setBracelet(new DummyBracelet());
        frame.getContentPane().add(visualizer);

        frame.pack();
        frame.setVisible(true);
    }
}
