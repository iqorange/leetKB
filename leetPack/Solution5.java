package leetPack;

import java.util.HashSet;

public class Solution5 {
    // 785. 判断二分图
    private boolean[] visited;
    private int[] colors;
    private int[][] graph;
    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];
        for (int v = 0;v<V;v++){
            if (!visited[v]){
                if (!dfs(v, 0)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;
        for (int w: graph[v]){
            if (!visited[w]){
                if (!dfs(w, 1-color)){
                    return false;
                }
            }else if (colors[v] == colors[w]){
                return false;
            }
        }
        return true;
    }

    // 695. 岛屿的最大面积
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private int [][] grid;
    private HashSet<Integer>[] G;
    private boolean[] visiteds;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null){
            return 0;
        }
        R = grid.length;
        if (R == 0){
            return 0;
        }
        C = grid[0].length;
        if (C == 0){
            return 0;
        }
        this.grid = grid;
        // 创建图结构
        G = constructGraph();
        int res = 0;
        visiteds = new boolean[G.length];
        for (int v=0;v<G.length;v++){
            int x = v/C, y = v%C;
            if (!visiteds[v] && grid[x][y] == 1){
                res = Math.max(res, dfsA(v));
            }
        }
        return res;
    }
    private int dfsA(int v){
        visiteds[v] = true;
        int res = 1;
        for (int w: G[v]){
            if (!visiteds[w]){
                res += dfsA(w);
            }
        }
        return res;
    }
    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g = new HashSet[R*C];
        for (int i=0;i<g.length;i++){
            g[i] = new HashSet<>();
        }
        for (int v = 0;v<g.length;v++){
            int x = v/C, y = v%C;
            if (grid[x][y] == 1){
                for (int d=0;d<4;d++){
                    int nextX = x + dirs[d][0];
                    int nextY = y + dirs[d][1];
                    if (inArea(nextX, nextY) && grid[nextX][nextY] == 1){
                        int next = nextX*C + nextY;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
        }
        return g;
    }
    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
