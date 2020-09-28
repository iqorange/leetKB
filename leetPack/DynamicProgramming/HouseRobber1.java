package leetPack.DynamicProgramming;

import java.util.Arrays;

public class HouseRobber1 {
    // 198. 打家劫舍
    private int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);
    }
    private int tryRob(int[] nums, int index){
        if(index >= nums.length) return 0;
        if(memo[index] != -1) return memo[index];
        int res = 0;
        for(int i = index;i<nums.length;i++){
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = res;
        return res;
    }
}
