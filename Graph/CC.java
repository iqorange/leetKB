package Graph;

import java.util.ArrayList;

// 图的深度优先遍历(计算连通分量)
public class CC {
    private Graph G;
    private int[] visited;
    // 建立连通分量
    private int ccCount = 0;

    public CC(Graph G){
        this.G = G;
        visited = new int[G.V()];
        for (int i=0;i<visited.length;i++){
            visited[i] = -1;
        }
        for (int i=0;i<G.V();i++){
            if (visited[i] == -1){
                // 类似并查集的形式
//                dfs(i, i);
                // 计数的形式
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
//        for (int e: visited){
//            System.out.print(e + " ");
//        }
//        System.out.println();
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

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        CC cc = new CC(g);
        System.out.println("连通分量：" + cc.count());
        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(0, 5));

        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0;ccid<comp.length;ccid++){
            System.out.print(ccid + ": ");
            for (int w: comp[ccid]){
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
