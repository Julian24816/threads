package de.julian.threads;

import javax.swing.*;
import java.awt.*;

public abstract class Visualizer extends JComponent {
    public abstract void setBracelet(Bracelet bracelet);
    @Override
    protected abstract void paintComponent(Graphics g);
}
