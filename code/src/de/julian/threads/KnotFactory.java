package de.julian.threads;

class KnotFactory {
    static Knot get(KnotType knotType, ColoredThread leftThread, ColoredThread rightThread) {
        return new DefaultKnot(leftThread, rightThread);
        //TODO use knotType
    }
}
