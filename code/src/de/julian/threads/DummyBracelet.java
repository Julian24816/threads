package de.julian.threads;

import java.awt.*;

/**
 * consists of 7 columns with orange knots
 */
class DummyBracelet implements Bracelet {
    @Override
    public int getColumns() {
        return 7;
    }

    @Override
    public Knot getKnot(int row, int column) {
        return new DummyKnot(Color.ORANGE);
    }
}
