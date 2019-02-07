package de.julian.threads;

import java.awt.Color;

/**
 * has the color, that was set during object construction
 */
public class DummyKnot implements Knot {
    private final Color color;

    DummyKnot(Color color) {
        super();
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
