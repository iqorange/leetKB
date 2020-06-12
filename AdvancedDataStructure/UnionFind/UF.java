package AdvancedDataStructure.UnionFind;

// 并查集接口
// 并查集是一种子节点指向父节点的特殊的树结构
public interface UF {
    // 返回并查集的元素数
    int getSize();
    // 对索引进行映射(查)
    boolean isConnected(int p, int q);
    // 将两个元素并在一起(并)
    void unionElements(int p, int q);
}
