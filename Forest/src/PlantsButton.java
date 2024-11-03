import java.awt.*;
import javax.swing.*;

public class PlantsButton extends ViewButton {
    public PlantsButton(String text, Color bgColor, Color fgColor) {
        super(text);

        this.setFont(new Font("FantasqueSansM Nerd Font", Font.BOLD, 18)); // Change font type and size
        // Set colors
        this.setForeground(fgColor); // Text color
        this.setBackground(bgColor); // Text color
        // Set border
        this.setBorder(BorderFactory.createLineBorder(bgColor, 15)); // Black border
        // Additional styling
        this.setFocusPainted(false); // Remove focus outline
        this.setOpaque(true); // Ensure the background color is rendered
    }
}
