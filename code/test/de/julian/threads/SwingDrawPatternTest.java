package de.julian.threads;

import javax.swing.*;
import java.awt.*;

/**
 * drawing a simple arrow head pattern using constants for computing knot positions
 */
public class SwingDrawPatternTest extends JComponent {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int[] UPPER_LEFT_CORNER = {70, 10};

    private static final int KNOT_WIDTH = 10;
    private static final int KNOT_HEIGHT = 15;
    private static final int KNOT_HORIZONTAL_COLUMN_OFFSET = 8;
    private static final int KNOT_VERTICAL_COLUMN_OFFSET = 6;
    private static final int[] VERTICAL_OFFSET_PER_COLUMN = {0, 1, 2, 3, 2, 1, 0};
    private static final int KNOT_VERTICAL_ROW_OFFSET = 14;

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final SwingDrawPatternTest drawing = new SwingDrawPatternTest();
        drawing.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        testFrame.getContentPane().add(drawing);
        testFrame.pack();
        testFrame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Color[] colors = {Color.ORANGE, Color.BLUE, Color.GREEN, Color.CYAN, Color.RED,
                Color.MAGENTA, Color.WHITE, Color.YELLOW, Color.PINK};
        for (int row = 0; row < colors.length; row++)
            for (int column = 0; column < VERTICAL_OFFSET_PER_COLUMN.length; column++)
                drawKnot(g, row, column, colors[row]);
    }

    private void drawKnot(Graphics g, int row, int column, Color color) {
        g.setColor(color);
        g.fillOval(
                UPPER_LEFT_CORNER[0]
                        + KNOT_HORIZONTAL_COLUMN_OFFSET * column,
                UPPER_LEFT_CORNER[1]
                        + KNOT_VERTICAL_COLUMN_OFFSET * VERTICAL_OFFSET_PER_COLUMN[column]
                        + KNOT_VERTICAL_ROW_OFFSET * row,
                KNOT_WIDTH, KNOT_HEIGHT);
    }
}
