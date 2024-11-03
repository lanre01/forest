import java.awt.Color;

public class Model {
    Controller controller;
    View view;
    int numberOfClicks = 0;
    int[] Ground = new int[View.mapSize*View.mapSize];
    int[] GroundValue = new int[View.mapSize*View.mapSize];
    Color[] Colors = new Color[View.mapSize*View.mapSize];
    double[] soiValue = new double[View.mapSize*View.mapSize];

    int[] plantCount = {0 , 0 , 0, 0, 0};

    /**
     * ground = 0
     * plant = 1
     */

    public Model() {

    }

    public void incNumberOfClicks(int x, int y) {

        this.numberOfClicks++;
        this.GroundValue[(View.mapSize*x) + y] = numberOfClicks;

    }

    public int getNumberOfClicks() {
        return this.numberOfClicks;
    }

    public Boolean canClick() {
        return (numberOfClicks <= 4);
    }

    public void setPlant(int plant, int x, int y) {
        Ground[(View.mapSize*x)+y] = plant;
    }

    public void initialise(View view, Controller controller)  {

    }

    public double getSoilValue(int x, int y) {
        return soiValue[(View.mapSize * x) + y];
    }


    public void setSoilValue(double soil, int x, int y) {
        this.soiValue[(View.mapSize*x) + y] = soil;
    }


    public Color getButtonColor(int x, int y) {
        return Colors[(View.mapSize*x) + y];
    }
    public void setButtonColor(Color color, int x, int y) {
        Colors[(View.mapSize * x) + y] = color;
    }

    public String getButtonPlant(int x, int y) {
        return null;
    }

    public int[] getPlantCount() {return plantCount;}

    public void setPlantCount(int plant) {
        if(plant <= 0) return;

        plantCount[plant - 1]++;
    }

    public void setPlantDecreaseCount(int plant) {
        if(plant <= 0) return;

        plantCount[plant - 1]--;
    }
}
