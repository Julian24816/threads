package de.julian.threads;

enum KnotType {
    LEFT_OVER('\\'),
    RIGHT_OVER('/', true),
    LEFT_BACK('>', false, false),
    RIGHT_BACK('<', true, false),
    NO_KNOT(Constants.NO_KNOT_SPECIAL_CHARACTER),
    IRRELEVANT('X'); // same behaviour as left

    private final char description;
    private final boolean useRightThread;
    private final boolean crossThreads;

    KnotType(char description) {
        this(description, false, true);
    }

    KnotType(char description, boolean useRightThread) {
        this(description, useRightThread, true);
    }

    KnotType(char description, boolean useRightThread, boolean crossThreads) {
        this.description = description;
        this.useRightThread = useRightThread;
        this.crossThreads = crossThreads;
    }

    static KnotType get(char description) {
        switch (description) {
            case '\\':
                return LEFT_OVER;
            case '/':
                return RIGHT_OVER;
            case '>':
                return LEFT_BACK;
            case '<':
                return RIGHT_BACK;
            case Constants.NO_KNOT_SPECIAL_CHARACTER:
                return NO_KNOT;
            case 'X':
            default:
                return IRRELEVANT;
        }
    }

    boolean isNoKnotSpecialCase() {
        return description == Constants.NO_KNOT_SPECIAL_CHARACTER;
    }

    boolean useRightThread() {
        return useRightThread;
    }

    boolean crossThreads() {
        return crossThreads;
    }

    @Override
    public String toString() {
        return Character.toString(description);
    }

    private static class Constants {
        private static final char NO_KNOT_SPECIAL_CHARACTER = '|';
    }
}
