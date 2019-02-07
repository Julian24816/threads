package de.julian.threads;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * inspired by https://stackoverflow.com/a/5802781/5509966
 */
public class SwingLinesTest extends JComponent {

    private static final int WIDTH = 320;
    private static final int HEIGHT = 200;

    private static class Line {
        final int x1, y1, x2, y2;
        final Color color;

        private Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private final LinkedList<Line> lines = new LinkedList<>();

    private void addLine(int x1, int y1, int x2, int y2, Color color) {
        lines.add(new Line(x1, y1, x2, y2, color));
        repaint();
    }

    private void clearLines() {
        lines.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final SwingLinesTest linesComponent = new SwingLinesTest();
        linesComponent.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        testFrame.getContentPane().add(linesComponent, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton newLineButton = new JButton("New Line");
        JButton clearButton = new JButton("Clear");
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        newLineButton.addActionListener(e -> {
            int x1 = (int) (Math.random() * WIDTH);
            int y1 = (int) (Math.random() * HEIGHT);
            int x2 = (int) (Math.random() * WIDTH);
            int y2 = (int) (Math.random() * HEIGHT);
            Color randomColor = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            linesComponent.addLine(x1, y1, x2, y2, randomColor);
        });
        clearButton.addActionListener(e -> {
            linesComponent.clearLines();
        });

        testFrame.pack();
        testFrame.setVisible(true);
    }
}
