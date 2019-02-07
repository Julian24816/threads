package de.julian.threads;

class LinesPattern implements Pattern {
    @Override
    public int getColumns() {
        return 7;
    }

    @Override
    public KnotType getKnotType(int row, int column) {
        return KnotType.LEFT;
    }
}
