package leetPack.Arrays;

import java.util.*;

public class ArraySolution {
    // 283. 移动零
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i] != 0){
                if (i != k){
                    int t = nums[k];
                    nums[k] = nums[i];
                    nums[i] = t;
                }
                k++;
            }
        }
    }

    // 27. 移除元素
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int cc = 0;
        for (int e: nums){
            if (e != val){
                nums[cc] = e;
                cc++;
            }
        }
        return cc;
    }

    // 26. 删除排序数组中的重复项
    public int removeDuplicates(int[] nums) {
        if (nums == null){
            return 0;
        }
        if (nums.length <= 1){
            return nums.length;
        }
        int cc = 0;
        for (int e: nums) {
            if (nums[cc] != e) {
                nums[++cc] = e;
            }
        }
        cc++;
        return cc;
    }

    // 80. 删除排序数组中的重复项 II
    public int removeDuplicates2(int[] nums) {
        if (nums == null){
            return 0;
        }
        if (nums.length<=2){
            return nums.length;
        }
        int cc = 0;
        for (int e: nums){
            if (cc<2 || e>nums[cc-2]){
                nums[cc++] = e;
            }
        }
        return cc;
    }

    // 75. 颜色分类
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int i=0;i<nums.length;i++){
            if (nums[i]>=0 && nums[i]<=2) {
                count[nums[i]]++;
            }else{
                throw new IllegalArgumentException("Error Color~");
            }
        }
        int index = 0;
        for (int i=0;i<count.length;i++){
            for (int j = 0;j<count[i];j++){
                nums[index++] = i;
            }
        }
    }
    // 解法2(三路快排)
    public void sortColors2(int[] nums) {
        int zero = -1;
        int two = nums.length;
        for (int i=0;i<two;){
            if (nums[i] == 1){
                i++;
            }else if (nums[i] == 2){
                two--;
                int t = nums[i];
                nums[i] = nums[two];
                nums[two]  = t;
            }else if (nums[i] == 0){
                zero++;
                int t = nums[i];
                nums[i] = nums[zero];
                nums[zero]  = t;
                i++;
            }else{
                throw new IllegalArgumentException("Error Color~");
            }
        }
    }

    // 88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 最小堆的方式
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i=0;i<m;i++){
            priorityQueue.add(nums1[i]);
        }
        for (int e: nums2){
            priorityQueue.add(e);
        }
        for (int i=0;i<(m+n);i++){
            nums1[i] = priorityQueue.remove();
        }
        priorityQueue = null;
    }
    // 解法2
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }

    // 215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        if (k>nums.length){
            throw new IllegalArgumentException("Out of nums length~");
        }
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    // 167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length<2){
            return new int[2];
        }
        int left = 0;
        int right = numbers.length-1;
        while (left<right){
            if (numbers[left] + numbers[right] == target){
                int[] res = {left+1, right+1};
                return res;
            }
            if (numbers[left] + numbers[right] < target){
                left++;
            }else{
                right--;
            }
        }
        throw new IllegalArgumentException("No Solution~");
    }

    // 125. 验证回文串
    public boolean isPalindrome(String s) {
        if (s == null || s.equals("")){
            return true;
        }
        s = s.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        for (char e: s.toCharArray()){
            if ((e>='a' && e<='z') || (e>='0' && e<='9')){
                stringBuilder.append(e);
            }
        }
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }

    // 345. 反转字符串中的元音字母
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int leftCar = 0;
        int rightCar = s.length()-1;
        char[] str = s.toCharArray();
        while (leftCar<rightCar){
            if (!set.contains(str[leftCar])){
                leftCar++;
                continue;
            }
            if (!set.contains(str[rightCar])){
                rightCar--;
                continue;
            }
            char t = str[leftCar];
            str[leftCar] = str[rightCar];
            str[rightCar] = t;
            leftCar++;
            rightCar--;
        }
        return String.valueOf(str);
    }

    // 11. 盛最多水的容器
    public int maxArea(int[] height) {
        int leftWall = 0;
        int rightWall = height.length-1;
        int res = 0;
        while (leftWall<rightWall){
            int minWall = Math.min(height[leftWall], height[rightWall]);
            res = Math.max(res, minWall*(rightWall-leftWall));
            if (height[leftWall] < height[rightWall]){
                leftWall++;
            }else{
                rightWall--;
            }
        }
        return res;
    }

    // 209. 长度最小的子数组
    public int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = -1;
        int sum = 0;
        int res = nums.length+1;
        // 滑动窗口
        while (l<nums.length){
            if ( r+1 < nums.length && sum<s){
                sum += nums[++r];
            }else{
                sum -= nums[l++];
            }
            if (sum>=s){
                res = Math.min(res, r-l+1);
            }
        }
        if (res == nums.length+1){
            return 0;
        }
        return res;
    }

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int l = 0;
        int r = -1;
        int result = 0;

        while (l<s.length()){
            if (r+1<s.length() && freq.getOrDefault(s.charAt(r+1), 0) == 0){
                r++;
                freq.put(s.charAt(r), freq.getOrDefault(s.charAt(r), 0) + 1);
            }else{
                freq.put(s.charAt(l), freq.getOrDefault(s.charAt(l), 0) - 1);
                l++;
            }
            result = Math.max(result, r-l+1);
        }
        return result;
    }

    // 438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.equals("") || s.length() < p.length()) return list;
        int[] hash = new int[256];
        for (int j = 0; j < p.length(); j++) {
            hash[p.charAt(j)]++;
        }
        int count = p.length();
        int left = 0, right = 0;
        while (right < s.length()) {
            if (hash[s.charAt(right++)]-- >= 1) count--;
            if (count == 0) list.add(left);
            if ((right - left) == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }

    // 76. 最小覆盖子串 **
    public static String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        // 用来统计t中每个字符出现次数
        int[] needs = new int[128];
        // 用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];
        for (int i = 0; i < t.length(); i++) {
            needs[t.charAt(i)]++;
        }
        int left = 0;
        int right = 0;
        String res = "";
        // 目前有多少个字符
        int count = 0;
        // 用来记录最短需要多少个字符。
        int minLength = s.length() + 1;
        while (right < s.length()) {
            char ch = s.charAt(right);
            window[ch]++;
            if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                count++;
            }
            // 移动到不满足条件为止
            while (count == t.length()) {
                ch = s.charAt(left);
                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                window[ch]--;
                left++;
            }
            right++;
        }
        return res;
    }
}
