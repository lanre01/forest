import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.List;

import static java.lang.Math.random;

public class Controller {

    Model model;
    View view;
    double[] growthRate = new double[100];
    // Environmental factors
    double[] HUMIDITY = new double[100];
    double[] RAINFALL = new double[100];
    double[] SUNLIGHT = new double[100];
    int[] Plants = new int[100];
    int[] plantAges = new int[100]; // To track the age of each plant
    int simulationSteps = 0;

    // Plant type constants
    public static final int PLANT_NONE = 0;
    public static final int PLANT_OAK_TREE = 1;
    public static final int PLANT_CACTUS = 2;
    public static final int PLANT_FERN = 3;
    public static final int PLANT_PINE_TREE = 4;
    public static final int PLANT_RICE_PLANT = 5;
    int numberOfSeedsPlanted = 0;
    boolean simulationStarted = false;
    Timer simulation;
    ActionListener simulationTask;


    // Maximum lifespan for plants
    int MAX_PLANT_AGE = 10; // Adjust as needed

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
        simulationTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSimulation();
            }
        };

        simulation = new Timer(200, simulationTask);
    }

    public void initialise(Model model, View view) {
        this.model = model;
        this.view = view;

        // Initialize growth rates and environmental factors but do not start the simulation yet
        initializeGrowthRates();
        initializeEnvironmentalFactors();
    }

    public void seedPlanted() {
        numberOfSeedsPlanted++;
        if (numberOfSeedsPlanted == 5 && !simulationStarted) {
            startSimulation();
        }
    }

    public void startSimulation() {
        simulationStarted = true;
        simulation.start();
    }

    public void pauseSimulation() {
        simulationStarted = false;
        simulation.stop();
    }



    public void startup() {
        initializeGrowthRates();
        initializeEnvironmentalFactors();
    }

    private void initializeGrowthRates() {
        for (int i = 0; i < 100; i++) {
            growthRate[i] = 0.0;
            plantAges[i] = 0;
        }
    }

    private void initializeEnvironmentalFactors() {
        for (int i = 0; i < 100; i++) {
            HUMIDITY[i] = 0.4 * random();
            RAINFALL[i] = 0.4 * random();
            SUNLIGHT[i] = 0.4 * random();
        }
    }

    private void updateSimulation() {
        simulationSteps++;

        for (int i = 0; i < 100; i++) {
            if (Plants[i] > PLANT_NONE) {
                // Increment plant age
                plantAges[i]++;

                if (plantAges[i] >= MAX_PLANT_AGE) {
                    // Plant dies of old age
                    Plants[i] = PLANT_NONE;
                    growthRate[i] = 0.0;
                    plantAges[i] = 0;
                    int x = i / 10;
                    int y = i % 10;
                    model.setButtonColor(Color.WHITE, x, y);
                    continue;
                }

                propagationFunction(i, Plants[i]);

                if (growthRate[i] < 0.01) {
                    // Plant dies
                    Plants[i] = PLANT_NONE;
                    growthRate[i] = 0.0;
                    plantAges[i] = 0;
                    int x = i / 10;
                    int y = i % 10;
                    model.setButtonColor(Color.WHITE, x, y);
                } else {
                    adjustCellColor(i);

                    if (growthRate[i] > 0.6) {
                        int x = i / 10;
                        int y = i % 10;
                        spreadToNeighbors(x, y, Plants[i], true);
                    }
                }
            }
        }

        // Periodically call spontaneousGrowth
        if (simulationSteps % 5 == 0) {
            spontaneousGrowth();
        }

        // Periodically update environmental factors
        if (simulationSteps % 10 == 0) {
            updateEnvironmentalFactors();
        }

        view.refreshView();
    }

    private void spontaneousGrowth() {
        for (int i = 0; i < 100; i++) {
            if (Plants[i] == PLANT_NONE) {
                if (Math.random() < 0.01) { // 1% chance per empty cell
                    int plantType = 1 + new Random().nextInt(5); // Plant types 1 to 5
                    Plants[i] = plantType;
                    growthRate[i] = 0.1; // Low initial growth rate
                    plantAges[i] = 0;
                    adjustCellColor(i);
                }
            }
        }
    }

    private void updateEnvironmentalFactors() {
        for (int i = 0; i < 100; i++) {
            HUMIDITY[i] += (Math.random() - 0.5) * 0.1; // Small random changes
            HUMIDITY[i] = Math.max(0.0, Math.min(HUMIDITY[i], 1.0));

            RAINFALL[i] += (Math.random() - 0.5) * 0.1;
            RAINFALL[i] = Math.max(0.0, Math.min(RAINFALL[i], 1.0));

            SUNLIGHT[i] += (Math.random() - 0.5) * 0.1;
            SUNLIGHT[i] = Math.max(0.0, Math.min(SUNLIGHT[i], 1.0));
        }
    }

    private void adjustCellColor(int index) {
        int plantType = Plants[index];
        if (plantType == PLANT_NONE) {
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
        double growthIncrement;
        switch (plant) {
            case PLANT_OAK_TREE:
                growthIncrement = calculateGrowth(index, 0.6, 0.9, 0.4, 0.7, 0.4, 0.6);
                break;
            case PLANT_CACTUS:
                growthIncrement = calculateGrowth(index, 0.8, 1.0, 0.1, 0.3, 0.1, 0.4);
                break;
            case PLANT_FERN:
                growthIncrement = calculateGrowth(index, 0.2, 0.5, 0.7, 0.9, 0.7, 0.9);
                break;
            case PLANT_PINE_TREE:
                growthIncrement = calculateGrowth(index, 0.5, 0.8, 0.3, 0.6, 0.3, 0.5);
                break;
            case PLANT_RICE_PLANT:
                growthIncrement = calculateGrowth(index, 0.6, 0.8, 0.7, 0.9, 0.6, 0.9);
                break;
            default:
                return;
        }

        // Adjust growth increment for spontaneous plants
        if (growthRate[index] < 0.2) {
            growthIncrement *= 0.1; // Slow growth for spontaneous plants
        } else {
            growthIncrement *= 0.5; // Regular growth rate
        }

        growthRate[index] += growthIncrement;

        growthRate[index] = Math.max(0.0, Math.min(growthRate[index], 1.0));
    }

    private double calculateGrowth(int index, double sunMin, double sunMax, double rainMin, double rainMax, double humMin, double humMax) {
        double sunFactor = (SUNLIGHT[index] >= sunMin && SUNLIGHT[index] <= sunMax) ? SUNLIGHT[index] : SUNLIGHT[index] * 0.5;
        double rainFactor = (RAINFALL[index] >= rainMin && RAINFALL[index] <= rainMax) ? RAINFALL[index] : RAINFALL[index] * 0.5;
        double humFactor = (HUMIDITY[index] >= humMin && HUMIDITY[index] <= humMax) ? HUMIDITY[index] : HUMIDITY[index] * 0.5;
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
                if (Plants[index] == PLANT_NONE) {
                    neighbors.add(new Point(nx, ny));
                }
            }
        }

        // Shuffle for sporadic spread
        if (sporadic) {
            Collections.shuffle(neighbors);
        }

        // Limit the spread
        int spreadLimit = 1;
        int spreadCount = 0;

        for (Point p : neighbors) {
            if (spreadCount >= spreadLimit - random()) break;
            int index = (10 * p.x) + p.y;
            Plants[index] = plantType;
            growthRate[index] = 0.5; // Starting growth rate
            plantAges[index] = 0;
            adjustCellColor(index);
            spreadCount++;
        }
    }
}
