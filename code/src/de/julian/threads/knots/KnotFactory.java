package de.julian.threads.knots;

import de.julian.threads.ColoredThread;
import de.julian.threads.Knot;

import java.awt.*;

public class KnotFactory {
    public static Knot get(KnotType knotType, ColoredThread leftThread, ColoredThread rightThread) {
        if (knotType.isNoKnotSpecialCase()) throw new RuntimeException("not implemented");
        return new Knot() {
            @Override
            public Color getColor() {
                return (knotType.useRightThread() ? rightThread : leftThread).getColor();
            }

            @Override
            public ColoredThread getLeftEmergingThread() {
                return knotType.crossThreads() ? rightThread : leftThread;
            }

            @Override
            public ColoredThread getRightEmergingThread() {
                return knotType.crossThreads() ? leftThread : rightThread;
            }
        };
    }
}
