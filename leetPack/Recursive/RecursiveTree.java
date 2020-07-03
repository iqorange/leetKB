package leetPack.Recursive;

import java.util.*;

public class RecursiveTree {
    // 111. 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDepth(root, 1);
    }
    private int minDepth(TreeNode node, int depth){
        if (node.left == null && node.right == null) return depth;
        int minDepth = Integer.MAX_VALUE;
        if (node.left != null) minDepth = Math.min(minDepth, minDepth(node.left, depth + 1));
        if (node.right != null) minDepth = Math.min(minDepth, minDepth(node.right, depth + 1));
        return minDepth;
    }

    // 226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        return root;
    }

    // 100. 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null) return false;
        if (q == null) return false;
        if (p.val == q.val){
            if (!isSameTree(p.left, q.left)) return false;
            return isSameTree(p.right, q.right);
        }else{
            return false;
        }
    }

    // 101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        Deque<Pair<Integer, Integer>> deque = new ArrayDeque<>();
        pushSymmetric(root, deque, 1);
        if (deque.size() % 2 == 0) return false;
        while (deque.size() >= 2){
            Pair<Integer, Integer> left = deque.removeFirst();
            Pair<Integer, Integer> right = deque.removeLast();
            if (!left.equals(right)) return false;
        }
        return true;
    }
    private void pushSymmetric(TreeNode node, Deque<Pair<Integer, Integer>> deque, int depth){
        if (node != null){
            pushSymmetric(node.left, deque, depth + 1);
            deque.add(new Pair<>(node.val, depth));
            pushSymmetric(node.right, deque, depth + 1);
        }
    }

    // 110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return true;
        int left = numBalanced(root.left);
        int right = numBalanced(root.right);
        if (left == -1 || right == -1){
            return false;
        }
        return Math.abs(left - right) <= 1;
    }
    private int numBalanced(TreeNode node){
        if (node == null) return 0;
        int left = numBalanced(node.left);
        int right = numBalanced(node.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1){
            return -1;
        }else{
            return Math.max(left, right) + 1;
        }
    }

    // 112. 路径总和
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // 404. 左叶子之和
    int sumOfLeftLeaves = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return 0;
        sumOfLeftLeaves(root.left, true);
        sumOfLeftLeaves(root.right, false);
        return sumOfLeftLeaves;
    }
    private void sumOfLeftLeaves(TreeNode node, boolean isLeft){
        if (node != null){
            if (node.left == null && node.right == null && isLeft){
                sumOfLeftLeaves += node.val;
                return;
            }
            sumOfLeftLeaves(node.left, true);
            sumOfLeftLeaves(node.right, false);
        }
    }

    // 257. 二叉树的所有路径
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        if (root.left == null && root.right == null){
            result.add(String.valueOf(root.val));
            return result;
        }
        List<String> leftS = binaryTreePaths(root.left);
        for (String left : leftS) {
            result.add(String.valueOf(root.val) + "->" + left);
        }
        List<String> rightS = binaryTreePaths(root.right);
        for (String right : rightS) {
            result.add(String.valueOf(root.val) + "->" + right);
        }
        return result;
    }

    // 113. 路径总和 II
    List<List<Integer>> pathSumLists = new ArrayList<>();
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null) return pathSumLists;
        List<Integer> list = new ArrayList<>();
        pathSum2(root, sum, list);
        return pathSumLists;
    }
    private void pathSum2(TreeNode node, int sum, List<Integer> list){
        if (node == null) return;
        if (node.left == null && node.right == null){
            if (sum == node.val){
                list.add(node.val);
                pathSumLists.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        list.add(node.val);
        pathSum2(node.left, sum - node.val, list);
        pathSum2(node.right, sum - node.val, list);
        list.remove(list.size() - 1);
    }

    // 129. 求根到叶子节点数字之和
    ArrayList<Integer> sumNumbers = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        sumNumbers(root, 0);
        int sum = 0;
        for (int num: sumNumbers){
            sum += num;
        }
        return sum;
    }
    private void sumNumbers(TreeNode node, int numbers){
        if (node == null) return;
        if (node.left == null && node.right == null){
            sumNumbers.add(numbers + node.val);
            return;
        }
        sumNumbers(node.left, (numbers + node.val) * 10);
        sumNumbers(node.right, (numbers + node.val) * 10);
    }

    // 437. 路径总和 III
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int result = findPath(root, sum);
        result += pathSum(root.left, sum);
        result += pathSum(root.right, sum);
        return result;
    }
    private int findPath(TreeNode node, int num){
        if (node == null) return 0;
        int result = 0;
        if (node.val == num){
            result += 1;
        }
        result += findPath(node.left, num - node.val);
        result += findPath(node.right,  num - node.val);
        return result;
    }

    // 235. 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        assert p != null && q != null;
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestor2(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val){
            return lowestCommonAncestor2(root.right, p, q);
        }
        return root;
    }

    // 98. 验证二叉搜索树
    long parentNum = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (root.val <= parentNum) return false;
        parentNum = root.val;
        return isValidBST(root.right);
    }

    // 450. 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key){
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left == null){
                return right;
            }
            if (right == null){
                return left;
            }
            if (left.right == null){
                left.right = right;
                return left;
            }
            TreeNode car = right;
            while (car.left != null){
                car = car.left;
            }
            car.left = left.right;
            left.right = right;
            return left;
        }
        root.left = deleteNode(root.left, key);
        root.right = deleteNode(root.right, key);
        return root;
    }

    // 108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : createTree(nums, 0, nums.length - 1);
    }
    private TreeNode createTree(int[] nums, int left, int right){
        if (left > right) return null;
        int middle = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[middle]);
        node.left = createTree(nums, left, middle - 1);
        node.right = createTree(nums, middle + 1, right);
        return node;
    }

    // 236. 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val) return root;
        if (root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
