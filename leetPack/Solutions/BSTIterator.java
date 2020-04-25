package leetPack.Solutions;

import java.util.LinkedList;
import java.util.Queue;

// 173. 二叉搜索树迭代器
public class BSTIterator {
    private Queue<Integer> queue;

    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        midDFS(root);
    }

    private void midDFS(TreeNode node){
        if (node == null){
            return;
        }
        midDFS(node.left);
        queue.add(node.val);
        midDFS(node.right);
    }

    /** @return the next smallest number */
    public int next() {
        return queue.remove();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */