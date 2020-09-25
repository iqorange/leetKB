package leetPack.DynamicProgramming;

public class ClimbStairs {
    public int climbStairs(int n) {
        assert n > 0;
        return calcWays(n);
    }
    private int calcWays(int n) {
        if(n == 0 || n == 1) return 1;
        return calcWays(n - 1) + calcWays(n - 2);
    }
}
