package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 单源路径，提前结束递归
public class Path {
    private Graph G;
    private int s;
    private int t;
    private boolean[] visited;
    private int[] pre;

    public Path(Graph G, int s, int t){
        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        dfs(s, s);
        for (boolean e: visited){
            System.out.print(e + " ");
        }
        System.out.println();
    }

    private boolean dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        // 判断是否到终止点
        if (v == t){
            return true;
        }
        for (int w: G.adj(v)){
            if (!visited[w]){
                if (dfs(w, v)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) return res;
        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        Path path = new Path(g, 0, 1);
        System.out.println("从0到1的路径：" + path.path());
    }
}
