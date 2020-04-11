package Graph.DFS;

import Graph.GraphBase.Graph;

// 二分图检测
public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    // 对节点进行染色
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i=0;i<G.V();i++){
            colors[i] = -1;
        }
        for (int i=0;i<G.V();i++){
            if (!visited[i]){
                if (dfs(i, 0)){
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;
        for (int w: G.adj(v)){
            if (!visited[w]){
                if (!dfs(w, 1-color)){
                    return false;
                }else if (colors[w] == colors[v]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite());
    }
}
