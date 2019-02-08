package de.julian.threads;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

class BasicBracelet implements Bracelet {
    private final Pattern pattern;

    private final ColoredThread[] currentThreadArrangement;
    private final LinkedList<Row> computedRows = new LinkedList<>();

    BasicBracelet(Pattern pattern, ColoredThread[] threads) {
        assert threads.length == pattern.getColumns() + 1 : "incorrect number of threads for this pattern";
        this.pattern = pattern;
        this.currentThreadArrangement = threads;
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
        assert knotRow.complete();
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

        private boolean complete() {
            return Arrays.stream(knots).noneMatch(Objects::isNull);
        }
    }
}
