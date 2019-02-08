package de.julian.threads;

import java.awt.*;

public interface Knot {
    Color getColor();

    ColoredThread getLeftEmergingThread();

    ColoredThread getRightEmergingThread();
}
