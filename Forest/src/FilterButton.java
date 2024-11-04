import java.awt.*;
import javax.swing.*;

public class FilterButton extends ViewButton {
    public FilterButton(String text, String color, View view, Controller controller, int factor) {
        super(text);

        this.customiseFilterRibbonBtns();

        this.addActionListener(e -> {
            if (controller.numberOfSeedsPlanted > 0 && controller.numberOfSeedsPlanted < 5 && !controller.simulationStarted) {
                return;
            }
            if(!this.clicked)
            {
                this.setBackground(Color.decode(color));
                this.setBorder(BorderFactory.createLineBorder(Color.decode(color), 15)); // Black border
                ViewButton.otherClicked = true;
            }
            else {
                this.setBackground(Color.WHITE);
                this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15)); // Black border
                ViewButton.otherClicked = false;
            }
            this.toggleClicked();
        });

        switch (factor) {
            case 1: // RAINFALL
                this.addActionListener(e -> {
                    if (controller.numberOfSeedsPlanted > 0 && controller.numberOfSeedsPlanted < 5 && !controller.simulationStarted) {
                        return;
                    }
                    if (!this.clicked) {
                        controller.pauseSimulation();
                        for (int i = 0; i < View.mapSize; i++) {
                            for(int j = 0; j < View.mapSize; j++) {
                                int index = (View.mapSize*i) + j;
                                view.grounds[index].setColor(FilterButton.adjustBrightness(Color.decode(color), (float) controller.RAINFALL[index]));
                            }
                        }
                    }
                    else{
                        if (controller.numberOfSeedsPlanted > 0)
                            controller.startSimulation();
                        for (int i = 0; i < View.mapSize; i++) {
                            for(int j = 0; j < View.mapSize; j++) {
                                int index = (View.mapSize*i) + j;
                                view.grounds[index].setColor(Color.WHITE);
                            }
                        }
                    }
                });
                break;
            case 2: // HUMIDITY
                this.addActionListener(e -> {
                    if (controller.numberOfSeedsPlanted > 0 && controller.numberOfSeedsPlanted < 5 && !controller.simulationStarted) {
                        return;
                    }
                    System.out.println(controller.numberOfSeedsPlanted);
                    if (!this.clicked) {
                        controller.pauseSimulation();
                        for (int i = 0; i < View.mapSize; i++) {
                            for(int j = 0; j < View.mapSize; j++) {
                                int index = (View.mapSize*i) + j;
                                view.grounds[index].setColor(FilterButton.adjustBrightness(Color.decode(color), (float) controller.HUMIDITY[index]));
                            }
                        }
                    }
                    else {
                        if (controller.numberOfSeedsPlanted > 0)
                            controller.startSimulation();
                        for (int i = 0; i < View.mapSize; i++) {
                            for(int j = 0; j < View.mapSize; j++) {
                                int index = (View.mapSize*i) + j;
                                view.grounds[index].setColor(Color.WHITE);
                            }
                        }
                    }
                });
                break;
            case 3: // SUNLIGHT
                this.addActionListener(e -> {
                    if (controller.numberOfSeedsPlanted > 0 && controller.numberOfSeedsPlanted < 5 && !controller.simulationStarted) {
                        return;
                    }
                    System.out.println(controller.numberOfSeedsPlanted);
                    if (!this.clicked) {
                        controller.pauseSimulation();
                        for (int i = 0; i < View.mapSize; i++) {
                            for(int j = 0; j < View.mapSize; j++) {
                                int index = (View.mapSize*i) + j;
                                view.grounds[index].setColor(FilterButton.adjustBrightness(Color.decode(color), (float) controller.SUNLIGHT[index]));
                            }
                        }
                    }
                    else {
                        if (controller.numberOfSeedsPlanted > 0)
                            controller.startSimulation();
                        for (int i = 0; i < View.mapSize; i++) {
                            for(int j = 0; j < View.mapSize; j++) {
                                int index = (View.mapSize*i) + j;
                                view.grounds[index].setColor(Color.WHITE);
                            }
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    public static Color adjustBrightness(Color color, float brightness) {
        // Ensure brightness stays within the range [0.0, 1.0]
        brightness = Math.min(Math.max(0.5f + brightness, 0.0f), 1.0f);
        
        // Convert RGB to HSB values
        float[] hsbVals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        
        // Adjust brightness
        Color newColor = Color.getHSBColor(hsbVals[0], hsbVals[1], brightness);
        
        return newColor;
    }

    public void customiseFilterRibbonBtns() {
        this.setFont(new Font("FantasqueSansM Nerd Font", Font.BOLD, 18)); // Change font type and size
        // Set colors
        this.setForeground(Color.decode("#21201d")); // Text color
        this.setBackground(Color.WHITE); // Text color
        // Set border
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15)); // Black border
        // Additional styling
        this.setFocusPainted(false); // Remove focus outline
        this.setOpaque(true); // Ensure the background color is rendered
    }
}
