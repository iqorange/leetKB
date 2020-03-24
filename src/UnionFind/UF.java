package UnionFind;

// 并查集接口
public interface UF {
    // 返回并查集的元素数
    int getSize();
    // 对索引进行映射(查)
    boolean isConnected(int p, int q);
    // 将两个元素并在一起(并)
    void unionElements(int p, int q);
}
