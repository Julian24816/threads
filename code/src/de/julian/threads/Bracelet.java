package de.julian.threads;

interface Bracelet {
    int getColumns();
    int getRows();
    Knot getKnot(int row, int column);
}
