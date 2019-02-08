package de.julian.threads;

interface Pattern {
    int getColumns();
    int getRows();
    KnotType getKnotType(int row, int column);
}
