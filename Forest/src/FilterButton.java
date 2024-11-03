import java.awt.*;
import javax.swing.*;

public class FilterButton extends ViewButton {
    public FilterButton(String text, String color, View view, Controller controller, int factor) {
        super(text, color);

        switch (factor) {
            case 1: // RAINFALL
                this.addActionListener(e -> {
                    if (!this.clicked) {
                        controller.pauseSimulation();
                        for (int i = 0; i < 10; i++) {
                            for(int j = 0; j < 10; j++) {
                                int index = (10*i) + j;
                                view.grounds[index].setColor(FilterButton.adjustBrightness(Color.decode(color), (float) controller.RAINFALL[index]));
                            }
                        }
                    }
                    else {
                        controller.startSimulation();
                        for (int i = 0; i < 10; i++) {
                            for(int j = 0; j < 10; j++) {
                                int index = (10*i) + j;
                                view.grounds[index].setColor(Color.WHITE);
                            }
                        }
                    }
                });
                break;
            case 2: // HUMIDITY
                this.addActionListener(e -> {
                    if (!this.clicked) {
                        controller.pauseSimulation();
                        for (int i = 0; i < 10; i++) {
                            for(int j = 0; j < 10; j++) {
                                int index = (10*i) + j;
                                view.grounds[index].setColor(FilterButton.adjustBrightness(Color.decode(color), (float) controller.HUMIDITY[index]));
                            }
                        }
                    }
                    else {
                        controller.startSimulation();
                        for (int i = 0; i < 10; i++) {
                            for(int j = 0; j < 10; j++) {
                                int index = (10*i) + j;
                                view.grounds[index].setColor(Color.WHITE);
                            }
                        }
                    }
                });
                break;
            case 3: // SUNLIGHT
                this.addActionListener(e -> {
                    if (!this.clicked) {
                        controller.pauseSimulation();
                        for (int i = 0; i < 10; i++) {
                            for(int j = 0; j < 10; j++) {
                                int index = (10*i) + j;
                                view.grounds[index].setColor(FilterButton.adjustBrightness(Color.decode(color), (float) controller.SUNLIGHT[index]));
                            }
                        }
                    }
                    else {
                        controller.startSimulation();
                        for (int i = 0; i < 10; i++) {
                            for(int j = 0; j < 10; j++) {
                                int index = (10*i) + j;
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
        brightness = Math.min(Math.max(brightness, 0.0f), 1.0f);
        
        // Convert RGB to HSB values
        float[] hsbVals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        
        // Adjust brightness
        Color newColor = Color.getHSBColor(hsbVals[0], hsbVals[1], brightness);
        
        return newColor;
    }
}
