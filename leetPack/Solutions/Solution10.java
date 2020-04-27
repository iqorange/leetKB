package leetPack.Solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution10 {
    // 1008. 先序遍历构造二叉树
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int i = 0; i < preorder.length; i++) {
            root = bstFromPreorder(root, preorder[i]);
        }
        return root;
    }
    private TreeNode bstFromPreorder(TreeNode root, int num) {
        if (root == null) {
            return new TreeNode(num);
        }
        if (root.val > num) {
            root.left = bstFromPreorder(root.left, num);
        } else {
            root.right = bstFromPreorder(root.right, num);
        }
        return root;
    }

    // 230. 二叉搜索树中第K小的元素
    private int step = 0, value = -1;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null){
            return value;
        }
        kthSmallest(root.left, k);
        step++;
        if (step == k){
            value = root.val;
            return value;
        }
        kthSmallest(root.right, k);
        return value;
    }

    // 222. 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
