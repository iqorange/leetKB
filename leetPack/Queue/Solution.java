package leetPack.Queue;

import java.util.*;

public class Solution {
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
}
