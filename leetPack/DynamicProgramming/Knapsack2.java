package leetPack.DynamicProgramming;

import java.util.Arrays;

// 背包问题优化
public class Knapsack2 {
    public int knapsack(final int[] w, final int[] v, int C){
        assert w.length == v.length && C>=0;
        int n = w.length;
        if (n == 0) return 0;
        int[] memo = new int[C + 1];
        Arrays.fill(memo, -1);
        for(int j=0;j<=C;j++){
            memo[j] = (j >= w[0] ? v[0] : 0);
        }
        for(int i=1;i<n;i++){
            for(int j=C;j>=w[i];j--)
                memo[j] = Math.max(memo[j], v[i] + memo[j-w[i]]);
        }
        return memo[C];
    }
}
