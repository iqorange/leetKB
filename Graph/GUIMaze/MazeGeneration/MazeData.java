package Graph.GUIMaze.MazeGeneration;

// 生成迷宫
public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';
    // 行和列
    private int N, M;
    // 迷宫数组
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] inMist;

    // 入口和出口
    private int entranceX, entranceY;
    private int exitX, exitY;

    public MazeData(int N, int M) {
        if (N%2 == 0 || M%2 == 0){
            throw new IllegalArgumentException("我们的迷宫行与列必须是奇数～");
        }
        this.N = N;
        this.M = M;
        maze = new char[N][M];
        visited = new boolean[N][M];
        inMist = new boolean[N][M];
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                // 初始化迷宫数组
                if (i%2==1 && j%2==1){
                    maze[i][j] = ROAD;
                }else{
                    maze[i][j] = WALL;
                }
                visited[i][j] = false;
                inMist[i][j] = true;
            }
        }
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
        // 入口和出口
        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y){
        return x>=0 && x<N && y>=0 && y<M;
    }

    public void openMist(int x, int y){
        if (!inArea(x, y)){
            throw new IllegalArgumentException("x or y is out of index in openMist~");
        }
        for (int i=x-1;i<=x+1;i++){
            for (int j=y-1;j<=y+1;j++){
                if (inArea(i, j)){
                    inMist[i][j] = false;
                }
            }
        }
    }
}
