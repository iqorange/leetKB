package Graph.ShortestPath;

import Graph.GraphBase.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// BellmanFord处理负权边问题
public class BellmanFord {
    private WeightedGraph G;
    private int s;
    private int[] distance;
    // 记录路径前面的点用于求解最短路径
    private int[] precede;
    // 标记是否存在负权环
    private boolean hasNegativeCycle = false;

    public BellmanFord(WeightedGraph G, int s){
        this.G = G;
        G.validateVertex(s);
        this.s = s;
        distance = new int[G.V()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        // pre开空间
        precede = new int[G.V()];
        Arrays.fill(precede, -1);
        // V-1轮对所有边进行松弛操作
        for (int pass=1;pass<G.V();pass++){
            for (int v=0;v<G.V();v++){
                for (int w: G.adj(v)){
                    // 无限值的判断，防止溢出
                    if (distance[v] != Integer.MAX_VALUE && distance[v] + G.getWeight(v, w) < distance[w]){
                        distance[w] = distance[v] + G.getWeight(v, w);
                        // 记录记录当前节点的上一节点
                        precede[w] = v;
                    }
                }
            }
        }
        // 负权环判断
        for (int v=0;v<G.V();v++){
            for (int w: G.adj(v)){
                // 无限值的判断，防止溢出
                if (distance[v] != Integer.MAX_VALUE && distance[v] + G.getWeight(v, w) < distance[w]){
                    // 如果找到更小的值，则含有负权环
                    hasNegativeCycle = true;
                }
            }
        }
    }

    public boolean isHasNegativeCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return distance[v] != Integer.MAX_VALUE;
    }

    public int dostTo(int v){
        G.validateVertex(v);
        if (hasNegativeCycle) throw new RuntimeException("Exist negative cycle!");
        return distance[v];
    }

    public Iterable<Integer> parh(int temp){
        ArrayList<Integer> result = new ArrayList<>();
        if (!isConnectedTo(temp)) return result;
        int current = temp;
        while (current != s){
            result.add(current);
            current = precede[current];
        }
        result.add(s);
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("./src/Graph/Data/g8.txt");
        BellmanFord bellmanFord = new BellmanFord(g, 0);
        if (!bellmanFord.isHasNegativeCycle()){
            for (int v=0;v<g.V();v++){
                System.out.print(bellmanFord.dostTo(v) + " ");
            }
            System.out.println();
            System.out.println(bellmanFord.parh(3));
        }else{
            System.out.println("Exist negative cycle.");
        }
    }
}
