package leetPack;
import java.util.*;
public class Solution3 {
    // 961. 重复 N 次的元素
    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        for (int i=0;i<A.length-1;i++){
            if (A[i]==A[i+1]){
                return A[i];
            }
        }
        return 0;
    }
    // 解法2
    public int repeatedNTimes2(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int e: A){
            if (!set.add(e)){
                return e;
            }
        }
        return 0;
    }

    // 1150. 检查一个数是否在数组中占绝大多数
    public boolean isMajorityElement(int[] nums, int target) {
        if (nums.length == 1){
            if (nums[0] == target){
                return true;
            }else{
                return false;
            }
        }
        if (nums[nums.length/2-1]==target){
            return true;
        }else{
            return false;
        }
    }

    // 682. 棒球比赛
    public int calPoints(String[] ops) {
        int[] marks = new int[ops.length];
        int i = 0;
        for (String str: ops){
            if (str.equals("+")){
                marks[i] = marks[i-1] + marks[i-2];
                i++;
            }else if (str.equals("D")){
                marks[i] = marks[i-1]*2;
                i++;
            }else if (str.equals("C")){
                marks[i-1] = 0;
                i--;
            }else{
                marks[i] = Integer.parseInt(str);
                i++;
            }
        }
        int sum = 0;
        for (int j=0;j<marks.length;j++){
            sum += marks[j];
        }
        return sum;
    }

    // 811. 子域名访问计数
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String string: cpdomains){
            String[] strs = string.split(" ");
            map.put(strs[1], map.getOrDefault(strs[1], 0) + Integer.parseInt(strs[0]));
            String[] str = strs[1].split("\\.");
            if (str.length == 0){
                map.put(strs[1], map.getOrDefault(strs[1], 0) + Integer.parseInt(strs[0]));
            }else if (str.length == 3){
                String strTemp = str[1] + "." + str[2];
                map.put(strTemp, map.getOrDefault(strTemp, 0) + Integer.parseInt(strs[0]));
                map.put(str[str.length-1], map.getOrDefault(str[str.length-1], 0) + Integer.parseInt(strs[0]));
            }else{
                map.put(str[str.length-1], map.getOrDefault(str[str.length-1], 0) + Integer.parseInt(strs[0]));
            }
        }
        List<String> list = new LinkedList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            list.add(entry.getValue() + " " + entry.getKey());
        }
        return list;
    }

    // 118. 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new LinkedList<>();
        for (int i=0;i<numRows;i++){
            List<Integer> list = new LinkedList<>();
            if (i == 0){
                list.add(1);
                lists.add(list);
            }else if (i==1){
                list.add(1); list.add(1);
                lists.add(list);
            }else{
                list.add(1);
                for (int j=0;j<i-1;j++){
                    list.add(lists.get(i-1).get(j) + lists.get(i-1).get(j+1));
                }
                list.add(1);
                lists.add(list);
            }
        }
        return lists;
    }

    // 897. 递增顺序查找树
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        increasingBST(root, list);
        if (list.size() == 0) return null;
        TreeNode node = new TreeNode(list.get(0));
        TreeNode prev = node;
        for (int i=1;i<list.size();i++){
            prev.right = new TreeNode(list.get(i));
            prev = prev.right;
        }
        return node;
    }
    private void increasingBST(TreeNode root, List<Integer> list) {
        if (root == null) return;
        increasingBST(root.left, list);
        list.add(root.val);
        increasingBST(root.right, list);
    }

    // 1200. 最小绝对差
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minSub = 100000;
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> list;
        for (int i=0;i<arr.length-1;i++){
            int sub = Math.abs(arr[i]-arr[i+1]);
            if (sub<minSub){
                lists = new LinkedList<>();
                list = new LinkedList<>();
                list.add(arr[i]);
                list.add(arr[i+1]);
                lists.add(list);
                minSub = sub;
            }else if (sub == minSub){
                list = new LinkedList<>();
                list.add(arr[i]);
                list.add(arr[i+1]);
                lists.add(list);
            }
        }
        return lists;
    }

    // 258. 各位相加
    public int addDigits(int num) {
        String ans = String.valueOf(num);
        while (ans.length() > 1){
            int sum = 0;
            for (int i=0;i<ans.length();i++){
                sum += Integer.valueOf(ans.substring(i, i+1));
            }
            ans = String.valueOf(sum);
        }
        return Integer.parseInt(ans);
    }
    // 解法2
    public int addDigits2(int num) {
        return (num-1)%9 + 1;
    }

    // 575. 分糖果
    public int distributeCandies(int[] candies) {
        int kind = 0;
        Set<Integer> set = new HashSet<>();
        for (int e: candies){
            if (set.add(e)){
                kind++;
                if (kind == candies.length/2){
                    return kind;
                }
            }
        }
        return kind;
    }

    // 136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int num = 0;
        for (int e: nums){
            num = e^num;
        }
        return num;
    }

    // 面试题57. 和为s的两个数字
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int e: nums){
            if (!set.contains(target-e)){
                set.add(e);
            }else{
                return new int[]{e, target-e};
            }
        }
        return new int[]{};
    }

    // 463. 岛屿的周长
    public int islandPerimeter(int[][] grid) {
        int edge = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 1){
                    edge+=4;
                    if (i>0 && grid[i-1][j] == 1){
                        edge--;
                    }
                    if (i<grid.length-1 && grid[i+1][j] == 1){
                        edge--;
                    }
                    if (j>0 && grid[i][j-1] == 1){
                        edge--;
                    }
                    if (j<grid[0].length-1 && grid[i][j+1] ==1){
                        edge--;
                    }
                }
            }
        }
        return edge;
    }

    // 1122. 数组的相对排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap();
        Queue<Integer> queue = new ArrayDeque<>();
        Arrays.sort(arr1);
        int[] arr = new int[arr1.length];
        int t = arr2.length;
        int i = 0;
        for (int e: arr2){
            set.add(e);
        }
        for (int e: arr1){
            if (set.contains(e)){
                map.put(e, map.getOrDefault(e, 0)+1);
            }else{
                queue.add(e);
            }
        }
        for (int e: arr2){
            for (int k=0;k<map.get(e);k++){
                arr[i] = e;
                i++;
            }
        }
        while (!queue.isEmpty()){
            arr[i] = queue.remove();
            i++;
        }
        return arr;
    }


}
