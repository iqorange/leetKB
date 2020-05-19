package leetPack.Arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

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
}
