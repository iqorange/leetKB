package leetPack;

import java.util.LinkedList;
import java.util.Queue;

// 1091. 二进制矩阵中的最短路径
public class Solution7 {
    private int R, C;
    // 八个方向的差值
    private final int [][] directions = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };
    public int shortestPathBinaryMatrix(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        // 记录相对应的节点是否访问过
        boolean[][] visited = new boolean[R][R];
        // 记录每个顶点找到的最短路近的值
        int[][] distance = new int[R][C];
        // 起始点阻塞的情况
        if (grid[0][0] == 1){
            return -1;
        }
        // 当矩阵只有1X1大小
        if (R == 1 && C == 1){
            return 1;
        }

        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        // 添加起始点
        queue.add(0);
        visited[0][0] = true;
        distance[0][0] = 1;
        while (!queue.isEmpty()){
            int current = queue.remove();
            int curX = current / C, curY = current % C;
            // 遍历当前顶点的八个方向
            for (int d=0;d<8;d++){
                int nextX = curX + directions[d][0];
                int nextY = curY + directions[d][1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == 0){
                    queue.add(nextX*C + nextY);
                    visited[nextX][nextY] = true;
                    distance[nextX][nextY] = distance[curX][curY] + 1;
                    if (nextX == R-1 && nextY == C-1){
                        return distance[nextX][nextY];
                    }
                }
            }
        }
        return -1;
    }
    // 区域的合法性检查
    private boolean inArea(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
