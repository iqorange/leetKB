package Graph.MinimumSpanningTree;

import Graph.GraphBase.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

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
        // 优先队列获得权值最小的边
        Queue pq = new PriorityQueue<WeightedEdge>();
        // 初始情况，取出0的所有横切边
        for(int w: G.adj(0)) {
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));
        }
        while(!pq.isEmpty()){

            WeightedEdge minEdge = (WeightedEdge) pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }

            mst.add(minEdge);

            int newv = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newv] = true;
            for(int w: G.adj(newv)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newv, w, G.getWeight(newv, w)));
                }
            }
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
