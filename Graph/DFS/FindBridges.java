package Graph.DFS;

import Graph.GraphBase.Graph;

import java.util.ArrayList;

// 桥
public class FindBridges {
    private Graph G;
    private boolean[] visited;

    // 记录每一个顶点的遍历顺序
    private int ord[];
    // 通过另外一条路来到达顶点的order
    private int low[];
    // 记录遍历的顶点数
    private int cnt;
    // 存储边
    private ArrayList<Edge> res;

    public FindBridges(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        res = new ArrayList<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        // 对各个连通分量进行遍历
        for (int i=0;i<G.V();i++){
            if (!visited[i]){
                dfs(i, i);
            }
        }
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;
        for (int w: G.adj(v)){
            if (!visited[w]){
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]){
                    res.add(new Edge(v, w));
                }
            }else if (w != parent){
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g6.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println(fb.result());
    }
}
