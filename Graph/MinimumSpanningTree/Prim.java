package Graph.MinimumSpanningTree;

import Graph.GraphBase.WeightedGraph;

import java.util.ArrayList;

// Prim求解最小生成树
public class Prim {
    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count()>1){
            return;
        }

        // Prim
        boolean[] visited = new boolean[G.V()];
        // 切分
        visited[0] = true;
        // 循环v-1次
        for (int i=1;i<G.V();i++){
            // 记录最小权值的横切遍
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            // 找横切边的最短边
            for (int v=0;v<G.V();v++){
                if (visited[v]){
                    for (int w: G.adj(v)){
                        if (!visited[w] && G.getWeight(v, w) < minEdge.getWeight()){
                            minEdge = new WeightedEdge(v, w, G.getWeight(v, w));
                        }
                    }
                }
            }
            mst.add(minEdge);
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("./src/Graph/g1.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
