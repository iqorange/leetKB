package leetPack.DynamicProgramming;

import AdvancedDataStructure.MaxHeap.Array;

import java.util.Arrays;

// 背包问题
public class Knapsack {
    // 自顶向下
    private int[][] memo1;
    public int knapsack1(final int[] w, final int[] v, int C){
        memo1 = new int[w.length][C + 1];
        Arrays.fill(memo1, -1);
        return bestValue(w, v, w.length-1, C);
    }
    private int bestValue(final int[] w, final int[] v, int index, int c){
        if(index < 0 || c <= 0) return 0;
        if(memo1[index][c] != -1) return memo1[index][c];
        int res = bestValue(w, v, index - 1, c);
        if(c >= w[index]){
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }
        memo1[index][c] = res;
        return res;
    }
    // 自底向上 - 动态规划
    public int knapsack2(final int[] w, final int[] v, int C){
        assert w.length == v.length;
        int n = w.length;
        if (n == 0) return 0;
        int[][] memo = new int[n][C + 1];
        Arrays.fill(memo, -1);
        for(int j=0;j<=C;j++){
            memo[0][j] = (j >= w[0] ? v[0] : 0);
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=C;j++){
                memo[i][j] = memo[i - 1][j];
                if (j >= w[i]){
                    memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
                }
            }
        }
        return memo[n-1][C];
    }
}
