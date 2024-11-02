import java.awt.Color;

public class Model {
    Controller controller;
    View view;
    int numberOfClicks = 0;
    
    int[] Ground = new int[100];



    /**
     * ground = 0
     * plant = 1
     */

    public Model() {

    }

    public void incNumberOfClicks() {
        this.numberOfClicks++;
    }

    public int getNumberOfClicks() {
        return this.numberOfClicks;
    }

    public Boolean canClick() {
        return (numberOfClicks <= 5);
    }

    public void setPlant(int plant, int x, int y) {
        Ground[(10*x)+y] = plant;
    }

    public void initialise(View view, Controller controller)  {

    }

    public Color getButtonColor(int x, int y) {
        return null;
    }

    public String getButtonPlant(int x, int y) {
        return null;
    }
}
