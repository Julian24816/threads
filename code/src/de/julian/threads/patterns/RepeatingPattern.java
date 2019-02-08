package de.julian.threads.patterns;

import de.julian.threads.Pattern;
import de.julian.threads.knots.KnotType;

import java.util.regex.Matcher;

class RepeatingPattern implements Pattern {

    private final String[] rows;
    private final int columns;

    RepeatingPattern(String description) {
        if (!containsOnlyAllowedCharacters(description))
            throw new IllegalArgumentException("illegal character in description - only '\\/<>X|' are allowed.");
        rows = description.trim().split("\n");
        columns = rows[0].length();
        if (!allRowLengthsAreEqual(rows, columns))
                throw new IllegalArgumentException("not all rows have the same length");
    }

    static boolean containsOnlyAllowedCharacters(String description) {
        Matcher matcher = java.util.regex.Pattern.compile("[\\\\/<>X|\n]").matcher(description);
        return matcher.find();
    }

    static boolean allRowLengthsAreEqual(String description) {
        String[] rows = description.trim().split("\n");
        int columns = rows[0].length();
        return allRowLengthsAreEqual(rows, columns);
    }

    private static boolean allRowLengthsAreEqual(String[] rows, int columns) {
        for (String row : rows)
            if (row.length() != columns)
                return false;
        return true;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getRows() {
        return 48;
    }

    @Override
    public KnotType getKnotType(int row, int column) {
        return KnotType.get(rows[row % rows.length].charAt(column));
    }

    @Override
    public String toString() {
        return String.join("\n", rows);
    }
}
