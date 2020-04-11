package Graph.GraphBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// 邻接矩阵
// 处理无向无权图简单图
// 空间复杂度：O(V^2)
// 时间复杂度：
// *  建图：O(E)
// *  查看两点是否相邻：O(1)
// *  求一个点的相邻节点：O(V)
// **
public class AdjMatrix {
    // 表示整个图有V个顶点
    private int V;
    // 表示整个图有E条边
    private int E;
    // 用数组形式创建邻接矩阵
    private int[][] adj;

    // 通过读取一个文件来输入数据
    public AdjMatrix(String fileName){
        // 开始读取文件，使用java.io.File类
        File file = new File(fileName);
        // JDK7特性，自动关闭Scanner资源
        try(Scanner scanner = new Scanner(file);) {
            // 读取顶点信息，该数字是文件第一行第一个
            V = scanner.nextInt();
            if (V < 0){
                throw new IllegalArgumentException("V must be non-negative");
            }
            // 实例化一个大小为V*V方阵的邻接矩阵
            adj = new int[V][V];
            // 读取边的数量，该数字是文件第一行第二个
            E = scanner.nextByte();
            if (E < 0){
                throw new IllegalArgumentException("V must be non-negative");
            }
            // 逐个读取每一个边的信息
            for (int i=0;i<E;i++){
                // 读取每一行的两个数据
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                // 判断是否为自环边
                if (a == b){
                    throw new IllegalArgumentException("Self loop is detected!");
                }
                // 判断是否平行边
                if (adj[a][b] == 1){
                    throw new IllegalArgumentException("Parallel edges are detected");
                }
                // 对相应的顶点进行无向图的连接
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 判断顶点序号是否合法
    private void validateVertex(int v){
        if (v<0 || v>=V){
            throw new IllegalArgumentException("Vertex "+v+" is invalid");
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    // 判断v和w是否存在一条边
    public boolean hasEdge(int v, int w){
        // 判断v和w的合法性
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    // 返回与v相邻的顶点
    public ArrayList<Integer> adj(int v){
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i=0;i<V;i++){
            if (adj[v][i] == 1){
                res.add(i);
            }
        }
        return res;
    }

    // 求顶点的度
    public int degree(int v){
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("V = %d, E = %d\n", V, E));
        for (int i=0;i<V;i++){
            for (int j=0;j<V;j++){
                stringBuilder.append(String.format("%d", adj[i][j]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // 项目的路径开始
        AdjMatrix adjMatrix = new AdjMatrix("./src/Graph/g.txt");
        System.out.println(adjMatrix);
    }
}
