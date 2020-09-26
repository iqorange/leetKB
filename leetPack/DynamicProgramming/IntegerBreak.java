package leetPack.DynamicProgramming;

public class IntegerBreak {
    private int[] memo;

    public int integerBreak(int n) {
        assert n >= 2;
        memo = new int[n + 1];
        return breakInteger(n);
    }

    private int breakInteger(int n){
        if (n == 1) return 1;
        if(memo[n] != -1) return memo[n];
        int res = -1;
        for(int i=1;i<n;i++){
            res = maxAll(1, res, i * (n - i), i * breakInteger(n - i));
        }
        memo[n] = res;
        return res;
    }

    private int maxAll(int miniVal, int ...a){
        int maxVal = miniVal;// 这里只考虑正整数情况
        for(int e : a){
            maxVal = Math.max(maxVal, e);
        }
        return maxVal;
    }
}
