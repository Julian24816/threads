package de.julian.threads;

import de.julian.threads.knot.KnotType;

public interface Pattern {
    int getColumns();

    int getRows();

    KnotType getKnotType(int row, int column);
}
