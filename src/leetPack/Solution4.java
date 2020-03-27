package leetPack;

import java.util.*;

public class Solution4 {
    // 876. 链表的中间结点
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode car = head;
        ListNode point = head;
        while (car.next != null){
            if (car.next.next != null){
                car = car.next.next;
                point = point.next;
            }else{
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
        ListNode node1=headA;
        ListNode node2=headB;
        while(node1!=node2){
            node1=node1==null?headB:node1.next;
            node2=node2==null?headA:node2.next;
        }
        return node1;
    }

    // 面试题 05.07. 配对交换
    public int exchangeBits(int num) {
        String s = Integer.toBinaryString(num);
        StringBuilder str = new StringBuilder();
        int i=0;
        if (s.length()%2!=0){
            str.append(s.charAt(0));
            str.append('0');
            i = 1;
        }
        for (;i<s.length();i+=2){
            str.append(s.charAt(i+1));
            str.append(s.charAt(i));
        }
        return Integer.parseInt(str.toString(), 2);
    }

    // 669. 修剪二叉搜索树
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val<L){
            return trimBST(root.right, L, R);
        }
        if (root.val>R){
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
    private void levelOrderBottom (TreeNode root, int depth) {
        if (root == null) return;
        if (lists.size()<=depth) lists.add(new LinkedList<>());
        levelOrderBottom(root.left, depth+1);
        levelOrderBottom(root.right, depth+1);
        lists.get(depth).add(root.val);
    }

    // 806. 写字符串需要的行数
    public int[] numberOfLines(int[] widths, String S) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0;i<widths.length;i++){
            map.put(abc.charAt(i), widths[i]);
        }
        int[] result = new int[2];
        int length = 0;
        int row = 1;
        for (int i=0;i<S.length();i++){
            int t = map.get(S.charAt(i));
            if (t+length>100){
                row++;
                length = t;
            }else{
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
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                ((o1, o2) -> map.get(o1)-map.get(o2))
        );
        for (int key: map.keySet()){
            if (pq.size()<k){
                pq.add(key);
            }else if (map.get(key)>map.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()){
            res.add(pq.remove());
        }
        return res;
    }

    // 387. 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i=0;i<s.length();i++){
            if (map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }
}
