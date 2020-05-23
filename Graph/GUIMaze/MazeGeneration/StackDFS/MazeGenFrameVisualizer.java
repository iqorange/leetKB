package Graph.GUIMaze.MazeGeneration.StackDFS;

import Graph.GUIMaze.MazeGeneration.MazeGenFrameVisHelper;
import Graph.GUIMaze.MazeGeneration.MazeData;
import java.awt.*;
import java.util.Stack;

// 控制层
public class MazeGenFrameVisualizer {
    // 渲染间隔时间
    private static int DELAY = 5;
    // 小方块的边长
    private static int blockSide = 8;
    // 四个方向
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

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
        setdata(-1, -1);
        Stack<Position> stack = new Stack<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        stack.push(first);
        data.visited[first.getX()][first.getY()] = true;
        while (!stack.empty()){
            Position curPos = stack.pop();
            for (int i=0;i<4;i++){
                int newX = curPos.getX() + d[i][0] * 2;
                int newY = curPos.getY() + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]){
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    setdata(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
        setdata(-1, -1);
    }

    private void setdata(int x, int y){
        if (data.inArea(x, y)){
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        MazeGenFrameVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int N = 101;
        int M = 101;

        MazeGenFrameVisualizer visualizer = new MazeGenFrameVisualizer(N, M);
    }
}
