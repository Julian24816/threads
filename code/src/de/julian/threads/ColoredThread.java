package de.julian.threads;

import java.awt.*;

/**
 * represents a thread running through a bracelet.
 * This is mutable for easy color changes without re-computation of knot arrangement.
 */
class ColoredThread {
    private Color color;

    ColoredThread(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
