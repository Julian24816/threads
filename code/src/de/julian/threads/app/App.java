package de.julian.threads.app;

import de.julian.threads.BasicBracelet;
import de.julian.threads.Bracelet;
import de.julian.threads.ColoredThread;
import de.julian.threads.Pattern;
import de.julian.threads.patterns.PatternFactory;
import de.julian.threads.visualizer.BasicVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class App extends JFrame {

    private Bracelet bracelet;
    private BraceletVisualizerComponent braceletVisualizerComponent;
    private JTextArea descriptionTextArea;
    private JTextArea patternTextArea;
    private JPanel threadColorPane;

    private App(Bracelet bracelet) {
        this.bracelet = bracelet;
        createFrame();
    }

    public static void main(String[] args) {
        final BasicBracelet defaultBracelet = new BasicBracelet(PatternFactory.forString(">\\\\\\///\n\\\\\\///<"), ColoredThread.forColors(
                Color.YELLOW, Color.WHITE, Color.GREEN, Color.BLACK,
                Color.WHITE, Color.WHITE, Color.GREEN, Color.YELLOW));
        new App(defaultBracelet);
    }

    private void setDisplayedBracelet(Bracelet bracelet) {
        this.bracelet = bracelet;
        braceletVisualizerComponent.switchToVisualizer(new BasicVisualizer(bracelet));
        patternTextArea.setText(bracelet.getPatternDescription());
        descriptionTextArea.setText("a description of bracelet " + bracelet + " will land here.");
        refreshColorButtons();
        pack();
    }

    private void createFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Threads - a friendship bracelet pattern visualizer");
        this.getContentPane().add(createBraceletsPane(), BorderLayout.LINE_START);
        this.getContentPane().add(createMainPane());
        this.pack();
        this.setVisible(true);
    }

    private JPanel createMainPane() {
        JPanel mainPain = new JPanel(new BorderLayout());
        mainPain.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        mainPain.add(createDescriptionPane());
        mainPain.add(createSettingsPane(), BorderLayout.PAGE_END);
        return mainPain;
    }

    private JPanel createSettingsPane() {
        JPanel settingsPane = new JPanel();
        settingsPane.setLayout(new BoxLayout(settingsPane, BoxLayout.LINE_AXIS));
        settingsPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        settingsPane.add(createPatternAndManipulationsPane());
        settingsPane.add(createGeneratorPane());
        return settingsPane;
    }

    private JPanel createThreadColorPane() {
        threadColorPane = new JPanel(new GridLayout(0, 1));
        threadColorPane.setBorder(BorderFactory.createEmptyBorder());
        refreshColorButtons();

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        wrapper.add(threadColorPane, BorderLayout.PAGE_START);
        return wrapper;
    }

    private void refreshColorButtons() {
        threadColorPane.removeAll();
        for (ColoredThread thread : bracelet.getThreads()) {
            JButton colorButton = new JButton();
            colorButton.setBackground(thread.getColor());
            colorButton.setPreferredSize(new Dimension(30, 30));
            colorButton.addActionListener(e -> {
                Color newColor = JColorChooser.showDialog(this, "choose a new color", thread.getColor());
                if (newColor != null) {
                    colorButton.setBackground(newColor);
                    thread.setColor(newColor);
                    braceletVisualizerComponent.repaint();
                }
            });
            threadColorPane.add(colorButton);
        }
    }

    private JPanel createPatternAndManipulationsPane() {
        JPanel patternAndManipulationButtonsPane = new JPanel();
        patternAndManipulationButtonsPane.setLayout(new BoxLayout(patternAndManipulationButtonsPane, BoxLayout.PAGE_AXIS));
        patternAndManipulationButtonsPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        patternAndManipulationButtonsPane.add(createPatternPane());
        patternAndManipulationButtonsPane.add(Box.createRigidArea(new Dimension(0, 10)));
        patternAndManipulationButtonsPane.add(createManipulationButtonsPane());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder());
        wrapper.add(patternAndManipulationButtonsPane, BorderLayout.PAGE_START);
        return wrapper;
    }

    private JPanel createPatternPane() {
        JPanel patternPane = new JPanel(new BorderLayout());
        patternPane.setBorder(BorderFactory.createTitledBorder("Pattern"));
        patternPane.add(createPatternTextArea());
        patternPane.add(createApplyPatternButton(), BorderLayout.LINE_END);
        return patternPane;
    }

    private JButton createApplyPatternButton() {
        final JButton applyPatternButton = new JButton("GO");
        applyPatternButton.addActionListener(e -> submitPattern());
        return applyPatternButton;
    }

    private void submitPattern() {
        String description = patternTextArea.getText();
        if (PatternFactory.isCorrectPattern(description)) {
            final Pattern pattern = PatternFactory.forString(description);
            final ColoredThread[] threads = fixNumberOfThreads(bracelet.getThreads(), pattern.getColumns() + 1);
            setDisplayedBracelet(new BasicBracelet(pattern, threads));
        }
    }

    private ColoredThread[] fixNumberOfThreads(ColoredThread[] existingThreads, int targetNumber) {
        if (existingThreads.length == targetNumber) return existingThreads;

        ColoredThread[] result = new ColoredThread[targetNumber];
        if (existingThreads.length > targetNumber) {
            System.arraycopy(existingThreads, 0, result, 0, targetNumber);
        } else {
            final int requiredNewThreads = targetNumber - existingThreads.length;
            final ColoredThread[] newThreads = ColoredThread.getRandomThreads(requiredNewThreads);
            System.arraycopy(existingThreads, 0, result, 0, existingThreads.length);
            System.arraycopy(newThreads, 0, result, existingThreads.length, requiredNewThreads);
        }
        return result;
    }

    private JTextArea createPatternTextArea() {
        patternTextArea = new JTextArea(bracelet.getPatternDescription(), 2, 11);

        final String SUBMIT_PATTERN = "submit-pattern";
        final InputMap inputMap = patternTextArea.getInputMap();
        inputMap.put(KeyStroke.getKeyStroke("shift ENTER"), "insert-break");
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), SUBMIT_PATTERN);
        patternTextArea.getActionMap().put(SUBMIT_PATTERN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPattern();
            }
        });

        return patternTextArea;
    }

    private JPanel createManipulationButtonsPane() {
        JPanel manipulationButtonsPane = new JPanel(new GridLayout(2, 2));
        manipulationButtonsPane.setBorder(BorderFactory.createTitledBorder("Manipulations"));
        for (int i = 0; i < 4; i++)
            manipulationButtonsPane.add(new JButton("Manipulation #" + i));
        return manipulationButtonsPane;
    }

    private JPanel createGeneratorPane() {
        JPanel generatorPane = new JPanel(new BorderLayout());
        generatorPane.setLayout(new BoxLayout(generatorPane, BoxLayout.PAGE_AXIS));
        generatorPane.setBorder(BorderFactory.createEmptyBorder());
        generatorPane.add(createGeneratorSettingsPane());
        generatorPane.add(Box.createRigidArea(new Dimension(0, 10)));
        generatorPane.add(createGeneratorButtonsPane(), BorderLayout.PAGE_END);
        return generatorPane;
    }

    private JPanel createGeneratorSettingsPane() {
        JPanel generatorSettingsPane = new JPanel(new BorderLayout());
        generatorSettingsPane.setBorder(BorderFactory.createTitledBorder("Generator Settings"));
        generatorSettingsPane.add(new JTextArea("there are no generator settings yet.", 2, 10));
        return generatorSettingsPane;
    }

    private JPanel createGeneratorButtonsPane() {
        JPanel generatorButtonsPane = new JPanel();
        generatorButtonsPane.setBorder(BorderFactory.createTitledBorder("generate"));
        generatorButtonsPane.add(new JButton("random bracelet"));
        generatorButtonsPane.add(createGenerateDefaultBraceletsButton());
        return generatorButtonsPane;
    }

    private JButton createGenerateDefaultBraceletsButton() {
        final JButton generateDefaultBracelets = new JButton("default bracelets");
        generateDefaultBracelets.addActionListener(e -> {
            setDisplayedBracelet(new BasicBracelet(
                    PatternFactory.forString("\\\\\\"),
                    ColoredThread.forColors(Color.GREEN, Color.RED, Color.BLUE, Color.CYAN)));
        });
        return generateDefaultBracelets;
    }

    private JPanel createDescriptionPane() {
        JPanel descriptionPane = new JPanel(new BorderLayout());
        descriptionPane.setBorder(BorderFactory.createTitledBorder("Description"));
        descriptionPane.add(createDescriptionTextArea());
        return descriptionPane;
    }

    private JTextArea createDescriptionTextArea() {
        descriptionTextArea = new JTextArea("a description of bracelet " + bracelet + " will land here.", 10, 50);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        //descriptionTextArea.setEditable(false);
        descriptionTextArea.setOpaque(true);
        descriptionTextArea.setBackground(new Color(0, 0, 0, 0));
        return descriptionTextArea;
    }

    private JPanel createBraceletsPane() {
        JPanel braceletsPane = new JPanel(new BorderLayout());
        braceletsPane.add(createSavedBraceletsPane());
        braceletsPane.add(createBraceletVisualizerAndColorsPane(), BorderLayout.LINE_END);
        return braceletsPane;
    }

    private JPanel createBraceletVisualizerAndColorsPane() {
        JPanel braceletVisualizerAndColorsPane = new JPanel();
        braceletVisualizerAndColorsPane.setLayout(new BoxLayout(braceletVisualizerAndColorsPane, BoxLayout.LINE_AXIS));
        braceletVisualizerAndColorsPane.setBorder(BorderFactory.createEmptyBorder());
        braceletVisualizerComponent = new BraceletVisualizerComponent(new BasicVisualizer(bracelet));
        braceletVisualizerAndColorsPane.add(braceletVisualizerComponent);
        braceletVisualizerAndColorsPane.add(createThreadColorPane());
        return braceletVisualizerAndColorsPane;
    }

    private JPanel createSavedBraceletsPane() {
        JPanel savedBraceletsPane = new JPanel(new BorderLayout());
        savedBraceletsPane.add(createSavedBraceletsScrollPane());
        savedBraceletsPane.add(new JButton("save"), BorderLayout.PAGE_END);
        savedBraceletsPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        return savedBraceletsPane;
    }

    private JScrollPane createSavedBraceletsScrollPane() {
        JScrollPane savedBracelets = new JScrollPane(new JTextArea("no saved bracelet yet.", 40, 20));
        savedBracelets.setBorder(BorderFactory.createEmptyBorder());
        savedBracelets.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        savedBracelets.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return savedBracelets;
    }
}
