import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ground extends JButton {
    private Color bgColor = Color.WHITE;  // Initial color
    private final Model model;
    private final View view;
    private final Controller controller;
    private final int x, y;
    private String plant = "Soil";
    private boolean clicked = false;

    public Ground(Model model, Controller controller, View view, int x, int y) {
        this.view = view;
        this.model = model;
        this.controller = controller;
        this.x = x;
        this.y = y;

        this.setBackground(this.bgColor);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!clicked && model.canClick()) {
                    // Show dialog to select plant type
                    String[] plantOptions = {"Oak Tree", "Cactus", "Fern", "Pine Tree", "Rice Plant"};
                    int plantType = JOptionPane.showOptionDialog(
                            view,
                            "Select a plant to plant:",
                            "Plant Selection",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            plantOptions,
                            plantOptions[0]);

                    if (plantType >= 0 && plantType <= 4) {
                        int plantTypeId = plantType + 1; // Plant types start from 1
                        model.incNumberOfClicks(x, y);
                        model.setPlant(plantTypeId, x, y);
                        controller.Plants[(10 * x) + y] = plantTypeId;
                        controller.growthRate[(10 * x) + y] = 0.5; // Starting growth rate
                        Color baseColor = controller.InitColors[plantTypeId];
                        animateColorTransition(baseColor);
                        clicked = true;
                    }
                }
            }
        });
    }

    public void refresh() {
        Color color = model.getButtonColor(this.x, this.y);
        this.plant = model.getButtonPlant(this.x, this.y);
        this.setColor(color);
    }

    public void setColor(Color color) {
        this.bgColor = color;
        this.setBackground(color);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(bgColor);
    }

    private void animateColorTransition(Color targetColor) {
        final Color startColor = getBackground(); // Use getBackground()

        if (startColor == null) {
            System.err.println("startColor is null!");
            return; // Prevent NullPointerException
        }

        Timer timer = new Timer(70, new ActionListener() {
            int steps = 0;
            final int maxSteps = 30;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (steps < maxSteps) {
                    int r = (int) (startColor.getRed() + (targetColor.getRed() - startColor.getRed()) * ((float) steps / maxSteps));
                    int g = (int) (startColor.getGreen() + (targetColor.getGreen() - startColor.getGreen()) * ((float) steps / maxSteps));
                    int b = (int) (startColor.getBlue() + (targetColor.getBlue() - startColor.getBlue()) * ((float) steps / maxSteps));
                    bgColor = new Color(r, g, b);
                    setBackground(bgColor);
                    repaint();
                    steps++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}
