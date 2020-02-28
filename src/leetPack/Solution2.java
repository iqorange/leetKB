package leetPack;

import java.util.*;

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

    // 349. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num: nums1){
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num: nums2){
            if (set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i=0;i<list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }

    // 350. 两个数组的交集 II
    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num: nums1){
            if (!map.containsKey(num)){
                map.put(num, 1);
            }else{
                map.put(num, map.get(num)+1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num: nums2){
            if (map.containsKey(num)){
                list.add(num);
                map.put(num, map.get(num)-1);
                if (map.get(num) == 0){
                    map.remove(num);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i=0;i<list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }

    // 面试题57 - II. 和为s的连续正数序列
    public int[][] findContinuousSequence(int target) {
        int begin = 1,end = 2, sum = 1;
        List<int[]> res = new ArrayList<>();
        while(begin < end){
            if(sum + end  < target){
                sum +=end++ ;
            }else if(sum + end > target){
                sum -= begin++;
            }else{
                int[] arr = new int[end-begin+1];
                for(int j = 0,i = begin; i <= end; i++,j++){
                    arr[j] = i;
                }
                res.add(arr);
                sum -= begin++;
            }
        }
        int[][] result = new int[res.size()][];
        return res.toArray(result);
    }

    // 1064. 不动点
    public int fixedPoint(int[] A) {
        for (int i=0;i<A.length;i++){
            if (A[i]==i){
                return i;
            }
        }
        return -1;
    }

    // 942. 增减字符串匹配
    public int[] diStringMatch(String S) {
        int[] arr = new int[S.length()+1];
        int left = 0;
        int right = S.length();
        for (int i=0;i<S.length();i++){
            if (S.charAt(i) == 'I'){
                arr[i] = left;
                left++;
            }else{
                arr[i] = right;
                right--;
            }
        }
        arr[S.length()] = right;
        return arr;
    }

    // 977. 有序数组的平方
    public int[] sortedSquares(int[] A) {
        for (int i=0;i<A.length;i++){
            A[i] *= A[i];
        }
        Arrays.sort(A);
        return A;
    }

    // 面试题 02.03. 删除中间节点
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 293. 翻转游戏
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new LinkedList<>();
        for (int i=0;i<s.length()-1;i++){
            if (s.substring(i,i+2).equals("++")){
                list.add(s.substring(0,i)+"--"+s.substring(i+2,s.length()));
            }
        }
        return list;
    }

    // 面试题 08.03. 魔术索引
    public int findMagicIndex(int[] nums) {
        for (int i=0;i<nums.length;i++){
            if (nums[i]==i) return i;
        }
        return -1;
    }

    // 面试题68 - I. 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode node1 = lowestCommonAncestor(root.left, p, q);
        TreeNode node2 = lowestCommonAncestor(root.right, p, q);
        if (root == p || root == q){
            return root;
        }
        if (node1 != null && node2 != null){
            return root;
        }
        if (node1 != null) return node1;
        if (node2 != null) return node2;
        return null;
    }

    // 561. 数组拆分 I
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i=0;i<nums.length;i+=2){
            sum += nums[i];
        }
        return sum;
    }

    // 292. Nim 游戏
    public boolean canWinNim(int n) {
        return !(n%4==0);
    }

    // 852. 山脉数组的峰顶索引
    public int peakIndexInMountainArray(int[] A) {
        for (int i=0;i<A.length;i++){
            if (A[i+1]<A[i]){
                return i;
            }
        }
        return 0;
    }

    // 557. 反转字符串中的单词 III
    public String reverseWords(String s) {
        StringBuilder str = new StringBuilder();
        String[] strs = s.split(" ");
        for (String e: strs){
            for (int i=e.length()-1;i>=0;i--){
                str.append(e.charAt(i));
            }
            str.append(" ");
        }
        return str.toString().substring(0, str.length()-1);
    }

    // 108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        TreeNode node = new TreeNode(nums[nums.length/2]);
        int[] left = new int[nums.length/2];
        for (int i=0;i<nums.length/2;i++){
            left[i] = nums[i];
        }
        node.left = sortedArrayToBST(left);
        int[] right;
        if (nums.length%2==0){
             right = new int[nums.length/2-1];
        }else{
            right = new int[nums.length/2];
        }
        for (int i=nums.length/2+1, k=0;i<nums.length;i++, k++){
            right[k] = nums[i];
        }
        node.right = sortedArrayToBST(right);
        return node;
    }

    // 1025. 除数博弈
    public boolean divisorGame(int N) {
        return N%2==0;
    }

    // 面试题03. 数组中重复的数字
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int e: nums){
            if (set.contains(e)){
                return e;
            }
            set.add(e);
        }
        return 0;
    }

    // 1196. 最多可以买到的苹果数量
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int w = 5000;
        int count = 0;
        for (int e: arr){
            if (w-e>=0){
                count++;
                w -= e;
            }else{
                break;
            }
        }
        return count;
    }

    // 面试题 02.01. 移除重复节点
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        return removeDuplicateNodes(head, set);
    }
    private ListNode removeDuplicateNodes(ListNode head, Set<Integer> set) {
        if (head == null) return null;
       if (set.contains(head.val)){
           return removeDuplicateNodes(head.next, set);
       }
       set.add(head.val);
       head.next = removeDuplicateNodes(head.next, set);
       return head;
    }

    // 559. N叉树的最大深度
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = 0;
        for (int i=0;i<root.children.size();i++){
            max = Math.max(maxDepth(root.children.get(i)), max);
        }
        return max+1;
    }

    // 944. 删列造序
    public int minDeletionSize(String[] A) {
        int count = 0;
        for (int i=0;i<A[0].length();i++){
            for (int j=0;j<A.length-1;j++){
                if(A[j].charAt(i)>A[j+1].charAt(i)){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // 500. 键盘行
    public String[] findWords(String[] words) {
        List<String> s = new LinkedList<>();
        Set<Character>[] set = new Set[3];
        String[] k = new String[3];
        k[0] = "qwertyuiop";
        k[1] = "asdfghjkl";
        k[2] = "zxcvbnm";
        for (int p = 0;p<3;p++){
            set[p] = new HashSet<>();
            for (int i=0;i<k[p].length();i++){
                set[p].add(k[p].charAt(i));
            }
        }
        for (String e: words){
            String elem = e.toLowerCase();
            List<Character> t = new ArrayList<>(e.length());
            for (int i=0;i<e.length();i++){
                t.add(elem.charAt(i));
            }
            for (Set sett: set){
                if (sett.containsAll(t)){
                    s.add(e);
                    break;
                }
            }
        }
        return s.toArray(new String[s.size()]);
    }

    // 1207. 独一无二的出现次数
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int e: arr){
            map.put(e, map.getOrDefault(e, 0)+1);
        }
        return map.size() == new HashSet<>(map.values()).size();
    }

    // 905. 按奇偶排序数组
    public int[] sortArrayByParity(int[] A) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        for (int e: A){
            if (e%2==0){
                list1.add(e);
            }else{
                list2.add(e);
            }
        }
        list1.addAll(list2);
        int[] arr = new int[list1.size()];
        for (int i=0;i<list1.size();i++){
            arr[i] = list1.get(i);
        }
        return arr;
    }

    // 476. 数字的补数
    public int findComplement(int num) {
        String str = Integer.toBinaryString(num);
        int sum = 0;
        for (int i=str.length()-1, k=0;i>=0;i--, k++){
            if (str.charAt(i)=='0'){
                if (k==0){
                    sum++;
                }else{
                    sum += Math.pow(2, k);
                }
            }
        }
        return sum;
    }

    // 1237. 找出给定方程的正整数解
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<Integer> t;
        List<List<Integer>> lists = new LinkedList<>();
        for(int i=1;i<=1000;i++){
            for(int j=1;j<=1000;j++){
                if (customfunction.f(i, j) == z){
                    t = new ArrayList<>(2);
                    t.add(i);
                    t.add(j);
                    lists.add(t);
                    t = null;
                }else if (customfunction.f(i, j)>z){
                    break;
                }
            }
        }
        return lists;
    }

    // 1356. 根据数字二进制下 1 的数目排序
    public int[] sortByBits(int[] arr) {
        Integer[] nums = new Integer[arr.length];
        for (int i=0;i<arr.length;i++){
            nums[i] = arr[i];
        }
        Arrays.sort(nums, (o1, o2) -> {
            int bitA = Integer.bitCount(o1);
            int bitB = Integer.bitCount(o2);
            return bitA == bitB ? o1-o2 : bitA-bitB;
        });
        for (int i=0;i<arr.length;i++){
            arr[i] = nums[i];
        }
        return arr;
    }
}
