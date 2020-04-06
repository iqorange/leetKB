package Graph;

import java.util.ArrayList;

// 图的深度优先遍历(计算连通分量)
public class CC {
    private Graph G;
    private boolean[] visited;
    // 建立连通分量
    private int ccCount = 0;

    public CC(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int i=0;i<G.V();i++){
            if (!visited[i]){
                dfs(i);
                ccCount++;
            }
        }
    }

    private void dfs(int v){
        visited[v] = true;
        for (int w: G.adj(v)){
            if (!visited[w]){
                dfs(w);
            }
        }
    }

    public int count(){
        return ccCount;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        CC cc = new CC(g);
        System.out.println("连通分量：" + cc.count());
    }
}
