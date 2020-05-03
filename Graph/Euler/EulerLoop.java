package Graph.Euler;

import Graph.GraphBase.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

// 欧拉回路
public class EulerLoop {
    private Graph G;
    public EulerLoop(Graph G){
        this.G = G;
    }

    // 判断是否存在欧拉回路
    public boolean hasEulerLoop(){
        // 判断是否是连通图，复用CC
        CC cc = new CC(G);
        // 如果有多个连通分量
        if (cc.count() > 1){
            return false;
        }
        // 遍历顶点
        for (int v=0;v<G.V();v++){
            // 如果这个顶点的度数是奇数
            if (G.degree(v)%2 == 1){
                return false;
            }
        }
        // 都是顶点的度数都是偶数时存在欧拉回路
        return true;
    }

    // Hierholzer算法求解欧拉回路
    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()){
            return res;
        }
        Graph g = (Graph) G.clone();
        Stack<Integer> stack = new Stack<>();
        // 设定起始点
        int curV = 0;
        stack.push(curV);
        while (!stack.empty()){
            if (g.degree(curV) != 0){
                stack.push(curV);
                int w = g.adj(curV).iterator().next();
                g.removeEdge(curV, w);
                curV = w;
            }else{
                res.add(curV);
                curV = stack.pop();
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/Euler/g2.txt");
        EulerLoop eulerLoop = new EulerLoop(g);
        System.out.println(eulerLoop.result());
    }
}
