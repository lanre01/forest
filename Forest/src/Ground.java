import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JButton;

public class Ground extends JButton {
    Color bgColor = Color.WHITE;

    public Ground() {
        //this.setMinimumSize(new Dimension(width, height));
        //this.setPreferredSize(new Dimension(width, height));
        this.setBackground(this.bgColor);
        //this.setBorder(BorderFactory.createBevelBorder(this.borderSize));
        //this.setBorder(BorderFactory.createLineBorder(this.borderColor));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered");
                // Change background color on hover
                setColor(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
                // Reset background color when mouse exits
                setColor(Color.WHITE);
            }
        });
    }


    public void setColor(Color color) {
        this.setBackground(color);
        repaint(); //
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.bgColor);
    }
}
