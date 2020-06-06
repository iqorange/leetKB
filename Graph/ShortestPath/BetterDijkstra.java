package Graph.ShortestPath;

import Graph.GraphBase.WeightedGraph;

import java.util.*;

// Dijkstra算法
// 时间复杂度：O(ElogE)
// 适用于处理没负权边的最短路径问题
public class BetterDijkstra {
    // 无向带权图
    private WeightedGraph G;
    // 原点
    private int s;
    // 数组存储每个顶点到原点s的距离
    private int[] distance;
    // 记录确定的节点
    private boolean[] visited;
    // 记录最短路径的上一节点
    private int[] pre;

    public class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }

    public BetterDijkstra(WeightedGraph G, int s){
        this.G = G;
        // 验证原点合法性
        G.validateVertex(s);
        this.s = s;
        // 初始化距离
        distance = new int[G.V()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        // 到自己的距离为0
        distance[s] = 0;
        // 原点的上一个节点是自己
        pre[s] = s;
        // 初始化确定节点数组
        visited = new boolean[G.V()];
        // 使用优先队列的方式寻找最小距离
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        priorityQueue.add(new Entry<>(s, 0));
        // Dijkstra算法循环
        while (!priorityQueue.isEmpty()){
            // 直接拿到distance小值定点
            int current = priorityQueue.remove().getKey();
            // 如果是已经处理过的节点
            if (visited[current]) continue;
            // 标记当前最小节点
            visited[current] = true;
            // 通过最小节点进行节点更新
            for (int w: G.adj(current)){
                if (!visited[w]){
                    if (distance[current] + G.getWeight(current, w) < distance[w]){
                        distance[w] = distance[current] + G.getWeight(current, w);
                        // 维护优先队列
                        priorityQueue.add(new Entry<>(w, distance[w]));
                        // 标记上一节点
                        pre[w] = current;
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

    // 记录路径
    public Iterable<Integer> path(int temp){
        ArrayList<Integer> result = new ArrayList<>();
        if (!isConnectedTo(temp)) return result;
        int current = temp;
        while (current != s){
            result.add(current);
            current = pre[current];
        }
        result.add(s);
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("./src/Graph/Data/g8.txt");
        BetterDijkstra betterDijkstra = new BetterDijkstra(g, 0);
        for (int v=0;v<g.V();v++){
            System.out.print(betterDijkstra.distanceTo(v) + " ");
        }
        System.out.println();
        System.out.println(betterDijkstra.path(3));
    }
}
