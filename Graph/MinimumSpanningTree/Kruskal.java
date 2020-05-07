package Graph.MinimumSpanningTree;

import Graph.GraphBase.WeightedGraph;

import java.util.ArrayList;
import java.util.Collections;

// Kruskal算法求解最小生成树
public class Kruskal {
    private WeightedGraph G;
    // 带权边
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<WeightedEdge>();
        CC cc = new CC(G);
        // 不联通的情况
        if (cc.count()>1){
            return;
        }
        // Kruskal
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        // 放入所有的边
        for (int v = 0;v<G.V();v++){
            for (int w: G.adj(v)){
                if (v < w){
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
                }
            }
        }
        // 对边进行排序
        Collections.sort(edges);
        // 快速环判断
        UF uf = new UF(G.V());
        // 取出边
        for (WeightedEdge edge: edges){
            int v = edge.getV();
            int w = edge.getW();
            // 如果不联通的话，取出这条最小生成树的边（切分定理）
            if (!uf.isConnected(v, w)){
                mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("./src/Graph/g1.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
