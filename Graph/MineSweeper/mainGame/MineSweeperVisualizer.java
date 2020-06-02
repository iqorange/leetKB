package Graph.MineSweeper.mainGame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class MineSweeperVisualizer {
    private static int DELAY = 5;
    private static int blockSide = 32;
    private MineSweeperData data;
    private MineSweeperFrame frame;

    public MineSweeperVisualizer(int N, int M, int mineNumber){
        data = new MineSweeperData(N, M, mineNumber);
        int sceneWidth = M * blockSide;
        int sceneHeight = N * blockSide;
        EventQueue.invokeLater(() -> {
            frame = new MineSweeperFrame("Mine Sweeper", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        setData();
    }

    private void setData(){
        frame.render(data);
        MineSweeperVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int N = 20;
        int M = 20;
        int mineNumber = 1;

        MineSweeperVisualizer visualizer = new MineSweeperVisualizer(N, M, mineNumber);
    }
}
