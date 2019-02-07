package de.julian.threads;

import java.awt.*;

/**
 * a left knot
 */
public class DefaultKnot implements Knot {

    private final ColoredThread leftThread;
    private final ColoredThread rightThread;

    DefaultKnot(ColoredThread leftThread, ColoredThread rightThread) {
        this.leftThread = leftThread;
        this.rightThread = rightThread;
    }

    @Override
    public Color getColor() {
        return leftThread.getColor();
    }

    @Override
    public ColoredThread getLeftEmergingThread() {
        return rightThread;
    }

    @Override
    public ColoredThread getRightEmergingThread() {
        return leftThread;
    }
}
