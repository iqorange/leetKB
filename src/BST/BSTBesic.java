package BST;
// E要有可比较性Comparable
public class BSTBesic<E extends Comparable<E>> {
    //二分搜索树的节点定义
    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    //构造方法
    public BSTBesic(){
        root = null;
        size = 0;
    }

    /**
     * 获取树的尺寸
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     * 判断树是否为空
     * @return true为空，false不为空
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 外部接口添加元素e
     * @param e 可比较的元素E
     */
    public void add(E e){
        root = add(root, e);
    }

    /**
     * 私有方法递归寻找左右节点，插入e
     * @param node 以Node为跟节点
     * @param e 元素e
     * @return 返回插入新节点后二叉树的根
     */
    private Node add(Node node, E e){
        //先判断一下是否在这个节点中已经有了
        if (node == null){
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e)>0){
            node.right = add(node.right, e);
        }
        return node;
    }
}
