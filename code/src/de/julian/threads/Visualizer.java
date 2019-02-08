package de.julian.threads;

import java.awt.*;

public abstract class Visualizer {

    private static final int MARGIN = 10;

    private int width, height;

    private int knotWidth = 10;
    private int knotHeight = 15;
    private int horizontalSpacing = 8;
    private int verticalSpacing = 14;
    private int oddColumnOffset = 6; // every second column is offset down by this amount

    private Bracelet bracelet;
    private Graphics g;
    private int offsetX, offsetY;

    Visualizer(Bracelet bracelet) {
        this.bracelet = bracelet;
        width = knotWidth + horizontalSpacing * (bracelet.getColumns() - 1) + 2 * MARGIN;
        height = knotHeight + verticalSpacing * (bracelet.getRows() - 1) + oddColumnOffset + 2 * MARGIN;
    }

    final int getWidth() {
        return width;
    }

    final int getHeight() {
        return height;
    }

    final void paintOn(Graphics g, int offsetX, int offsetY) {
        this.g = g;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        drawBracelet();
    }

    private void drawBracelet() {
        final int rows = bracelet.getRows();
        for (int row = 0; row < rows; row++)
            drawRow(row);
    }

    private void drawRow(int row) {
        final int columns = bracelet.getColumns();
        for (int column = 0; column < columns; column++)
            drawKnot(row, column);
    }

    private void drawKnot(int row, int column) {
        final int x = offsetX + MARGIN + horizontalSpacing * column;
        final int y = offsetY + MARGIN + verticalSpacing * row + oddColumnOffset * (column % 2);
        final Knot knot = bracelet.getKnot(row, column);
        drawKnot(g, x, y, knotWidth, knotHeight, knot);
    }

    protected abstract void drawKnot(Graphics g, int x, int y, int width, int height, Knot knot);
}
