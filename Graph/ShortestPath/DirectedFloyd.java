package Graph.ShortestPath;

import Graph.GraphBase.DirectedWeightedGraph;
import Graph.GraphBase.WeightedGraph;

import java.util.Arrays;

// Floyd算法 Warshall Roy
// 可以求解任意两点的最短路径
// 可以求解图的直径（路径最大值）
// 可以包含/检测负权环
// 时间复杂度：O(VVV)
public class DirectedFloyd {
    // 无向带权图
    private DirectedWeightedGraph G;
    // 距离数组v-w
    private int[][] distance;
    // 标记是否有负权环
    private boolean hasNegativeCycle = false;

    public DirectedFloyd(DirectedWeightedGraph G){
        this.G = G;
        distance = new int[G.V()][G.V()];
        // 初始化
        for (int i=0;i<G.V();i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        for (int v=0;v<G.V();v++){
            // 所有点到自己的距离为0
            distance[v][v] = 0;
            // 如果存在边则存在这条边的权值
            for (int w: G.adj(v)){
                distance[v][w] = G.getWeight(v, w);
            }
        }
        // Floyd
        for (int t=0;t<G.V();t++){
            for (int v=0;v<G.V();v++){
                for (int w=0;w<G.V();w++){
                    // 防止整型溢出
                    if (distance[v][t] != Integer.MAX_VALUE && distance[t][w] != Integer.MAX_VALUE && distance[v][t] + distance[t][w] < distance[v][w]){
                        distance[v][w] = distance[v][t] + distance[t][w];
                    }
                }
            }
        }
        for (int v=0;v<G.V();v++){
            if (distance[v][v] < 0){
                hasNegativeCycle = true;
                break;
            }
        }
    }

    public boolean isHasNegativeCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnectedTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return distance[v][w] != Integer.MAX_VALUE;
    }

    public int distanceTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return distance[v][w];
    }

    public static void main(String[] args) {
        // 测试有向图
        DirectedWeightedGraph g = new DirectedWeightedGraph("./src/Graph/Data/wg.txt", true);
        DirectedFloyd floyd = new DirectedFloyd(g);
        if (!floyd.isHasNegativeCycle()){
            for (int v=0;v<g.V();v++){
                for (int w=0;w<g.V();w++){
                    System.out.print(floyd.distanceTo(v, w) + " ");
                }
                System.out.println();
            }
        }else{
            System.out.println("Exist negative cycle.");
        }
    }
}
