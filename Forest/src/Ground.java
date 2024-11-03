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
                        controller.Plants[(View.mapSize * x) + y] = plantTypeId;
                        controller.growthRate[(View.mapSize * x) + y] = 0.8; // Starting growth rate increased
                        Color baseColor = controller.InitColors[plantTypeId];
                        animateColorTransition(baseColor);
                        clicked = true;

                        // Notify the controller that a seed has been planted
                        controller.seedPlanted();
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

    private void updateCellColor(int x, int y, int plantType) {
        if (plantType == 1) {
            view.grounds[(View.mapSize * x) + y].animateColorTransition(Color.GREEN);  // Animated color change for plant type 1
        } else {
            view.grounds[(View.mapSize * x) + y].animateColorTransition(Color.LIGHT_GRAY);  // Default color
        }
    }

    private void spreadToNeighbors(int x, int y, int plantType, boolean sporadic) {
        List<Point> neighbors = new ArrayList<>();

        // Collect all valid neighbors
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;

                if (nx < 0 || ny < 0 || nx >= View.mapSize || ny >= View.mapSize || (dx == 0 && dy == 0)) continue;

                if (model.Ground[(View.mapSize * nx) + ny] == 0) {  // Check if neighboring cell is empty
                    neighbors.add(new Point(nx, ny));
                }
            }
        }

        // Shuffle for sporadic spread
        if (sporadic) {
            Collections.shuffle(neighbors);
        }

        // Start a timer to animate spreading to each neighboring cell
        Timer timer = new Timer(200, new ActionListener() {  // Delay of 200 ms
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < neighbors.size()) {
                    Point p = neighbors.get(index);
                    System.out.println(p.x);
                    System.out.println(p.y);
                    model.setPlant(plantType, p.x, p.y);
                    updateCellColor(p.x, p.y, plantType);
                    index++;
                } else {
                    ((Timer) e.getSource()).stop();  // Stop the timer after all neighbors are processed
                }
            }
        });

        timer.start();  // Start the timer to begin animation
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
