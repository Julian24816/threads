package de.julian.threads;

class PatternFactory {
    static Pattern forString(String description) {
        return new RepeatingPattern(description);
    }
}
