package Graph.MinimumSpanningTree;

import Graph.GraphBase.WeightedGraph;

import java.util.ArrayList;

// 带权图的深度优先遍历(计算连通分量)
public class CC {
    private WeightedGraph G;
    private int[] visited;
    private int ccCount = 0;

    public CC(WeightedGraph G){
        this.G = G;
        visited = new int[G.V()];
        for (int i=0;i<visited.length;i++){
            visited[i] = -1;
        }
        for (int i=0;i<G.V();i++){
            if (visited[i] == -1){
                dfs(i, ccCount);
                ccCount++;
            }
        }
    }

    private void dfs(int v, int ccid){
        visited[v] = ccid;
        for (int w: G.adj(v)){
            if (visited[w] == -1){
                dfs(w, ccid);
            }
        }
    }

    public int count(){
        return ccCount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i=0;i<ccCount;i++){
            res[i] = new ArrayList<>();
        }
        for (int v=0;v<G.V();v++){
            res[visited[v]].add(v);
        }
        return res;
    }
}
