package de.julian.threads;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * draws all knots as ovals in their color
 */
public class BasicVisualizer extends Visualizer {

    private static final int KNOT_WIDTH = 10;
    private static final int KNOT_HEIGHT = 15;
    private static final int HORIZONTAL_SPACING = 8;
    private static final int VERTICAL_SPACING = 14;
    private static final int EVEN_COLUMN_OFFSET = 6; // every second column is offset down by this amount

    private static final int ROWS = 30;
    private static final int MAX_COLUMNS = 11;
    private static final int MARGIN = 10;
    private static final int COMPONENT_WIDTH = KNOT_WIDTH + HORIZONTAL_SPACING * (MAX_COLUMNS - 1) + 2 * MARGIN;
    private static final int COMPONENT_HEIGHT = ROWS * KNOT_HEIGHT + 2 * MARGIN;

    private int[] upperLeftCorner = {70, MARGIN};
    private Bracelet bracelet;

    BasicVisualizer() {
        super();
        setPreferredSize(new Dimension(COMPONENT_WIDTH, COMPONENT_HEIGHT));
        recomputeUpperLeftCornerOnResize();
    }

    private void recomputeUpperLeftCornerOnResize() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                recomputeUpperLeftCorner();
            }
        });
    }

    private void recomputeUpperLeftCorner() {
        if (bracelet == null) return;
        upperLeftCorner[0] = (getSize().width - HORIZONTAL_SPACING * bracelet.getColumns() - KNOT_WIDTH / 2) / 2;
        assert upperLeftCorner[0] >= MARGIN : "bracelet too wide for this component-width";
    }

    @Override
    public void setBracelet(Bracelet bracelet) {
        this.bracelet = bracelet;
        recomputeUpperLeftCorner();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (bracelet == null) return;
        for (int row = 0; row < ROWS; row++)
            drawRow(g, row);
    }

    private void drawRow(Graphics g, int row) {
        final int columns = bracelet.getColumns();
        for (int column = 0; column < columns; column++)
            drawKnot(g, row, column);
    }

    private void drawKnot(Graphics g, int row, int column) {
        Knot knot = bracelet.getKnot(row, column);
        g.setColor(knot.getColor());
        g.fillOval(
                upperLeftCorner[0] + HORIZONTAL_SPACING * column,
                upperLeftCorner[1] + VERTICAL_SPACING * row + EVEN_COLUMN_OFFSET * (column % 2),
                KNOT_WIDTH, KNOT_HEIGHT
        );
    }
}