package de.julian.threads;

import java.awt.*;

public class ColoredThread {
    private Color color;

    private ColoredThread(Color color) {
        this.color = color;
    }

    public static ColoredThread[] forColors(Color... colors) {
        final ColoredThread[] threads = new ColoredThread[colors.length];
        for (int i = 0; i < colors.length; i++)
            threads[i] = new ColoredThread(colors[i]);
        return threads;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }
}
