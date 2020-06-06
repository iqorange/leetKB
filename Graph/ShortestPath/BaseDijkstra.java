package Graph.ShortestPath;

import Graph.GraphBase.WeightedGraph;

import java.util.Arrays;

// Dijkstra算法
// 时间复杂度：O(V^2)
// 适用于处理没负权边的最短路径问题
public class BaseDijkstra {
    // 无向带权图
    private WeightedGraph G;
    // 原点
    private int s;
    // 数组存储每个顶点到原点s的距离
    private int[] distance;
    // 记录确定的节点
    private boolean[] visited;

    public BaseDijkstra(WeightedGraph G, int s){
        this.G = G;
        // 验证原点合法性
        G.validateVertex(s);
        this.s = s;
        // 初始化距离
        distance = new int[G.V()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 到自己的距离为0
        distance[s] = 0;
        // 初始化确定节点数组
        visited = new boolean[G.V()];
        // Dijkstra算法循环
        while (true){
            // 当前找到的最小距离
            int currentDistance = Integer.MAX_VALUE;
            // 当前找到的最小节点
            int current = -1;
            // 找到当前还没确定最短路径中distance最短的节点
            for (int v=0;v<G.V();v++){
                // 第一次循环时会自动找到节点s
                if (!visited[v] && distance[v] < currentDistance){
                    // 更新最小距离
                    currentDistance = distance[v];
                    // 更新最小节点
                    current = v;
                }
            }
            // 遍历过当前所有节点
            if (current == -1){
                break;
            }
            // 标记当前最小节点
            visited[current] = true;
            // 通过最小节点进行节点更新
            for (int w: G.adj(current)){
                if (!visited[w]){
                    if (distance[current] + G.getWeight(current, w) < distance[w]){
                        distance[w] = distance[current] + G.getWeight(current, w);
                    }
                }
            }
        }
    }

    public boolean isConnectedTo(int v){
        // 验证合法性
        G.validateVertex(v);
        // 通过连通性来判断
        return visited[v];
    }

    public int distanceTo(int v){
        // 验证合法性
        G.validateVertex(v);
        // 直接找到最短路径，如果返回了int最大值说明没有路径
        return distance[v];
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("./src/Graph/Data/g8.txt");
        BaseDijkstra baseDijkstra = new BaseDijkstra(g, 0);
        for (int v=0;v<g.V();v++){
            System.out.print(baseDijkstra.distanceTo(v) + " ");
        }
        System.out.println();
    }
}
