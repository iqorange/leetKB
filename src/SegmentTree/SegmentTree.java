package SegmentTree;

/**
 * 线段树的实现类
 * @param <E>
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    // 构造函数传入data
    public SegmentTree(E[] arr, Merger<E> merger){
        // 定义好用户传来的融合器，用于用户使用加减乘除等比较功能
        this.merger = merger;
        // 创建数组存储传进来的线段树的值
        data = (E[])new Object[arr.length];
        for (int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }
        // 开辟4n的线段树空间
        tree = (E[])new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length-1);
    }

    // 在treeIndex的位置创建区间从l到r的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        // 长度为1的时候直接构建当前节点
        if (l==r){
            tree[treeIndex] = data[r];
            return;
        }
        // 获取左右孩子的tree中对应的值
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 获取中间值，并防止int溢出
        int mid = l + (r - l) / 2;
        // 递归创建左右子树
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);
        // 创建完成之后计算左右子树的和为当前节点，用融合器进行融合
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    // 获取对应index的E
    public E get(int index){
        if (index < 0 || index>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 获取线段树的大小
    public int getSize(){
        return data.length;
    }

    // 返回index的左孩子的节点
    private int leftChild(int index){
        return 2*index+1;
    }

    // 返回index的右孩子的节点
    private int rightChild(int index){
        return 2*index+2;
    }

    // 查询某个区间前闭后闭的的值
    public E query(int queryL, int queryR){
        if (queryL > queryR || queryL<0 || queryR>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length-1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l==queryL && r==queryR){
            return tree[treeIndex];
        }
        int mid = l+(r-l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid+1){
            return query(rightTreeIndex, mid+1, r, queryL, queryR);
        }else if (queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }else{
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid+1, r, mid+1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    // 重写toString打印输出
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i=0;i<tree.length;i++){
            if (tree[i] != null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
            if (i != tree.length-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
