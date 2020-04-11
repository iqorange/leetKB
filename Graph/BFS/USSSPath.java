package Graph.BFS;

import Graph.GraphBase.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// 无权图的单源最短路径
// Unweighted Single Source Shortest Path
public class USSSPath {
    private Graph G;
    private boolean[] visited;
    private int s;
    private int[] pre;
    private  int[] distance;

    public USSSPath(Graph G, int s){
        this.G = G;
        this.s = s;
        distance = new int[G.V()];
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i=0;i<G.V();i++){
            pre[i] = -1;
            distance[i] = -1;
        }
        bfs(s);

        for (int i=0;i<G.V();i++){
            System.out.print(distance[i] + " ");
        }
        System.out.println();
    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        distance[s] = 0;
        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int w: G.adj(v)){
                if (!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    distance[w] = distance[v] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) return res;
        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    // 最短路径的长度
    public int distance(int t){
        G.validateVertex(t);
        return distance[t];
    }

    public static void main(String[] args){
        Graph g = new Graph("./src/Graph/g3.txt");
        USSSPath singleSourcePath = new USSSPath(g, 0);
        System.out.println("0 -> 6最短路径的距离: " + singleSourcePath.distance(6));
    }
}
