package Graph.DFS;

public class Edge {
    // 边的两个端点
    private int v, w;

    public Edge(int v, int w){
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", v, w);
    }
}
