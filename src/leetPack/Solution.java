package leetPack;

import LinkedList.ListNode;

import javax.xml.soap.Node;
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
    public int getDecimalValue(ListNode head, int length) {
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
    public TreeNode sortedArrayToBST(int[] nums, TreeNode node) {
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
}
