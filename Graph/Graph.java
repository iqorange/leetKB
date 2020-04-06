package Graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

// 邻接表，基于红黑树
// 暂时只支持无向无权图
public class Graph {
    // 表示整个图有V个顶点
    private int V;
    // 表示整个图有E条边
    private int E;
    // 用红黑树形式创建邻接表
    private TreeSet<Integer>[] adj;

    // 通过读取一个文件来输入数据
    public Graph(String fileName){
        // 开始读取文件，使用java.io.File类
        File file = new File(fileName);
        // JDK7特性，自动关闭Scanner资源
        try(Scanner scanner = new Scanner(file);) {
            // 读取顶点信息，该数字是文件第一行第一个
            V = scanner.nextInt();
            if (V < 0){
                throw new IllegalArgumentException("V must be non-negative");
            }
            // 当创建的是对象的时候，申请链表数组内存空间
            adj = new TreeSet[V];
            for (int i=0;i<V;i++){
                // Java8 类型推断
                adj[i] = new TreeSet<Integer>();
            }
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
                if (adj[a].contains(b)){
                    throw new IllegalArgumentException("Parallel edges are detected");
                }
                // 对相应的顶点进行无向图的连接
                adj[a].add(b);
                adj[b].add(a);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 判断顶点序号是否合法
    public void validateVertex(int v){
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
        return adj[v].contains(w);
    }

    // 返回与v相邻的顶点
    // 用Iterable封装实现细节，用户接收后只用遍历即可
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    // 求顶点的度
    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("V = %d, E = %d\n", V, E));
        for (int v=0;v<V;v++){
            stringBuilder.append(String.format("%d\t:\t", v));
            for (int w: adj[v]){
                stringBuilder.append(String.format("%d\t", w));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // 项目的路径开始
        Graph adjTreeSet = new Graph("./src/Graph/g.txt");
        System.out.println(adjTreeSet);
    }
}
