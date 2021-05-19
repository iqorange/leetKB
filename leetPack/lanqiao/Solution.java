package leetPack.lanqiao;

public class Solution {
    private int maxVal = Integer.MIN_VALUE;
    public int rob(int[] nums) {
        find(nums, 0, 0);
        find(nums, 1, 0);
        return maxVal;
    }
    private void find(int[] nums, int point, int sum){
        sum = sum + nums[point];
        if(point+2 >= nums.length) {
            maxVal = Math.max(sum, maxVal);
        }

        for(int i=point+2;i<nums.length;i++){
            find(nums, i, sum);
        }
    }

}
