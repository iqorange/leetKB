package Graph;

import java.util.ArrayList;

// 无向图的环检测
public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    // 环检测
    private boolean hasCycle = false;

    public CycleDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int i=0;i<G.V();i++){
            if (!visited[i]){
                if (dfs(i, i)){
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // 从顶点v开始，判断图中是否有环
    private boolean dfs(int v, int parent){
        visited[v] = true;
        for (int w: G.adj(v)){
            if (!visited[w]){
                if (dfs(w, v)) return true;
            } else if (w != parent){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println("图中是否有环：" + cycleDetection.hasCycle());
    }
}

