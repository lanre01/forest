import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;

public class View extends JFrame {
    Model model;
    Controller controller;
    Ground[] grounds = new Ground[100];

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
        this.model = model;
        this.controller = controller;


        setTitle("Farmland Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 10));
        setSize(500, 500); // Set a size for the window

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                Ground button = new Ground(this.model, this.controller,this, i, j);
                grounds[(10 * i) + j] = button;
                model.setSoilValue(Math.random(), i, j);
                add(button); // Add button to the frame directly\
            }
        }

        // Make the frame visible
        setVisible(true);
    }

    public void feedbackToUser() {}


    public void refreshView() {

        for(int i = 0; i < 10; i++) {
           for(int j = 0; j < 10; j++) {
                grounds[(10 * i) + j].refresh();
           }
        }
    }
}
