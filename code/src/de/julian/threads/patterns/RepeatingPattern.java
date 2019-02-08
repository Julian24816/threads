package de.julian.threads.patterns;

import de.julian.threads.Pattern;
import de.julian.threads.knots.KnotType;

import java.util.regex.Matcher;

class RepeatingPattern implements Pattern {

    private final String[] rows;
    private final int columns;

    RepeatingPattern(String description) {
        testForIllegalCharacters(description);
        rows = description.trim().split("\n");
        columns = rows[0].length();
        for (String row : rows)
            if (row.length() != columns)
                throw new IllegalArgumentException("not all rows have the same length");
    }

    private void testForIllegalCharacters(String description) {
        Matcher matcher = java.util.regex.Pattern.compile("[^\\\\/<>X|\n]").matcher(description);
        if (matcher.find())
            throw new IllegalArgumentException("illegal character in description: " + matcher.group());
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
