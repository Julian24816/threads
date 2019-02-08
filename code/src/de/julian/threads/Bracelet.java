package de.julian.threads;

public interface Bracelet {
    int getColumns();
    int getRows();
    Knot getKnot(int row, int column);
    String getPatternDescription();
    ColoredThread[] getThreads();
}
