package de.julian.threads.patterns;

import de.julian.threads.Pattern;

public class PatternFactory {
    public static Pattern forString(String description) {
        return new RepeatingPattern(description);
    }

    public static boolean isCorrectPattern(String description) {
        return RepeatingPattern.containsOnlyAllowedCharacters(description) && RepeatingPattern.allRowLengthsAreEqual(description);
    }
}
