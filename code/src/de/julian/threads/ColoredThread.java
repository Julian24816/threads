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

    public static ColoredThread getRandomThread() {
        return new ColoredThread(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
    }

    public static ColoredThread[] getRandomThreads(int n) {
        final ColoredThread[] threads = new ColoredThread[n];
        for (int i = 0; i < n; i++)
            threads[i] = getRandomThread();
        return threads;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    @Override
    public String toString() {
        return "ColoredThread{" +
                "color=" + color +
                '}';
    }
}
