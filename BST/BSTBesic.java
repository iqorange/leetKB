package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 二分搜索树的基本实现，完全随机的数据性能很好，但极端情况会退化成链表
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

    /**
     * 看二分搜索树是否包含元素e
     * @param e 元素e
     * @return 返回是否包含e
     */
    public boolean contains(E e){
        return contains(root, e);
    }

    /**
     * 以node为根的二分搜索树是否包含元素e，递归方式
     * @param node 传入的node视为根节点
     * @param e 元素e
     * @return 返回是否包含e
     */
    private boolean contains(Node node, E e){
        // 当node为空的时候，就不存在e了
        if (node == null){
            return false;
        }
        // 找到node的e的情况下
        if (e.compareTo(node.e) == 0){
            return true;
        }else if(e.compareTo(node.e) < 0){
            // 在该节点有元素的情况下，并且e小于该节点的话，向左子树继续向下寻找
            return contains(node.left, e);
        }else{
            // 在e大于该节点时，到右子树寻找
            return contains(node.right, e);
        }
    }

    /**
     * --- 深度优先遍历 ---
     */

    /**
     * 二叉树的前序遍历
     * 最自然的遍历方式
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 内部方法进行递归遍历
     * @param node 传入每一个节点遍历
     */
    private void preOrder(Node node){
        // 判断是否为空节点
        if (node == null){
            return;
        }
        // 先输出该节点
        System.out.println(node.e);
        // 然后分别遍历左右子树
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR(){
        // 使用栈来进行
        Stack<Node> stack = new Stack<>();
        // 把根节点压入栈
        stack.push(root);
        // 当栈不是空时
        while (!stack.isEmpty()){
            // 从栈中取出
            Node cur = stack.pop();
            System.out.println(cur.e);
            // 将右子树和左子树压入栈中
            if (cur.right != null){
                stack.push(cur.right);
            }
            if (cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二叉树的中序遍历
     * 可以输出排序后的结果
     * 此时二分搜索树：排序树
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 中序遍历私有方法，以node为根遍历二分搜索树，递归算法
     * @param node 传入的节点
     */
    private void inOrder(Node node){
        // 判断是否为空节点
        if (node == null){
            return;
        }
        // 先遍历左子树
        inOrder(node.left);
        // 输出该节点
        System.out.println(node.e);
        // 然后访问右子树
        inOrder(node.right);
    }

    /**
     * 后序遍历
     * 用于内存的释放
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * --- 广度优先遍历 ---
     */

    /**
     * 二分搜索树的层序遍历
     * 可以更快找到要查找的元素（最短路径）
     */
    public void levelOrder(){
        // 队列接口->链表实现
        Queue<Node> q = new LinkedList<>();
        // 将根节点加入队列中
        q.add(root);
        // 当队列不为空
        while (!q.isEmpty()){
            // 从队列中取出一个节点
            Node cur = q.remove();
            // 读取当前访问的元素
            System.out.println(cur.e);
            // 如果该节点的左右孩子不为空，则将他们加入队列中
            if (cur.left != null){
                q.add(cur.left);
            }
            if (cur.right != null){
                q.add(cur.right);
            }
        }
    }

    /**
     * --- 删除二分搜索树中的元素 ---
     */

    /**
     * 寻找二分搜索树中的最小元素
     * @return 返回该元素E
     */
    public E minimum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    /**
     * 递归返回最小节点
     * @param node 以node为节点
     * @return 返回最小值
     */
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }else{
            return minimum(node.left);
        }
    }

    /**
     * 寻找二分搜索树中的最大元素
     * @return 返回该元素E
     */
    public E maximum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    /**
     * 递归返回最大节点
     * @param node 以node为节点
     * @return 返回最大值
     */
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }else{
            return maximum(node.right);
        }
    }

    /**
     * 删除最小值
     * @return 返回最小值
     */
    public E removeMin(){
        E ret = minimum();
        removeMin(root);
        return ret;
    }

    /**
     * 删掉以node为根的二叉树中的最小节点
     * @param node 每一层节点
     * @return 返回二分搜索树新的根
     */
    private Node removeMin(Node node){
        if (node.left == null){
            // 不能丢右子树
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }else{
            node.left = removeMin(node.left);
            return node;
        }
    }

    /**
     * 删除最大值
     * @return 返回最大值
     */
    public E removeMax(){
        E ret = maximum();
        removeMax(root);
        return ret;
    }

    /**
     * 删掉以node为根的二叉树中的最大节点
     * @param node 每一层节点
     * @return 返回二分搜索树新的根
     */
    private Node removeMax(Node node){
        if (node.right == null){
            // 不能丢左子树
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }else{
            node.right = removeMin(node.right);
            return node;
        }
    }

    /**
     * 从二分搜索树中删除e节点
     * @param e 节点
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     * 私有方法递归删除
     * @param node 根
     * @param e 要删除的节点
     * @return 返回根
     */
    private Node remove(Node node, E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) >0){
            node.right = remove(node.right, e);
            return node;
        }else{// node.e == e
            // 左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 右子树为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 左右子树均不为空
            // 找到比待删除节点大的最小节点顶替
            Node successor = minimum(node.right);
            successor.right = removeMin(node.left);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * --- 重写toString方法，自动打印输出树结构 ---
     */

    /**
     * 生成一个描述二叉树的字符串，使用前序遍历
     * @param node 根结点
     * @param depth 深度
     * @param res 输出的字符串
     */
    private void generateBSTString(Node node, int depth, StringBuilder res){
        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth+1, res);
        generateBSTString(node.right, depth+1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }
}
