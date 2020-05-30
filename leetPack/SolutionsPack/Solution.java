package leetPack.SolutionsPack;

import LinkedList.ListNode;
import leetPack.SolClass.NestedInteger;

import java.util.*;

public class Solution {
    // 1119. 删去字符串中的元音
    public String removeVowels(String S) {
        StringBuilder str = new StringBuilder();
        for(int i=0;i<S.length();i++){
            if(S.charAt(i) != 'a' && S.charAt(i) != 'e' && S.charAt(i) != 'i' && S.charAt(i) != 'o' && S.charAt(i) != 'u'){
                str.append(S.charAt(i));
            }
        }
        return str.toString();
    }

    // 1165. 单行键盘
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for(int i=0;i<keyboard.length();i++){
            hashMap.put(keyboard.charAt(i), i);
        }
        int step = hashMap.get(word.charAt(0));
        for (int i=1;i<word.length();i++){
            step = step + Math.abs(hashMap.get(word.charAt(i))-hashMap.get(word.charAt(i-1)));
        }
        return step;
    }

    // LCP 1. 猜数字
    public int game(int[] guess, int[] answer) {
        int num = 0;
        if (guess[0]==answer[0]){
            num++;
        }
        if (guess[1]==answer[1]){
            num++;
        }
        if (guess[2]==answer[2]){
            num++;
        }
        return num;
    }

    // 1281. 整数的各位积和之差
    public int subtractProductAndSum(int n) {
        String str = String.valueOf(n);
        int sum = 0;
        int acc = 1;
        for(int i=0;i<str.length();i++){
            int num = Integer.parseInt(str.substring(i,i+1));
            sum += num;
            acc *= num;
        }
        return acc-sum;
    }

    // 1295. 统计位数为偶数的数字
    public int findNumbers(int[] nums) {
        int sum = 0;
        for (int n : nums){
            if(String.valueOf(n).length()%2==0){
                sum++;
            }
        }
        return sum;
    }

    // 1313. 解压缩编码列表
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<nums.length;i+=2){
            for(int j=0;j<nums[i];j++){
                list.add(nums[i+1]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 771. 宝石与石头
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<J.length();i++){
            map.put(J.charAt(i), 0);
        }
        for(int i=0;i<S.length();i++){
            if(map.get(S.charAt(i)) != null){
                count++;
            }
        }
        return count;
    }

    // 1108. IP 地址无效化
    public String defangIPaddr(String address) {
        address.replaceAll(".", "[.]");
        return address;
    }

    // 面试题58 - II. 左旋转字符串
    public String reverseLeftWords(String s, int n) {
        return s.substring(n,s.length()) + s.substring(0,n);
    }

    // 1290. 二进制链表转整数
    public int getDecimalValue(ListNode head) {
        int length = 1;
        ListNode prev = head;
        while (prev.next != null){
            prev = prev.next;
            length ++;
        }
        return getDecimalValue(head, length-1);
    }
    private int getDecimalValue(ListNode head, int length) {
        if(head.next == null){
            return 1*head.val;
        }else{
            return getDecimalValue(head.next, length-1) + (int)Math.pow(2, length)*head.val;
        }
    }

    // 804. 唯一摩尔斯密码词
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word: words){
            StringBuilder res = new StringBuilder();
            for (int i=0;i<word.length();i++){
                res.append(codes[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }

    // 760. 找出变位映射
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<B.length;i++){
            map.put(B[i], i);
        }
        int[] C = new int[A.length];
        for (int i=0;i<A.length;i++){
            C[i] = map.get(A[i]);
        }
        return C;
    }

    // 1266. 访问所有点的最小时间
    public int minTimeToVisitAllPoints(int[][] points) {
        int move = 0;
        for(int i=1;i<points.length;i++){
            move += Math.max(Math.abs(points[i][0]-points[i-1][0]), Math.abs(points[i][1]-points[i-1][1]));
        }
        return move;
    }

    // 面试题17. 打印从1到最大的n位数
    public int[] printNumbers(int n) {
        int[] arr = new int[(int)Math.pow(10, n)-1];
        for(int i=0;i<arr.length;i++){
            arr[i] = i+1;
        }
        return arr;
    }

    // 面试题27. 二叉树的镜像
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null){
            return root;
        }else {
            TreeNode temp = mirrorTree(root.left);
            root.left = mirrorTree(root.right);
            root.right = temp;
            return root;
        }
    }

    // 面试题 04.02. 最小高度树
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length==0) return null;
        TreeNode root = new TreeNode(nums[nums.length/2]);
        return sortedArrayToBST(nums, root);
    }
    private TreeNode sortedArrayToBST(int[] nums, TreeNode node) {
        if(nums.length == 1){
            return new TreeNode(nums[0]);
        }else if (nums.length == 2){
            node = new TreeNode(nums[1]);
            node.left = new TreeNode(nums[0]);
            return node;
        }else{
            node = new TreeNode(nums[nums.length/2]);
            int lefts[] = new int[nums.length/2];
            for (int i=0;i<nums.length/2;i++){
                lefts[i] = nums[i];
            }
            node.left = sortedArrayToBST(lefts, node.left);
            int[] rights = new int[(nums.length%2==0)?(nums.length/2-1):(nums.length/2)];
            for (int i=nums.length/2+1, k=0;i<nums.length;i++, k++){
                rights[k] = nums[i];
            }
            node.right = sortedArrayToBST(rights, node.right);
            return node;
        }
    }

    // 面试题22. 链表中倒数第k个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;
        ListNode point = head;
        ListNode car = head;
        for (int i=1;i<k;i++){
            car = car.next;
        }
        while (car.next!=null){
            point = point.next;
            car = car.next;
        }
        return point;
    }

    // 面试题55 - I. 二叉树的深度
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }else{
            return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
        }
    }

    // 面试题05. 替换空格
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }

    // 237. 删除链表中的节点
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 1351. 统计有序矩阵中的负数
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] gd: grid){
            for (int i=gd.length-1;i>=0;i--){
                if (gd[i]<0){
                    count++;
                }else{
                    break;
                }
            }
        }
        return count;
    }

    // 339. 嵌套列表权重和
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }
    private int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger e: nestedList){
            if (e.isInteger()){
                sum += e.getInteger() * depth;
            }else{
                sum += depthSum(e.getList(), depth+1);
            }
        }
        return sum;
    }

    // 面试题06. 从尾到头打印链表
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        Stack<Integer> stack = new Stack<>();
        int length = 0;
        ListNode point = head;
        while (point != null){
            stack.push(point.val);
            length++;
            point = point.next;
        }
        int[] arr = new int[length];
        for (int i=0;i<length;i++){
            arr[i] = stack.pop();
        }
        return arr;
    }
    // 解法2
    public int[] reversePrint2(ListNode head) {
        return reversePrint2(head, 0);
    }
    private int[] reversePrint2(ListNode head, int depth) {
        if (head == null){
            return new int[depth];
        }else{
            int[] port = reversePrint2(head.next, depth+1);
            port[port.length-depth-1] = head.val;
            return port;
        }
    }

    // 1221. 分割平衡字符串
    public int balancedStringSplit(String s) {
        int r = 0;
        int l = 0;
        int count = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == 'R'){
                r++;
            }else{
                l++;
            }
            if (r == l){
                count++;
                r = l = 0;
            }
        }
        return count;
    }

    // 面试题24. 反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode point = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return point;
    }

    // 1323. 6 和 9 组成的最大数字
    public int maximum69Number (int num) {
        String str = String.valueOf(num);
        StringBuilder fnum = new StringBuilder();
        boolean flag = true;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)=='6' && flag){
                fnum.append('9');
                flag = false;
            }else{
                fnum.append(str.charAt(i));
            }
        }
        return Integer.parseInt(fnum.toString());
    }

    // 1021. 删除最外层的括号
    public String removeOuterParentheses(String S) {
        int num = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<S.length();i++){
            if (S.charAt(i)=='('){
                if (num != 0){
                    stringBuilder.append('(');
                }
                num++;
            }else{
                if (num != 1){
                    stringBuilder.append(')');
                }
                num--;
            }
        }
        return stringBuilder.toString();
    }

    // 938. 二叉搜索树的范围和
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val>=L && root.val<=R) {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }else if (root.val<L){
            return rangeSumBST(root.right, L, R);
        }else{
            return rangeSumBST(root.left, L, R);
        }
    }

    // 1085. 最小元素各数位之和
    public int sumOfDigits(int[] A) {
        Arrays.sort(A);
        String str = String.valueOf(A[0]);
        int S = 0;
        for (int i=0;i<str.length();i++){
            S +=Integer.parseInt(str.substring(i,i+1));
        }
        if (S%2==0){
            return 1;
        }else{
            return 0;
        }
    }

    // 面试题25. 合并两个排序的链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null){
            return null;
        }
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode point;
        if (l1.val <= l2.val){
            point = l1;
            point.next = mergeTwoLists(l1.next, l2);
        }else{
            point = l2;
            point.next = mergeTwoLists(l1, l2.next);
        }
        return point;
    }


    // 1252. 奇数值单元格的数目
    public int oddCells(int n, int m, int[][] indices) {
        boolean[][] switches = new boolean[n][m];
        for (int[] index: indices){
            for (int i=0;i<m;i++){
                switches[index[0]][i] = !(switches[index[0]][i]);
            }
            for (int i=0;i<n;i++){
                switches[i][index[1]] = !(switches[i][index[1]]);
            }
        }
        int count = 0;
        for (boolean[] swes: switches){
            for (boolean sw: swes){
                if (sw) count++;
            }
        }
        return count;
    }

    // 709. 转换成小写字母
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    // 1304. 和为零的N个唯一整数
    public int[] sumZero(int n) {
        int[] num = new int[n];
        if (n%2!=0){
            num[n/2] = 0;
        }
        int sum = 1;
        for (int i=0;i<n/2;i++){
            num[i] = sum + (int)(Math.random()*3);
            num[n-i-1] = -num[i];
            sum +=3;
        }
        return num;
    }

    // 面试题15. 二进制中1的个数
    public int hammingWeight(int n) {
        String str = Integer.toBinaryString(n);
        int count = 0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i) == '1') count++;
        }
        return count;
    }

    // 617. 合并二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    // 461. 汉明距离
    public int hammingDistance(int x, int y) {
        String strx = Integer.toBinaryString(x);
        String stry = Integer.toBinaryString(y);
        if (strx.length()>stry.length()){
            return hammingDistance(strx, stry);
        }else{
            return hammingDistance(stry, strx);
        }
    }
    private int hammingDistance(String x, String y){
        int count = 0;
        for (int i=0;i<x.length();i++){

            if (i<y.length()){
                if (x.charAt(x.length()-i-1) != y.charAt(y.length()-i-1)){
                    count++;
                }
            }else{
                if (x.charAt(x.length()-i-1) == '1'){
                    count++;
                }
            }
        }
        return count;
    }

    // 1213. 三个有序数组的交集
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List forks = new LinkedList();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int e: arr1){
            set1.add(e);
        }
        for (int e: arr2){
            set2.add(e);
        }
        for (int e: arr3){
            if (set1.contains(e) && set2.contains(e)){
                forks.add(e);
            }
        }
        return forks;
    }

    // 1134. 阿姆斯特朗数
    public boolean isArmstrong(int N) {
        String num = String.valueOf(N);
        int k = num.length();
        int sum = 0;
        for (int i=0;i<k;i++){
            sum += Math.pow(Integer.parseInt(num.substring(i, i+1)), k);
        }
        return sum==N;
    }

    // 832. 翻转图像
    public int[][] flipAndInvertImage(int[][] A) {
        Stack<Integer> stack = new Stack();
        for (int i=0;i<A.length;i++){
            for (int elem: A[i]){
                stack.push(elem==1?0:1);
            }
            for (int j=0;j<A[i].length;j++){
                A[i][j] = stack.pop();
            }
        }
        return A;
    }
    // 解法2
    public int[][] flipAndInvertImage2(int[][] A) {
        int x = A.length;
        int y = A[0].length;
        int[][] B = new int[x][y];
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                B[i][j] = A[i][y-j-1]==1?0:1;
            }
        }
        return B;
    }

    // 226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    // 1309. 解码字母到整数映射
    public String freqAlphabets(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a"); map.put("2", "b"); map.put("3", "c");
        map.put("4", "d"); map.put("5", "e"); map.put("6", "f");
        map.put("7", "g"); map.put("8", "h"); map.put("9", "i");
        map.put("10#", "j"); map.put("11#", "k"); map.put("12#", "l");
        map.put("13#", "m"); map.put("14#", "n"); map.put("15#", "o");
        map.put("16#", "p"); map.put("17#", "q"); map.put("18#", "r");
        map.put("19#", "s"); map.put("20#", "t"); map.put("21#", "u");
        map.put("22#", "v"); map.put("23#", "w"); map.put("24#", "x");
        map.put("25#", "y"); map.put("26#", "z");
        StringBuilder str = new StringBuilder();
        for (int i=0;i<s.length();i++){
            if ((s.charAt(i)=='1' || s.charAt(i)=='2') && i<s.length()-2) {
                if (s.charAt(i + 2) == '#') {
                    str.append(map.get(s.substring(i, i + 3)));
                    i += 2;
                    continue;
                }
            }
            str.append(map.get(s.substring(i, i+1)));
        }
        return str.toString();
    }
    // 解法2
    public String freqAlphabets2(String s) {
        StringBuilder str = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(i + 2 < s.length() && s.charAt(i + 2) == '#'){
                str.append((char)('a' - 1 + Integer.parseInt(s.substring(i,i+2))));
                i += 2;
            }else{
                str.append((char)('a' - 1 + s.charAt(i) - '0'));
            }
        }
        return str.toString();
    }

    // 1299. 将每个元素替换为右侧最大元素
    public int[] replaceElements(int[] arr) {
        int tMax = -1;
        int temp = 0;
        for (int i=arr.length-1;i>=0;i--){
            temp = arr[i];
            arr[i] = tMax;
            tMax = Math.max(temp, tMax);
        }
        return arr;
    }

    // 面试题54. 二叉搜索树的第k大节点
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new LinkedList();
        kthLargest(root, list);
        return list.get(list.size()-k);
    }
    public void kthLargest(TreeNode root, List list) {
        if (root == null) return;
        kthLargest(root.left, list);
        list.add(root.val);
        kthLargest(root.right, list);
    }

    // 面试题 01.01. 判定字符是否唯一
    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        for (int i=0;i<astr.length();i++){
            set.add(astr.charAt(i));
        }
        if (set.size() == astr.length()){
            return true;
        }else{
            return false;
        }
    }

    // 657. 机器人能否返回原点
    public boolean judgeCircle(String moves) {
        int point = 0;
        for (int i=0;i<moves.length();i++){
            switch (moves.charAt(i)){
                case 'L':
                    point++;
                    break;
                case 'R':
                    point--;
                    break;
                case 'U':
                    point +=2;
                    break;
                case 'D':
                    point -=2;
                    break;
                default:
            }
        }
        return point==0;
    }

    // 面试题 01.02. 判定是否互为字符重排
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length()!=s2.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i=0;i<c1.length;i++){
            if (c1[i]!=c2[i]){
                return false;
            }
        }
        return true;
    }

    // 1180. 统计只含单一字母的子串
    public int countLetters(String S) {
        int count = 1;
        int sum = 1;
        for (int i=1;i<S.length();i++){
            if (S.charAt(i)==S.charAt(i-1)){
                count++;
            }else{
                count = 1;
            }
            sum += count;
        }
        return sum;
    }

    // 728. 自除数
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new LinkedList<>();
        for (int i=left;i<=right;i++){
            int num = i;
            boolean flag = true;
            while (num>0){
                if (num%10==0 || i%(num%10)!=0){
                    flag = false;
                    break;
                }
                num /= 10;
            }
            if (flag){
                list.add(i);
            }
        }
        return list;
    }

    // 1051. 高度检查器
    public int heightChecker(int[] heights) {
        int count = 0;
        int[] stu = heights.clone();
        Arrays.sort(stu);
        for (int i=0;i<stu.length;i++){
            if (stu[i]!=heights[i]) count++;
        }
        return count;
    }


}
