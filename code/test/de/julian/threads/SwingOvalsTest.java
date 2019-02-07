package de.julian.threads;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * inspired by https://stackoverflow.com/a/5802781/5509966
 */
public class SwingOvalsTest extends JComponent {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 200;
    private final LinkedList<Oval> ovals = new LinkedList<>();

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final SwingOvalsTest linesComponent = new SwingOvalsTest();
        linesComponent.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        testFrame.getContentPane().add(linesComponent, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton newLineButton = new JButton("New Oval");
        JButton clearButton = new JButton("Clear");
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        newLineButton.addActionListener(e -> {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int width = (int) (Math.random() * WIDTH / 10);
            int heigth = (int) (Math.random() * HEIGHT / 10);
            Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            linesComponent.addOval(x, y, width, heigth, randomColor);
        });
        clearButton.addActionListener(e -> {
            linesComponent.clearOvals();
        });

        testFrame.pack();
        testFrame.setVisible(true);
    }

    private void addOval(int x, int y, int width, int height, Color color) {
        ovals.add(new Oval(x, y, width, height, color));
        repaint();
    }

    private void clearOvals() {
        ovals.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Oval oval : ovals) {
            g.setColor(oval.color);
            g.drawOval(oval.x, oval.y, oval.width, oval.height);
        }
    }

    private static class Oval {
        final int x, y, width, height;
        final Color color;

        private Oval(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }
    }
}
