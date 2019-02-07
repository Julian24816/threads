package de.julian.threads;

import java.awt.*;

interface Knot {
    Color getColor();
    ColoredThread getLeftEmergingThread();
    ColoredThread getRightEmergingThread();
}
