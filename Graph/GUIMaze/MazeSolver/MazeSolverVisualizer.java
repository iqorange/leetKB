package Graph.GUIMaze.MazeSolver;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

// 走迷宫
public class MazeSolverVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;

    private MazeData data;        // 数据
    private MazeSolverFrame frame;    // 视图

    // 位置偏移量
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public MazeSolverVisualizer(String mazeFile){
        data = new MazeData(mazeFile);
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;
        EventQueue.invokeLater(()->{
            frame = new MazeSolverFrame("Maze Solver Visualization", sceneWidth, sceneHeight);
            new Thread(()->{
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1, false);
        MazeSolverVisHelper.pause(10000);
        if (!go(data.getEntranceX(), data.getEnreanceY())){
            System.out.println("The maze has No solution~");
        }
        setData(-1, -1, false);
    }

    private boolean go(int x, int y){
        if (!data.inArea(x, y)){
            throw new IllegalArgumentException("x, y are out of index in go function");
        }
        data.visited[x][y] = true;
        setData(x, y, true);
        if (x == data.getExitX() && y == data.getExitY()){
            return true;
        }
        for (int i=0;i<4;i++){
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && data.getMaze(newX, newY) == MazeData.ROAD && !data.visited[newX][newY]){
                if (go(newX, newY)){
                    return true;
                }
            }
        }
        setData(x, y, false);
        return false;
    }

    private void setData(int x, int y, boolean isPath){
        if (data.inArea(x, y)){
            data.path[x][y] = isPath;
        }
        frame.render(data);
        MazeSolverVisHelper.pause(DELAY);
    }

    public static void main(String[] args){
        String mazeFile = "./Graph/GUIMaze/MazeSolver/maze_101_101.txt";
        MazeSolverVisualizer visualizer = new MazeSolverVisualizer(mazeFile);
    }
}
