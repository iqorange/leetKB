package Graph.BridgeAndCutPoints;

import Graph.GraphBase.Graph;

import java.util.HashSet;
import java.util.Set;

// 寻找割点（DFS）
public class FindCutPoints {
    private Graph G;
    private boolean[] visited;

    // 记录每一个顶点的遍历顺序
    private int ord[];
    // 通过另外一条路来到达顶点的order
    private int low[];
    // 记录遍历的顶点数
    private int cnt;
    // 存储割点
    private Set<Integer> res;

    public FindCutPoints(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        res = new HashSet<>();
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
        int child = 0;
        for (int w: G.adj(v)){
            if (!visited[w]){
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                // 对跟节点进行特殊判断
                if (v != parent &&low[w] >= ord[v]){
                    res.add(v);
                }
                child++;
                if (v == parent && child > 1){
                    res.add(v);
                }
            }else if (w != parent){
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public Iterable<Integer> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g6.txt");
        FindCutPoints fc = new FindCutPoints(g);
        System.out.println(fc.result());
    }
}
