package leetPack;

import java.util.LinkedList;
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
}
