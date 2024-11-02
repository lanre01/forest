import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View {

    Model model;
    Controller controller;

    public View() {}

    public void initialise(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;

        JFrame frame = new JFrame("10x10 Button Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton(i + "," + j);
                button.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(
                                "Button pressed: " + button.getText()
                            );
                        }
                    }
                );
                frame.add(button);
            }
        }
    }

    public void feedbackToUser() {}

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
        /*for(int y = 0; y < this.Height; ++y) {
            for(int x = 0; x < this.Width; ++x) {
                int value = this.model.getBoardContents(x, y);
                if (value == 0) {
                    this.Board1[x + y * this.Width].setDrawOval(false);
                    this.Board2[x + y * this.Width].setDrawOval(false);
                } else {
                    this.Board1[x + y * this.Width].setDrawOval(true);
                    this.Board2[x + y * this.Width].setDrawOval(true);
                    this.Board1[x + y * this.Width].setcolorOval(value);
                    this.Board2[x + y * this.Width].setcolorOval(value);
                }
            }
        }

        this.panel1.repaint();
        this.panel2.repaint();*/
    }
}