import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.HashSet;

import javax.swing.JButton;

public class Ground extends JButton {
    Color bgColor = Color.WHITE;
    Model model;
    Controller controller;
    int x, y;
    String plant = "soil";
    Boolean clicked = false;


    public Ground(Model model, Controller controller, int x, int y) {
        this.model = model;
        this.controller = controller;
        this.x = x;
        this.y = y;


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
                setColor(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
                // Reset background color when mouse exits
                setColor(Color.WHITE);
            }

        });
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!clicked && model.canClick()) {
                    model.incNumberOfClicks();
                    model.setPlant(1, x, y);
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
        this.setBackground(color);
        repaint(); //
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.bgColor);
    }
}
//int r = Math.min(bgColor.getRed() + increment, targetColor.getRed());
//int g = Math.min(bgColor.getGreen() + increment, targetColor.getGreen());
//int b = Math.min(bgColor.getBlue() + increment, targetColor.getBlue());
//bgColor = new Color(r, g, b);
//setBackground(bgColor);
//repaint();
//steps += increment; // Increment steps                    int g = Math.min(bgColor.getGreen() + increment, targetColor.getGreen());
//int b = Math.min(bgColor.getBlue() + increment, targetColor.getBlue());
//bgColor = new Color(r, g, b);
//setBackground(bgColor);
//repaint();
//steps += increment; // Increment steps
