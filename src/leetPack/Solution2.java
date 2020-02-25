package leetPack;

import java.util.LinkedList;
import java.util.List;

public class Solution2 {
    // 104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
    }

    // 589. N叉树的前序遍历
    public List<Integer> preorder(Node root) {
        if (root == null) return new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        list.add(root.val);
        for (int i=0;i<root.children.size();i++){
            list.addAll(preorder(root.children.get(i)));
        }
        return list;
    }

    // 590. N叉树的后序遍历
    public List<Integer> postorder(Node root) {
        if (root == null) return new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        for (int i=0;i<root.children.size();i++){
            list.addAll(postorder(root.children.get(i)));
        }
        list.add(root.val);
        return list;
    }

    // 700. 二叉搜索树中的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode t1 = searchBST(root.left, val);
        if (t1 != null){
            return t1;
        }else{
            return searchBST(root.right, val);
        }
    }

    // 面试题32 - II. 从上到下打印二叉树 II
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new LinkedList<>();
        List<List<Integer>> lists = new LinkedList<>();
        levelOrder(root, lists, 0);
        return lists;
    }
    private void levelOrder(TreeNode root, List<List<Integer>> lists, int depth){
        if (root == null) return;
        if (lists.size()<(depth+1)){
            List<Integer> list = new LinkedList<>();
            list.add(root.val);
            lists.add(list);
        }else{
            lists.get(depth).add(root.val);
        }
        levelOrder(root.left, lists, depth+1);
        levelOrder(root.right, lists, depth+1);
    }
}
