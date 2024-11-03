import java.awt.*;

public class Controller {


    Model model;
    View view;
    double[] growthRate = new double[100];
    // the factors
    double[] HUMIDITY = new double[100];
    double[] RAINFALL = new double[100];
    double[] SUNLIGHT = new double[100];
    int[] Plants = new int[100];
    final Color[] InitColors = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.GRAY};


    public Controller() {
    }



    public void initialise(Model model, View view) {
        this.model = model;
        this.view = view;

    }

    public void startup() {
        // get the soil values - store it as initial growth rate
        // use an endless while loop to start manipulating the object
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                growthRate[(10 * i) + j] = model.getSoilValue(i, j);
            }
        }

        // run the for loop
        int count= 0;
        while(true) {
            this.propagationFunction(count, Plants[count]);
            int x = count / 10;
            int y = count - (10 * x);
            Color color = model.getButtonColor(x, y);
            if(color == null) {
                color = InitColors[Plants[count]];
            }

            float[] hsbVals = Color.RGBtoHSB(
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue(),
                    null);
            float brightness = (float)(0.2 + (0.8 * growthRate[count]));
            Color adjustedColor = Color.getHSBColor(hsbVals[0], hsbVals[1], brightness);
            model.setButtonColor(adjustedColor, x, y);

            view.refreshView();
            count++;
            count = count % 100;

        }


    }

    public void update() {


    }

    public void squareSelected(int player, int x, int y) {

    }



    public void doAutomatedMove(int play) {

    }

    private void propagationFunction(int index, int plant) {

        switch (plant) {
            case 0: return;
            case 1:
                // oak tree
                growthRate[index] += (((SUNLIGHT[index] > 0.9 || SUNLIGHT[index] < 0.6)? -SUNLIGHT[index] : SUNLIGHT[index]) +
                        ((RAINFALL[index] > 0.7 || RAINFALL[index] < 0.4)? -RAINFALL[index] : RAINFALL[index]) +
                        ((HUMIDITY[index] > 0.6 || HUMIDITY[index] < 0.4)? -HUMIDITY[index] : HUMIDITY[index])) / 3;
                break;
            case 2:
                // cactus
                growthRate[index] += (((SUNLIGHT[index] < 0.8)? -SUNLIGHT[index] : SUNLIGHT[index]) +
                        ((RAINFALL[index] > 0.3 || RAINFALL[index] < 0.1)? -RAINFALL[index] : RAINFALL[index]) +
                        ((HUMIDITY[index] > 0.4 || HUMIDITY[index] < 0.1)? -HUMIDITY[index] : HUMIDITY[index])) / 3;
                break;
            case 3:
                // fern
                growthRate[index] += (((SUNLIGHT[index] > 0.5 || SUNLIGHT[index] < 0.2)? -SUNLIGHT[index] : SUNLIGHT[index]) +
                        ((RAINFALL[index] > 0.9 || RAINFALL[index] < 0.7)? -RAINFALL[index] : RAINFALL[index]) +
                        ((HUMIDITY[index] > 0.9 || HUMIDITY[index] < 0.7)? -HUMIDITY[index] : HUMIDITY[index])) / 3;
                break;

            case 4:
                // pine tree
                growthRate[index] += (((SUNLIGHT[index] > 0.8 || SUNLIGHT[index] < 0.5)? -SUNLIGHT[index] : SUNLIGHT[index]) +
                        ((RAINFALL[index] > 0.6 || RAINFALL[index] < 0.3)? -RAINFALL[index] : RAINFALL[index]) +
                        ((HUMIDITY[index] > 0.5 || HUMIDITY[index] < 0.3)? -HUMIDITY[index] : HUMIDITY[index])) / 3;
                break;
            case 5:
                // rice plant
                growthRate[index] += (((SUNLIGHT[index] > 0.8 || SUNLIGHT[index] < 0.6)? -SUNLIGHT[index] : SUNLIGHT[index]) +
                        ((RAINFALL[index] > 0.9 || RAINFALL[index] < 0.7)? -RAINFALL[index] : RAINFALL[index]) +
                        ((HUMIDITY[index] > 0.9 || HUMIDITY[index] < 0.6)? -HUMIDITY[index] : HUMIDITY[index])) / 3;
                break;

        }
    }

    /**
     * map of plants to colors
     * oak tree - blue
     * cactus - green
     * fern - red
     * pine tree - yellow
     * rice plant - grey
     */







}
