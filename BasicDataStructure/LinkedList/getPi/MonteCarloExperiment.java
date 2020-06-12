package BasicDataStructure.LinkedList.getPi;

import java.awt.*;

public class MonteCarloExperiment {
    private int squareSide;
    private int N;
    private int outputInterval = 100;

    public MonteCarloExperiment(int squareSide, int n) {
        if (squareSide <= 0 || n <= 0){
            throw new IllegalArgumentException("SquareSide and N must larger than 0!");
        }
        this.squareSide = squareSide;
        N = n;
    }

    public void setOutputInterval(int outputInterval) {
        if (outputInterval<0){
            throw new IllegalArgumentException("OutputInterval must larger than 0!");
        }
        this.outputInterval = outputInterval;
    }

    public void run(){
        Circle circle = new Circle(squareSide/2, squareSide/2, squareSide/2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for (int i=0;i<N;i++){
            if (i%outputInterval == 0){
                System.out.println(data.estimatePi());
            }
            int x = (int)(Math.random() * squareSide);
            int y = (int)(Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        int squareSide = 800;
        int N = 10000000;
        MonteCarloExperiment experiment = new MonteCarloExperiment(squareSide, N);
        experiment.setOutputInterval(100000);
        experiment.run();
    }
}
