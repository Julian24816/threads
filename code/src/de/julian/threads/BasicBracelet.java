package de.julian.threads;

import de.julian.threads.knots.KnotFactory;

import java.util.LinkedList;

public class BasicBracelet implements Bracelet {
    private final Pattern pattern;
    private final ColoredThread[] startingThreadArrangement;

    private final ColoredThread[] currentThreadArrangement;
    private final LinkedList<Row> computedRows = new LinkedList<>();

    public BasicBracelet(Pattern pattern, ColoredThread[] threads) {
        assert threads.length == pattern.getColumns() + 1 : "incorrect number of threads for this pattern";
        this.pattern = pattern;
        this.startingThreadArrangement = threads;
        this.currentThreadArrangement = threads.clone();
    }

    @Override
    public int getColumns() {
        return pattern.getColumns();
    }

    @Override
    public int getRows() {
        return pattern.getRows();
    }

    @Override
    public Knot getKnot(int row, int column) {
        if (row > computedRows.size() - 1) computeRowsUntil(row);
        return computedRows.get(row).knots[column];
    }

    @Override
    public String getPatternDescription() {
        return pattern.toString();
    }

    @Override
    public ColoredThread[] getThreads() {
        return startingThreadArrangement;
    }

    private void computeRowsUntil(int maxRow) {
        final int currentRow = computedRows.size() - 1;
        for (int row = currentRow + 1; row <= maxRow; row++)
            computeNextRow(row);
    }

    private void computeNextRow(int row) {
        final Row knotRow = new Row(pattern.getColumns());
        computedRows.add(knotRow);

        // compute even knots first, because odd knots use the resulting thread arrangement
        for (int column = 0; column < pattern.getColumns(); column += 2) computeKnot(knotRow, row, column);
        for (int column = 1; column < pattern.getColumns(); column += 2) computeKnot(knotRow, row, column);
    }

    private void computeKnot(Row knotRow, int row, int column) {
        knotRow.knots[column] = KnotFactory.get(pattern.getKnotType(row, column),
                currentThreadArrangement[column], currentThreadArrangement[column + 1]);
        // update current thread arrangement
        currentThreadArrangement[column] = knotRow.knots[column].getLeftEmergingThread();
        currentThreadArrangement[column + 1] = knotRow.knots[column].getRightEmergingThread();
    }

    private static class Row {
        final int columns;
        final Knot[] knots;

        private Row(int columns) {
            this.columns = columns;
            knots = new Knot[columns];
        }
    }
}
