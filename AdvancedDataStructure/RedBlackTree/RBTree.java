package AdvancedDataStructure.RedBlackTree;

// 红黑树，适合多增删改，统计性能更优
public class RBTree<K extends Comparable<K>, V> {
    // 定义红黑布尔变量
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            // 默认初始化节点为红色，先融合进叶子结点
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 判断颜色，验证空节点
    private boolean isRed(Node node){
        if (node == null){
            return BLACK;
        }
        return node.color;
    }

    // 左旋转，用于出现新添加进来的节点比原先的节点大的情况(2节点->3节点)
    private Node leftRotate(Node node){
        // 旋转节点
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        // 旋转颜色
        x.color = node.color;
        node.color = RED;
        // 返回旋转后新的根节点
        return x;
    }

    // 当2节点->3节点->2节点时，进行颜色翻转，节点结构位置不变(一小一大的情况)
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // 右旋转，用于连续两个单向节点的维护
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        // 维持颜色
        x.color = node.color;
        node.color = RED;
        // 返回旋转后的新节点
        return x;
    }

    public void add(K key, V value){
        root = add(root, key, value);
        // 保持根节点为黑色节点
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }else if(key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else { // key.compareTo(node.key) == 0
            node.value = value;
        }

        // 三次旋转
        // 判断当前添加的节点是不是刚好在两个节点之间
        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }

        // 判断当前添加的节点是比两个节点都小的情况
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }

        // 判断当前添加的节点是比两个节点都大的情况
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key){

        if(node == null) {
            return null;
        }

        if(key.equals(node.key)) {
            return node;
        }else if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }else { // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        node.value = newValue;
    }

    private Node minimum(Node node){
        if(node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if( node == null ) {
            return null;
        }

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }else{   // key.compareTo(node.key) == 0

            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
        // TODO 红黑树的删除逻辑实现
    }
}

