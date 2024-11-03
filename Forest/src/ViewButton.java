import java.awt.*;
import javax.swing.*;

public class ViewButton extends JButton {
    Boolean clicked;
    Color color;
    static Boolean otherClicked = false;
    static ViewButton[] buttons;

    public ViewButton(String text) {
        super(text);
        this.clicked = false;
        
    }

    public Boolean toggleClicked() {
        clicked = !clicked;
        return clicked;
    }
}
