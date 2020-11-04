package leetPack.DynamicProgramming;

import java.util.Arrays;

public class CanPartition {
    // 416. 分割等和子集
    // -1表示未计算，0表示不可以填充，1表示可以填充
    int [][] memos;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            assert num > 0;
            sum += num;
        }
        // 不是偶数的话无法被两个证书分开
        if (sum % 2 != 0){
            return false;
        }
        memos = new int[nums.length][sum / 2 + 1];
        Arrays.fill(memos, -1);
        return tryPartition(nums, nums.length - 1, sum / 2);
    }
    private boolean tryPartition(final int[] nums, int index, int sum){
        if(sum == 0){
            return true;
        }
        if (sum < 0 || index < 0){
            return false;
        }
        if (memos[index][sum] != -1){
            return memos[index][sum] == 1;
        }
        memos[index][sum] = tryPartition(nums, index - 1, sum) || tryPartition(nums, index - 1, sum - nums[index]) ? 1 : 0;
        return memos[index][sum] == 1;
    }

    // 动态规划的方式
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            assert num > 0;
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int C = sum / 2;
        boolean[] memo = new boolean[C + 1];
        Arrays.fill(memo, false);
        for (int i=0;i<=C;i++){
            memo[i] = (nums[0] == i);
        }
        for (int i=1;i<n;i++){
            for (int j=C;j>=nums[i];j--){
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }
        return memo[C];
    }
}
