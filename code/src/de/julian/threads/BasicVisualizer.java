package de.julian.threads;

import java.awt.*;

/**
 * draws all knots as ovals in their color
 */
public class BasicVisualizer extends Visualizer {

    BasicVisualizer(Bracelet bracelet) {
        super(bracelet);
    }

    @Override
    protected void drawKnot(Graphics g, int x, int y, int width, int height, Knot knot) {
        g.setColor(knot.getColor());
        g.fillOval(x, y, width, height);
    }
}