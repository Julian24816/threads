package de.julian.threads.visualizer;

import de.julian.threads.Bracelet;
import de.julian.threads.Knot;
import de.julian.threads.Visualizer;

import java.awt.*;

/**
 * draws all knots as ovals in their color
 */
public class BasicVisualizer extends Visualizer {

    public BasicVisualizer(Bracelet bracelet) {
        super(bracelet);
    }

    @Override
    protected void drawKnot(Graphics g, int x, int y, int width, int height, Knot knot) {
        g.setColor(knot.getColor());
        g.fillOval(x, y, width, height);
    }
}