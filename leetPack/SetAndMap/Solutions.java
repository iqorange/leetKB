package leetPack.SetAndMap;

import java.util.*;

public class Solutions {
    // 242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i=0;i<s.length();i++){
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i=0;i<t.length();i++){
            map2.put(t.charAt(i), map2.getOrDefault(t.charAt(i), 0) + 1);
        }
        return map1.equals(map2);
    }
    // 解法2
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        for (int i=0;i<str1.length;i++){
            if (str1[i] != str2[i]){
                return false;
            }
        }
        return true;
    }

    // 290. 单词规律
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length){
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i=0;i<pattern.length();i++){
            if (map.containsKey(pattern.charAt(i))){
                if (!map.get(pattern.charAt(i)).equals(words[i])){
                    return false;
                }
            }else{
                map.put(pattern.charAt(i), words[i]);
                set.add(words[i]);
            }
        }
        return map.size() == set.size();
    }

    // 205. 同构字符串
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i=0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) return false;
            }else{
                map.put(s.charAt(i), t.charAt(i));
                set.add(t.charAt(i));
            }
        }
        return map.size() == set.size();
    }

    // 451. 根据字符出现频率排序
    public String frequencySort(String s) {
        if (s.length()<=2) return s;
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i=0;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        queue.addAll(map.entrySet());
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()){
            Map.Entry<Character, Integer> entry = queue.remove();
            res.append(String.valueOf(entry.getKey()).repeat(Math.max(0, entry.getValue())));
        }
        return res.toString();
    }

    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        int[] towTag = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target - nums[i])){
                towTag[0] = map.get(target - nums[i]);
                towTag[1] = i;
                return towTag;
            }
            map.put(nums[i], i);
        }
        return towTag;
    }

    // 15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        PriorityQueue<Integer> queue  = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int e: nums){
            queue.add(e);
        }
        while (queue.size() > 2){
            int left = queue.remove();
            map.put(left, map.getOrDefault(left, 0) + 1);
            if (map.get(left) > 3) continue;
            LinkedList<Integer> linkedList = new LinkedList<>(queue);
            // 优先队列是边输出边整理的，所以直接导过来的时候要排序一下
            Collections.sort(linkedList);
            int mid = linkedList.removeFirst();
            int right = linkedList.removeLast();
            if (linkedList.isEmpty() && left + mid + right == 0){
                addToList(lists, left, mid, right);
            }
            while (!linkedList.isEmpty()) {
                if (left + mid + right == 0){
                    addToList(lists, left, mid, right);
                    mid = linkedList.removeFirst();
                    if (linkedList.size() > 0) right = linkedList.removeLast();
                }else if (left + mid + right > 0){
                    right = linkedList.removeLast();
                }else{
                    mid = linkedList.removeFirst();
                }
                if (linkedList.isEmpty() && left + mid + right == 0) {
                    addToList(lists, left, mid, right);
                }
            }
        }
        return new LinkedList<>(new HashSet<List<Integer>>(lists));
    }
    private void addToList(List<List<Integer>> lists, int left, int mid, int right){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(left); list.add(mid); list.add(right);
        Collections.sort(list);
        lists.add(list);
    }

    // 16. 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        if (nums.length ==3){
            return sum;
        }
        for (int i=0;i<nums.length-2;i++){
            int left = i+1;
            int right = nums.length-1;
            while (left<right){
                int newSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(newSum - target) < Math.abs(sum - target)){
                    sum = newSum;
                }
                if (newSum - target > 0){
                    right--;
                }else if (newSum -target < 0){
                    left++;
                }else{
                    return newSum;
                }
            }
        }
        return sum;
    }

    // 454. 四数相加 II
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || B == null || C == null || D == null){
            throw new IllegalArgumentException("Illegal argument");
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item : C) {
            for (int value : D) {
                int sum = item + value;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int result = 0;
        for (int item: A){
            for (int value: B){
                result += map.getOrDefault(-item - value, 0);
            }
        }
        return result;
    }

    // 49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs){
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String ss = String.valueOf(s);
            List<String> list = map.getOrDefault(ss, new LinkedList<>());
            list.add(str);
            map.put(ss, list);
        }
        for (Map.Entry<String, List<String>> entry: map.entrySet()){
            lists.add(entry.getValue());
        }
        return lists;
    }

    // 447. 回旋镖的数量
    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        for (int[] pointA : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] pointB : points) {
                int distance = distance(pointA, pointB);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (Integer distance: map.keySet()){
                result += map.get(distance) * (map.get(distance) - 1);
            }
        }
        return result;
    }
    private int distance(int[] pa, int[] pb){
        return (pa[0] - pb[0]) * (pa[0] - pb[0]) + (pa[1] - pb[1]) * (pa[1] - pb[1]);
    }

    // 219. 存在重复元素 II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1){
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<nums.length;i++){
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
            if (set.size() == k + 1){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    // 217. 存在重复元素
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2){
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int e: nums){
            if (set.contains(e)){
                return true;
            }
            set.add(e);
        }
        return false;
    }

    // 220. 存在重复元素 III
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k < 1){
            return false;
        }
        TreeSet<Long> set = new TreeSet<>();
        for (int i=0;i<nums.length;i++){
            if (set.ceiling((long)nums[i] - (long)t) != null && set.ceiling((long)nums[i] - (long)t) <= (long)nums[i] + (long)t){
                return true;
            }
            set.add((long)nums[i]);
            if (set.size() == k + 1){
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }
}
