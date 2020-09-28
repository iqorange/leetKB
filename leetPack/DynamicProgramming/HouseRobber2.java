package leetPack.DynamicProgramming;

import java.util.Arrays;

public class HouseRobber2 {
    // 198. 打家劫舍
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        // 最后一个房子
        memo[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            // 往前递推
            for(int j=i;j<n;j++){
                memo[i] = Math.max(memo[i], nums[j] + (j + 2 < n ? memo[j + 2] : 0));
            }
        }
        return memo[0];
    }
}
