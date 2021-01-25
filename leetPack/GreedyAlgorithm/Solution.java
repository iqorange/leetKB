package leetPack.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    // 455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = g.length - 1, si = s.length - 1;
        int res = 0;
        while(gi >= 0 && si >= 0){
            if(s[si] >= g[gi]){
                res ++;
                si --;
            }
            gi --;
        }
        return res;
    }
}
