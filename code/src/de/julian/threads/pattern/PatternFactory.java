package de.julian.threads.pattern;

import de.julian.threads.Pattern;

public class PatternFactory {
    public static Pattern forString(String description) {
        return new RepeatingPattern(description);
    }
}
