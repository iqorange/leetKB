package leetPack.Arrays;

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
}
