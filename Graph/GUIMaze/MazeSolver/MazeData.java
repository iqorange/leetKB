package Graph.GUIMaze.MazeSolver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    // 迷宫的高和宽
    private int N, M;
    // 迷宫矩阵
    private char[][] maze;

    // 迷宫入口坐标
    private int entranceX, enreanceY;
    // 迷宫出口坐标
    private int exitX, exitY;

    // 标记之前是否访问过
    public boolean[][] visited;
    // 标记访问过的路径
    public boolean[][] path;

    public boolean[][] result;

    // 读取相应的文件进行迷宫初始化
    public MazeData(String filename){
        // 判断用户传进来的文件路径是否为空
        if(filename == null) {
            throw new IllegalArgumentException("Filename can not be null!");
        }
        // 构建扫描器，初始化为空
        Scanner scanner = null;
        try{
            // 读取文件并捕获读取异常
            File file = new File(filename);
            if(!file.exists()) {
                // 如果文件不存在
                throw new IllegalArgumentException("File " + filename + " doesn't exist");
            }
            // 将文件转换为文件的输入流
            FileInputStream fis = new FileInputStream(file);
            // 扫描器使用utf8的编码方式读取文件输入流
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            // 读取第一行
            String nmline = scanner.nextLine();
            // 正则表达式寻找空白字符，存储分割结果nm
            String[] nm = nmline.trim().split("\\s+");
            // System.out.print(nm[0] + ' ' + nm[1]);

            // 读取第一行的参数
            // 存储第0个元素到N
            N = Integer.parseInt(nm[0]);
            // System.out.println("N = " + N);
            // 存储第1个元素到M
            M = Integer.parseInt(nm[1]);
            // System.out.println("M = " + M);

            // 读取后续的N行
            maze = new char[N][M];
            // 给访问数组开空间，默认false
            visited = new boolean[N][M];
            // 给走过的路径开空间，默认false
            path = new boolean[N][M];
            result = new boolean[N][M];
            for(int i = 0 ; i < N ; i ++){
                // 读取每一行
                String line = scanner.nextLine();

                // 每行保证有M个字符
                if(line.length() != M) {
                    throw new IllegalArgumentException("Maze file " + filename + " is invalid");
                }
                // 构造迷宫矩阵
                for(int j = 0 ; j < M ; j ++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                    result[i][j] = false;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(scanner != null) {
                // 关闭扫描器维护内存安全性
                scanner.close();
            }
        }
        entranceX = 1;
        enreanceY = 0;
        exitX = N - 2;
        exitY = M - 1;
    }

    public int N(){ return N; }
    public int M(){ return M; }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEnreanceY() {
        return enreanceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    // 获取迷宫矩阵对应的值
    public char getMaze(int i, int j){
        // 合法性校验
        if(!inArea(i,j)) {
            throw new IllegalArgumentException("i or j is out of index in getMaze!");
        }
        return maze[i][j];
    }

    // 判断x、y坐标值是否在迷宫中
    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    // 控制台中输入迷宫信息
    public void print(){
        System.out.println(N + " " + M);
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}