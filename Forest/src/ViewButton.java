import java.awt.*;
import javax.swing.*;

public class ViewButton extends JButton {
    Boolean clicked;
    String color;
    static Boolean otherClicked = false;
    static ViewButton[] buttons;

    public ViewButton(String text, String color, Controller controller) {
        super(text);
        this.clicked = false;
        this.color = color;
        
        this.addActionListener(e -> {
            if (controller.numberOfSeedsPlanted > 0 && controller.numberOfSeedsPlanted < 5 && !controller.simulationStarted) {
                return;
            }
            if(!this.clicked && !ViewButton.otherClicked)
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

        this.customiseFilterRibbonBtns();
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

    public Boolean toggleClicked() {
        clicked = !clicked;
        return clicked;
    }
}
