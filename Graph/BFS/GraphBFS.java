package Graph.BFS;

import Graph.GraphBase.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 广度优先遍历
// 时间复杂度：O(V+E)
public class GraphBFS {
    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int v=0;v<G.V();v++){
            if (!visited[v]){
                bfs(v);
            }
        }
    }

    private void bfs(int s){
        // 使用队列实现广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()){
            int v = queue.remove();
            order.add(v);
            for (int w: G.adj(v)){
                if (!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args){
        Graph g = new Graph("./src/Graph/g3.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS Order: " + graphBFS.order);
    }
}
