package Graph.Hamilton;


import Graph.GraphBase.Graph;

import java.util.ArrayList;
import java.util.Collections;

// 哈密尔顿回路
public class HamiltonLoop {
    private Graph G;
    // 记录访问节点的父亲节点
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G){
        this.G = G;
        int visited = 0;
        pre = new int[G.V()];
        end = -1;
        // 从一点出发
        dfs(visited, 0, 0, 0);
    }

    private boolean dfs(int visited, int v, int parent, int visits){
        visited += (1<<v);
        visits++;
        // 直接判断是否是最后一点并且存在遍
        if (visits == G.V() && G.hasEdge(v, 0)){
            end = v;
            return true;
        }
        pre[v] = parent;
        for (int w: G.adj(v)){
            if ((visited & (1<<w)) == 0){
                if (dfs(visited, w, v, visits)){
                    return true;
                }
            }
//            else if (w == 0 && visits == G.V()){
//                // 刚好回到了起始点并且所有点都经过了
//                end = v;
//                return true;
//            }
        }
        // 回溯
        return false;
    }

//    private boolean allVisited(){
//        for (boolean e: visited){
//            if (!e){
//                return false;
//            }
//        }
//        return true;
//    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1){
            return res;
        }
        int cur = end;
        while (cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g7.txt");
        HamiltonLoop loop = new HamiltonLoop(g);
        System.out.println(loop.result());
    }
}
