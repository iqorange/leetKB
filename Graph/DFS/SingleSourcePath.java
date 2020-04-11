package Graph.DFS;

import Graph.GraphBase.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 单源路径
public class SingleSourcePath {
    private Graph G;
    // 单源路径问题中的源s
    private int s;
    private boolean[] visited;
    // 存储类中前面的顶点是谁
    private int[] pre;

    public SingleSourcePath(Graph G, int s){
        // 验证一下source源的合法性
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        // 记录一下遍历前面的顶点
        pre = new int[G.V()];
        // 赋予初值（类似并查集）
        Arrays.fill(pre, -1);
        // 直接从当前制定节点进行遍历，定义源的上一级是自己
        dfs(s, s);
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        // 记录一下上一个顶点
        pre[v] = parent;
        for (int w: G.adj(v)){
            if (!visited[w]){
                dfs(w, v);
            }
        }
    }

    // 对于t来说，源s到是否可达
    public boolean isConnectedTo(int t){
        // 验证t是否合法
        G.validateVertex(t);
        // 返回是否和源联通
        return visited[t];
    }

    // 寻找路径
    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        // 没有路径的情况下返回空链表
        if (!isConnectedTo(t)) return res;
        // 存在路径的情况下找路径
        // 从目标顶点开始找路径
        int cur = t;
        // 循环向前找
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        // 记录一下源头source
        res.add(s);
        // 颠倒链表
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(g, 0);
        System.out.println("从0到6的路径：" + singleSourcePath.path(6));
        System.out.println("从0到5的路径：" + singleSourcePath.path(5));
    }
}
