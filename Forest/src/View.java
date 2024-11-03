import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;

public class View extends JFrame {
    Model model;
    Controller controller;
    Ground[] grounds = new Ground[20*20];

    int mapSize = 20;

    public View() {}

    public void initialise(Model model, Controller controller) {
        // Set up main frame properties
        this.model = model;
        this.controller = controller;
        setTitle("10x10 Button Grid");
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
        FilterButton rightRainfallViewButton = new FilterButton("Rainfall view", "#79e5de");
        FilterButton rightHumidityViewButton = new FilterButton("Humidity View", "#c9c386");
        FilterButton rightSunlightViewButton = new FilterButton("Sunlight View", "#f7d913");


        topPanel.add(rightRainfallViewButton);
        topPanel.add(rightHumidityViewButton);
        topPanel.add(rightSunlightViewButton);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
        add(topPanel, BorderLayout.NORTH); // Add right panel to the east side of the frame

        // Bottom panel for the ribbon of buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(Box.createHorizontalGlue());
        for (int m = 0; m < 5; m++) { // Example of 5 buttons; adjust as needed
            JButton bottomButton = new JButton("Plant " + (m + 1));
            bottomPanel.add(bottomButton);
        }
        bottomPanel.add(Box.createHorizontalGlue());
        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to the south of the frame

        // Make the frame visible
        setVisible(true);    
    }

    public void feedbackToUser() {}

    public void customiseFilterRibbonBtns(JButton filterBtn) {
        filterBtn.setFont(new Font("FantasqueSansM Nerd Font", Font.BOLD, 18)); // Change font type and size
        // Set colors
        filterBtn.setForeground(Color.decode("#21201d")); // Text color
        filterBtn.setBackground(Color.WHITE); // Text color
        // Set border
        filterBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15)); // Black border
        // Additional styling
        filterBtn.setFocusPainted(false); // Remove focus outline
        filterBtn.setOpaque(true); // Ensure the background color is rendered
    }

    /*private void createFrame() {
        this.player1.setLocationRelativeTo((Component)null);
        this.player1Panel.setLayout(new GridLayout(2, 1));
        this.player1.getContentPane().setLayout(new BorderLayout());
        this.player1.setDefaultCloseOperation(3);
        this.player1.setTitle("White player");
        this.panel1.setLayout(new GridLayout(8, 8));
        this.player1.add(this.message1, "North");
        this.player1.add(this.panel1, "Center");
        this.player1.add(this.player1Panel, "South");
        this.AIWhite.addActionListener(new ButtonPressed(this));
        this.player1Panel.add(this.AIWhite);
        this.restart1.addActionListener(new ButtonPressed(this));
        this.player1Panel.add(this.restart1);
        this.player1.setMinimumSize(new Dimension(400, 330));
        this.player2.setLocation(this.player1.getX() + this.player1.getWidth(), this.player1.getY());
        this.player2.getContentPane().setLayout(new BorderLayout());
        this.player2.setDefaultCloseOperation(3);
        this.player2.setTitle("Black player");
        this.player2Panel.setLayout(new GridLayout(2, 1));
        this.panel2.setLayout(new GridLayout(8, 8));
        this.player2.add(this.message2, "North");
        this.player2.add(this.panel2, "Center");
        this.player2.add(this.player2Panel, "South");
        this.AIBlack.addActionListener(new ButtonPressed(this));
        this.player2Panel.add(this.AIBlack);
        this.restart2.addActionListener(new ButtonPressed(this));
        this.player2Panel.add(this.restart2);
        this.player2.setMinimumSize(new Dimension(400, 330));

        int y;
        int x;
        GUISquareButton but;
        for(y = 0; y < this.Height; ++y) {
            for(x = 0; x < this.Width; ++x) {
                but = new GUISquareButton(50, 50, Color.green, 1, Color.black);
                but.storeButton(x, y, 1);
                but.getClass();
                but.addActionListener(new GUISquareButton.ButtonPressed(but, this.controller));
                but.setcolorOval(0);
                this.Board1[x + y * this.Width] = but;
                this.panel1.add(but);
            }
        }

        for(y = this.Height - 1; y >= 0; --y) {
            for(x = this.Width - 1; x >= 0; --x) {
                but = new GUISquareButton(50, 50, Color.green, 1, Color.black);
                but.storeButton(x, y, 2);
                but.getClass();
                but.addActionListener(new GUISquareButton.ButtonPressed(but, this.controller));
                but.setcolorOval(0);
                this.Board2[x + y * this.Width] = but;
                this.panel2.add(but);
            }
        }

        this.player1.pack();
        this.player1.setVisible(true);
        this.player2.pack();
        this.player2.setVisible(true);
    }*/

    public void refreshView() {

        for(int i = 0; i < 10; i++) {
           for(int j = 0; j < 10; j++) {
                grounds[(10 * i) + j].refresh();
           }
        }
    }
}
