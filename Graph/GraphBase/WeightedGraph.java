package Graph.GraphBase;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

// 无向带权图
public class WeightedGraph implements Cloneable{
    // 表示整个图有V个顶点
    private int V;
    // 表示整个图有E条边
    private int E;
    // 用红黑树形式创建映射
    private TreeMap<Integer, Integer>[] adj;

    // 通过读取一个文件来输入数据
    public WeightedGraph(String fileName){
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
            adj = new TreeMap[V];
            for (int i=0;i<V;i++){
                // Java8 类型推断
                adj[i] = new TreeMap<Integer, Integer>();
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
                // 读取权值
                int weight = scanner.nextInt();
                // 判断是否为自环边
                if (a == b){
                    throw new IllegalArgumentException("Self loop is detected!");
                }
                // 判断是否平行边，这里只处理简单图
                if (adj[a].containsKey(b)){
                    throw new IllegalArgumentException("Parallel edges are detected");
                }
                // 对相应的顶点进行无向图的连接
                adj[a].put(b, weight);
                adj[b].put(a, weight);
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
        return adj[v].containsKey(w);
    }

    // 返回与v相邻的顶点
    // 用Iterable封装实现细节，用户接收后只用遍历即可
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v].keySet();
    }

    // 求顶点的度
    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    // 获取某个边对应的权值
    public int getWeight(int v, int w){
        if (hasEdge(v, w)){
            return adj[v].get(w);
        }
        // 不存在边的情况
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    // 删除边
    public void removeEdge(int v,int w){
        // 验证顶点合法性
        validateVertex(v);
        validateVertex(w);
        // 无向图删除边
        adj[v].remove(w);
        adj[w].remove(v);
    }

    // 深拷贝
    @Override
    public Object clone(){
        try {
            WeightedGraph cloned = (WeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for (int v = 0;v<V;v++){
                cloned.adj[v] = new TreeMap<Integer, Integer>();
                for (Map.Entry<Integer, Integer> entry: adj[v].entrySet()){
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
                }
            }
            return cloned;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("V = %d, E = %d\n", V, E));
        for (int v=0;v<V;v++){
            stringBuilder.append(String.format("%d\t:\t", v));
            for (Map.Entry<Integer, Integer> entry: adj[v].entrySet()){
                stringBuilder.append(String.format("(%d: %d)\t", entry.getKey(), entry.getValue()));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // 项目的路径开始
        WeightedGraph g = new WeightedGraph("./src/Graph/g1.txt");
        System.out.println(g);
    }
}
