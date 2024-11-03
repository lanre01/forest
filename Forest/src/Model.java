import java.awt.Color;

public class Model {
    Controller controller;
    View view;
    int numberOfClicks = 0;
    int[] Ground = new int[100];
    int[] GroundValue = new int[100];
    Color[] Colors = new Color[100];
    double[] soiValue = new double[100];



    /**
     * ground = 0
     * plant = 1
     */

    public Model() {

    }

    public void incNumberOfClicks(int x, int y) {

        this.numberOfClicks++;
        this.GroundValue[(10*x) + y] = numberOfClicks;

    }

    public int getNumberOfClicks() {
        return this.numberOfClicks;
    }

    public Boolean canClick() {
        return (numberOfClicks <= 4);
    }

    public void setPlant(int plant, int x, int y) {
        Ground[(10*x)+y] = plant;
    }

    public void initialise(View view, Controller controller)  {

    }

    public double getSoilValue(int x, int y) {
        return soiValue[(10 * x) + y];
    }


    public void setSoilValue(double soil, int x, int y) {
        this.soiValue[(10*x) + y] = soil;
    }


    public Color getButtonColor(int x, int y) {
        return Colors[(10*x) + y];
    }
    public void setButtonColor(Color color, int x, int y) {
        Colors[(10 * x) + y] = color;
    }

    public String getButtonPlant(int x, int y) {
        return null;
    }
}
