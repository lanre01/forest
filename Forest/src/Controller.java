import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.List;

public class Controller {

    Model model;
    View view;
    double[] growthRate = new double[100];
    // Environmental factors
    double[] HUMIDITY = new double[100];
    double[] RAINFALL = new double[100];
    double[] SUNLIGHT = new double[100];
    int[] Plants = new int[100];
    // Plant type constants
    public static final int PLANT_NONE = 0;
    public static final int PLANT_OAK_TREE = 1;
    public static final int PLANT_CACTUS = 2;
    public static final int PLANT_FERN = 3;
    public static final int PLANT_PINE_TREE = 4;
    public static final int PLANT_RICE_PLANT = 5;

    // Initialize colors for each plant type
    final Color[] InitColors = {
            null, // index 0, no plant
            Color.BLUE,   // Oak Tree
            Color.GREEN,  // Cactus
            Color.RED,    // Fern
            Color.YELLOW, // Pine Tree
            Color.GRAY    // Rice Plant
    };

    public Controller() {
    }

    public void initialise(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startup() {
        initializeGrowthRates();
        initializeEnvironmentalFactors();

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSimulation();
            }
        });
        timer.start();
    }

    private void initializeGrowthRates() {
        for (int i = 0; i < 100; i++) {
            growthRate[i] = 0.0;
        }
    }

    private void initializeEnvironmentalFactors() {
        for (int i = 0; i < 100; i++) {
            HUMIDITY[i] = Math.random();
            RAINFALL[i] = Math.random();
            SUNLIGHT[i] = Math.random();
        }
    }

    private void updateSimulation() {
        for (int i = 0; i < 100; i++) {
            if (Plants[i] > PLANT_NONE) {
                propagationFunction(i, Plants[i]);

                if (growthRate[i] < 0.1) {
                    // Plant dies
                    Plants[i] = PLANT_NONE;
                    growthRate[i] = 0.0;
                    int x = i / 10;
                    int y = i % 10;
                    model.setButtonColor(Color.WHITE, x, y);
                } else {
                    adjustCellColor(i);
                    // Spread if growth rate is high
                    if (growthRate[i] > 0.8) {
                        int x = i / 10;
                        int y = i % 10;
                        spreadToNeighbors(x, y, Plants[i], true);
                    }
                }
            }
        }
        view.refreshView();
    }

    private void adjustCellColor(int index) {
        int plantType = Plants[index];
        if (plantType == PLANT_NONE) {
            // No plant, set to white
            int x = index / 10;
            int y = index % 10;
            model.setButtonColor(Color.WHITE, x, y);
            return;
        }

        Color baseColor = InitColors[plantType];
        float[] hsbVals = Color.RGBtoHSB(
                baseColor.getRed(),
                baseColor.getGreen(),
                baseColor.getBlue(),
                null);
        float brightness = (float) (0.2 + (0.8 * growthRate[index]));
        Color adjustedColor = Color.getHSBColor(hsbVals[0], hsbVals[1], brightness);
        int x = index / 10;
        int y = index % 10;
        model.setButtonColor(adjustedColor, x, y);
    }

    private void propagationFunction(int index, int plant) {
        switch (plant) {
            case PLANT_OAK_TREE:
                growthRate[index] += calculateGrowth(index, 0.6, 0.9, 0.4, 0.7, 0.4, 0.6);
                break;
            case PLANT_CACTUS:
                growthRate[index] += calculateGrowth(index, 0.8, 1.0, 0.1, 0.3, 0.1, 0.4);
                break;
            case PLANT_FERN:
                growthRate[index] += calculateGrowth(index, 0.2, 0.5, 0.7, 0.9, 0.7, 0.9);
                break;
            case PLANT_PINE_TREE:
                growthRate[index] += calculateGrowth(index, 0.5, 0.8, 0.3, 0.6, 0.3, 0.5);
                break;
            case PLANT_RICE_PLANT:
                growthRate[index] += calculateGrowth(index, 0.6, 0.8, 0.7, 0.9, 0.6, 0.9);
                break;
            default:
                return;
        }
        growthRate[index] = Math.max(0.0, Math.min(growthRate[index], 1.0));
    }

    private double calculateGrowth(int index, double sunMin, double sunMax, double rainMin, double rainMax, double humMin, double humMax) {
        double sunFactor = (SUNLIGHT[index] >= sunMin && SUNLIGHT[index] <= sunMax) ? SUNLIGHT[index] : -SUNLIGHT[index];
        double rainFactor = (RAINFALL[index] >= rainMin && RAINFALL[index] <= rainMax) ? RAINFALL[index] : -RAINFALL[index];
        double humFactor = (HUMIDITY[index] >= humMin && HUMIDITY[index] <= humMax) ? HUMIDITY[index] : -HUMIDITY[index];
        return (sunFactor + rainFactor + humFactor) / 3;
    }

    private void spreadToNeighbors(int x, int y, int plantType, boolean sporadic) {
        List<Point> neighbors = new ArrayList<>();

        // Collect all valid neighbors
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;

                if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10 || (dx == 0 && dy == 0)) continue;

                int index = (10 * nx) + ny;
                if (Plants[index] == PLANT_NONE) {  // Check if neighboring cell is empty
                    neighbors.add(new Point(nx, ny));
                }
            }
        }

        // Shuffle for sporadic spread
        if (sporadic) {
            Collections.shuffle(neighbors);
        }

        // Limit the spread
        int spreadLimit = 2;
        int spreadCount = 0;

        for (Point p : neighbors) {
            if (spreadCount >= spreadLimit) break;
            int index = (10 * p.x) + p.y;
            Plants[index] = plantType;
            growthRate[index] = 0.5; // Starting growth rate
            adjustCellColor(index);
            spreadCount++;
        }
    }

    // ... any additional methods ...
}


/**
 * map of plants to colors
 * oak tree - blue
 * cactus - green
 * fern - red
 * pine tree - yellow
 * rice plant - grey
 */








