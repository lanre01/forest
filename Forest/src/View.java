import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class View extends JFrame {
    Model model;
    Controller controller;
    Ground[] grounds = new Ground[View.mapSize*View.mapSize];

    static final int mapSize = 100;


    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialise(Model model, Controller controller) {
        // Set up main frame properties
        this.model = model;
        this.controller = controller;
        setTitle("Farmland Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLayout(new BorderLayout()); // Use BorderLayout for main frame
                                       //
        // Center panel for the 10x10 grid with a fixed size
        JPanel gridPanel = new JPanel(new GridLayout(mapSize, mapSize));
        gridPanel.setPreferredSize(new Dimension(900, 900)); // Set fixed size for grid

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Ground button = new Ground(this.model, this.controller, this, i, j);
                grounds[(mapSize * i) + j] = button;
                model.setSoilValue(Math.random(), i, j);
                gridPanel.add(button); // Add each button to the grid panel
            }
        }

        // Wrapper panel to center-align gridPanel
        JPanel centerWrapper = new JPanel(new GridBagLayout()); // Use GridBagLayout for centering
        centerWrapper.add(gridPanel); // Add grid to the wrapper panel
        add(centerWrapper, BorderLayout.CENTER); // Add wrapper panel to the center of the main frame

        // Right panel for three vertically aligned buttons
        JPanel topPanel = new JPanel();
        topPanel.add(Box.createHorizontalGlue());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        FilterButton rightHumidityViewButton = new FilterButton("Humidity View", "#c9c386", this, controller, 2);
        FilterButton rightRainfallViewButton = new FilterButton("Rainfall view", "#79e5de", this, controller, 1);
        FilterButton rightSunlightViewButton = new FilterButton("Sunlight View", "#f7d913", this, controller, 3);


        topPanel.add(rightRainfallViewButton);
        topPanel.add(rightHumidityViewButton);
        topPanel.add(rightSunlightViewButton);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
        add(topPanel, BorderLayout.NORTH); // Add right panel to the east side of the frame

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(Box.createHorizontalGlue());
        PlantsButton oakTreeLegend = new PlantsButton("Oak Tree", controller.InitColors[1], Color.WHITE);
        PlantsButton cactusLegend = new PlantsButton("Cactus", controller.InitColors[2], Color.BLACK);
        PlantsButton fernLegend = new PlantsButton("Fern", controller.InitColors[3], Color.WHITE);
        PlantsButton pineTreeLegend = new PlantsButton("Pine Tree", controller.InitColors[4], Color.BLACK);
        PlantsButton ricePlantLegend = new PlantsButton("Rice Plant", controller.InitColors[5], Color.WHITE);

        bottomPanel.add(ricePlantLegend);
        bottomPanel.add(oakTreeLegend);
        bottomPanel.add(cactusLegend);
        bottomPanel.add(fernLegend);
        bottomPanel.add(pineTreeLegend);
        bottomPanel.add(Box.createHorizontalGlue());
        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to the south of the frame
        setVisible(true);    
    }

    public void feedbackToUser() {}

    public void refreshView() {

        for(int i = 0; i < mapSize; i++) {
           for(int j = 0; j < mapSize; j++) {
                grounds[(mapSize * i) + j].refresh();
           }
        }
    }
}
