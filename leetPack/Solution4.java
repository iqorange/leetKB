package leetPack;

import java.util.*;

public class Solution4 {
    // 876. 链表的中间结点
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode car = head;
        ListNode point = head;
        while (car.next != null) {
            if (car.next.next != null) {
                car = car.next.next;
                point = point.next;
            } else {
                car = car.next;
                point = point.next;
            }
        }
        return point;
    }

    // 191. 位1的个数
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    // 面试题52. 两个链表的第一个公共节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }

    // 面试题 05.07. 配对交换
    public int exchangeBits(int num) {
        String s = Integer.toBinaryString(num);
        StringBuilder str = new StringBuilder();
        int i = 0;
        if (s.length() % 2 != 0) {
            str.append(s.charAt(0));
            str.append('0');
            i = 1;
        }
        for (; i < s.length(); i += 2) {
            str.append(s.charAt(i + 1));
            str.append(s.charAt(i));
        }
        return Integer.parseInt(str.toString(), 2);
    }

    // 669. 修剪二叉搜索树
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    // 107. 二叉树的层次遍历 II
    List<List<Integer>> lists = new LinkedList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        levelOrderBottom(root, 0);
        Collections.reverse(lists);
        return lists;
    }

    private void levelOrderBottom(TreeNode root, int depth) {
        if (root == null) return;
        if (lists.size() <= depth) lists.add(new LinkedList<>());
        levelOrderBottom(root.left, depth + 1);
        levelOrderBottom(root.right, depth + 1);
        lists.get(depth).add(root.val);
    }

    // 806. 写字符串需要的行数
    public int[] numberOfLines(int[] widths, String S) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < widths.length; i++) {
            map.put(abc.charAt(i), widths[i]);
        }
        int[] result = new int[2];
        int length = 0;
        int row = 1;
        for (int i = 0; i < S.length(); i++) {
            int t = map.get(S.charAt(i));
            if (t + length > 100) {
                row++;
                length = t;
            } else {
                length += t;
            }
        }
        result[0] = row;
        result[1] = length;
        return result;
    }

    // 347. 前 K 个高频元素
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                ((o1, o2) -> map.get(o1) - map.get(o2))
        );
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }

    // 387. 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 766. 托普利茨矩阵
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix.length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 566. 重塑矩阵
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if ((nums.length == 0) || (nums.length * nums[0].length != r * c)) {
            return nums;
        }
        int[][] res = new int[r][c];
        int pointR = 0;
        int pointC = 0;
        for (int[] num : nums) {
            for (int n : num) {
                if (pointC == c) {
                    pointC = 0;
                    pointR++;
                }
                res[pointR][pointC] = n;
                pointC++;
            }
        }
        return res;
    }

    // 面试题 17.04. 消失的数字
    public int missingNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int e : nums) {
            sum += e;
        }
        return (1 + nums.length) * nums.length / 2 - sum;
    }

    // 1189. “气球” 的最大数量
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 10000;
        for (int i = 0; i < text.length(); i++) {
            map.put(text.charAt(i), map.getOrDefault(text.charAt(i), 0) + 1);
        }
        count = Math.min(count, map.getOrDefault('b', 0));
        count = Math.min(count, map.getOrDefault('a', 0));
        count = Math.min(count, map.getOrDefault('l', 0) / 2);
        count = Math.min(count, map.getOrDefault('o', 0) / 2);
        count = Math.min(count, map.getOrDefault('n', 0));
        return count;
    }

    // 面试题 17.10. 主要元素
    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int major = nums[0];
        int count = 0;
        for (int e : nums) {
            if (count == 0) {
                major = e;
            }
            if (e == major) {
                count++;
            } else {
                count--;
            }
        }
        return count < -1 ? -1 : major;
    }

    // 1337. 方阵中战斗力最弱的 K 行
    public int[] kWeakestRows(int[][] mat, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int[] arr = new int[k];
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[j][i] == 0 && !queue.contains(j)) {
                    queue.add(j);
                }
            }
        }
        while (queue.size() < k) {
            for (int i = 0; i < k; i++) {
                if (!queue.contains(i)) {
                    queue.add(i);
                }
            }
        }
        for (int i = 0; i < k; i++) {
            arr[i] = queue.remove();
        }
        return arr;
    }

    // 面试题64. 求1+2+…+n
    public int sumNums(int n) {
        if (n == 1) {
            return 1;
        }
        return sumNums(n - 1) + n;
    }

    // 1379. 找出克隆二叉树中的相同节点
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode res = getTargetCopy(original.left, cloned.left, target);
        return res == null ? getTargetCopy(original.right, cloned.right, target) : res;
    }

    // 面试题 16.01. 交换数字
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }

    // 1302. 层数最深叶子节点的和
    int sum = 0, maxDepth = 0;
    public int deepestLeavesSum(TreeNode root) {
        deepestLeavesSum(root, 0);
        return sum;
    }
    private void deepestLeavesSum(TreeNode node, int depth){
        if (node.left != null){
            deepestLeavesSum(node.left, depth + 1);
        }
        if (node.right != null){
            deepestLeavesSum(node.right, depth + 1);
        }
        if (maxDepth == depth){
            sum += node.val;
        }else if (maxDepth < depth){
            maxDepth = depth;
            sum = node.val;
        }
    }

    // 1315. 祖父节点值为偶数的节点和
    public int sumEvenGrandparent(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.val % 2 == 0){
            int sum = 0;
            if (root.left != null){
                if (root.left.left != null){
                    sum += root.left.left.val;
                }
                if (root.left.right != null){
                    sum += root.left.right.val;
                }
                sum += sumEvenGrandparent(root.left);
            }
            if (root.right != null){
                if (root.right.right != null){
                    sum += root.right.right.val;
                }
                if (root.right.left != null){
                    sum += root.right.left.val;
                }
                sum += sumEvenGrandparent(root.right);
            }
            return sum;
        }else{
            return sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
        }
    }
}
