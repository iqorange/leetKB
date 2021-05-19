package Graph.GUIMaze.MazeSolver;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Stack;

// 走迷宫
public class MazeSolverVisualizerDFS {

    private static int DELAY = 5;
    private static int blockSide = 8;

    private MazeData data;        // 数据
    private MazeSolverFrame frame;    // 视图

    // 位置偏移量
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public MazeSolverVisualizerDFS(String mazeFile){
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
        Stack<Position> stack = new Stack<>();
        Position entrance = new Position(data.getEntranceX(), data.getEnreanceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;
        boolean isSolved = false;
        while (!stack.empty()){
            Position currentPosition = stack.pop();
            setData(currentPosition.getX(), currentPosition.getY(), true);
            // 找到终点的情况
            if (currentPosition.getX() == data.getExitX() && currentPosition.getY() == data.getExitY()){
                isSolved = true;
                findPath(currentPosition);
                break;
            }
            for (int i=0;i<4;i++){
                int newX = currentPosition.getX() + d[i][0];
                int newY = currentPosition.getY() + d[i][1];
                if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.getMaze(newX, newY) == MazeData.ROAD){
                    stack.push(new Position(newX, newY, currentPosition));
                    data.visited[newX][newY] = true;
                }
            }
        }
        if (!isSolved){
            System.out.println("The maze has no solution~");
        }
        setData(-1, -1, false);
    }

    private void findPath(Position destination){
        Position current = destination;
        while (current != null){
            data.result[current.getX()][current.getY()] = true;
            current = current.getPrev();
        }
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
        MazeSolverVisualizerDFS visualizer = new MazeSolverVisualizerDFS(mazeFile);
    }
}