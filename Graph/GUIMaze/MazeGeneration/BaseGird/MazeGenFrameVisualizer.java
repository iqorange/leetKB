package Graph.GUIMaze.MazeGeneration.BaseGird;

import Graph.GUIMaze.MazeGeneration.MazeData;
import Graph.GUIMaze.MazeGeneration.MazeGenFrameVisHelper;

import java.awt.*;

// 控制层
public class MazeGenFrameVisualizer {
    // 渲染间隔时间
    private static int DELAY = 5;
    // 小方块的边长
    private static int blockSide = 8;

    private MazeData data;        // 数据
    private MazeGenFrame frame;    // 视图

    public MazeGenFrameVisualizer(int N, int M){

        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new MazeGenFrame("Random Maze Generation", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setdata();
    }

    private void setdata(){
        frame.render(data);
        MazeGenFrameVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int N = 101;
        int M = 101;

        MazeGenFrameVisualizer visualizer = new MazeGenFrameVisualizer(N, M);
    }
}
