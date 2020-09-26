package leetPack.DynamicProgramming;

public class IntegerBreak {
    public int integerBreak(int n) {
        assert n >= 2;
        int[] memo = new int[n + 1];
        memo[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i-1;j++){
                memo[i] = maxAll(false, memo[i], j * (i - j), j * memo[i-j]);
            }
        }
        return memo[n];
    }

    private int maxAll(boolean miniVal, int ...a){
        int maxVal = miniVal ? Integer.MIN_VALUE : 0;
        for(int e : a){
            maxVal = Math.max(maxVal, e);
        }
        return maxVal;
    }
}
