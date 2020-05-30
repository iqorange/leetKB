package leetPack.SolutionsPack;

import java.util.Arrays;

public class Solution11 {
    // 980. 不同路径 III
    private int[][] grid;
    private int R, C;
    private int start, end;
    // 记忆化搜索
    private int[][] memory;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int visited = 0;
        int left = R * C;
        memory = new int[1<<(left)][left];
        for (int i=0;i<memory.length;i++){
            Arrays.fill(memory[i], -1);
        }
        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                if (grid[i][j] == 1){
                    start = i*C+j;
                    grid[i][j] = 0;
                }else if (grid[i][j] == 2){
                    end = i*C+j;
                    grid[i][j] = 0;
                }else if (grid[i][j] == -1){
                    left--;
                }
            }
        }
        return dfs(visited, start, left);
    }
    private int dfs(int visited, int v, int left){
        int x = v/C;
        int y = v%C;
        if (memory[visited][v] != -1){
            return memory[visited][v];
        }
        visited += (1<<v);
        left--;
        if (left == 0 && v == end){
            visited -= (1<<v);
            memory[visited][v] = 1;
            return 1;
        }
        int res = 0;
        for (int d=0;d<4;d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            int next = nextX*C+nextY;
            if (inArea(nextX, nextY) && grid[nextX][nextY] == 0 && (visited & (1<<next)) == 0) {
                res += dfs(visited, next, left);
            }
        }
        visited -= (1<<v);
        memory[visited][v] = res;
        return res;
    }
    private boolean inArea(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
