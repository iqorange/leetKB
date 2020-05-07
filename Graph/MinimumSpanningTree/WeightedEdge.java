package Graph.MinimumSpanningTree;

public class WeightedEdge implements Comparable<WeightedEdge>{
    private int v, w, weight;

    public WeightedEdge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    @Override
    public String toString(){
        return String.format("(%d-%d: %d)", v, w, weight);
    }

    @Override
    public int compareTo(WeightedEdge another) {
        // 比较权值
        return weight - another.weight;
    }
}
