package leetPack.Queue;

import java.util.*;

public class Solution {
    // 构建一个键值对数据结构
    private class Pair<K, V>{
        private final K key;
        private final V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    // 102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()){
            TreeNode node = queue.peek().key;
            int level = queue.peek().value;
            queue.remove();
            if (level == res.size()){
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            if (node.left != null) queue.add(new Pair<>(node.left, level + 1));
            if (node.right != null) queue.add(new Pair<>(node.right, level + 1));
        }
        return res;
    }

    // 103. 二叉树的锯齿形层次遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()){
            TreeNode node = queue.peek().key;
            int level = queue.peek().value;
            queue.remove();
            if (level == res.size()){
                res.add(new ArrayList<>());
            }
            res.get(level).add(node.val);
            if (node.left != null) queue.add(new Pair<>(node.left, level + 1));
            if (node.right != null) queue.add(new Pair<>(node.right, level + 1));
        }
        for (int i=0;i<res.size();i++){
            if (i % 2 == 1){
                Collections.reverse(res.get(i));
            }
        }
        return res;
    }

    // 199. 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        List<Deque<Integer>> res = new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()){
            TreeNode node = queue.peek().key;
            int level = queue.peek().value;
            queue.remove();
            if (level == res.size()){
                res.add(new ArrayDeque<>());
            }
            res.get(level).addFirst(node.val);
            if (node.left != null) queue.add(new Pair<>(node.left, level + 1));
            if (node.right != null) queue.add(new Pair<>(node.right, level + 1));
        }
        List<Integer> list = new ArrayList<>();
        for (Deque<Integer> e: res){
            list.add(e.removeFirst());
        }
        return list;
    }

    // 279. 完全平方数
    public int numSquares(int n) {
        assert n > 0;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(n, 0));
        boolean[] visited = new boolean[n+1];
        visited[n] = true;
        while (!queue.isEmpty()){
            int num = queue.peek().key;
            int step = queue.peek().value;
            queue.remove();
            for (int i=1;;i++){
                int temp = num - i * i;
                if (temp < 0) break;
                if (temp == 0) return step + 1;
                if (!visited[temp]){
                    queue.add(new Pair<>(temp, step + 1));
                    visited[temp] = true;
                }
            }
        }
        throw new IllegalArgumentException("No Solution.");
    }

    // 23. 合并K个排序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val-o2.val);
        for (ListNode node: lists){
            while (node != null){
                queue.add(node);
                node = node.next;
            }
        }
        if (queue.isEmpty()) return null;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = queue.remove();
        ListNode car = dummyHead.next;
        while (!queue.isEmpty()){
            car.next = queue.remove();
            car = car.next;
        }
        car.next = null;
        return dummyHead.next;
    }
}
