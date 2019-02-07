package de.julian.threads;

interface Pattern {
    int getColumns();
    KnotType getKnotType(int row, int column);
}
