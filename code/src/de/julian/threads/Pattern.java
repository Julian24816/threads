package de.julian.threads;

import de.julian.threads.knots.KnotType;

public interface Pattern {
    int getColumns();

    int getRows();

    KnotType getKnotType(int row, int column);
}
