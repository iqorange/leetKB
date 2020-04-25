package leetPack.SolClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class WaterPuzzle {
    private int[] pre;
    private int end = -1;

    public WaterPuzzle(){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[54];
        pre = new int[54];
        queue.add(0);
        visited[0] = true;
        // BFS
        while (!queue.isEmpty()){
            int current = queue.remove();
            int a = current / 10;
            int b = current % 10;
            // Max a = 5, Max b = 3
            ArrayList<Integer> nexts = new ArrayList<>();

            // 给桶里灌满水
            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);
            // 把桶里的水倒掉
            nexts.add(b);
            nexts.add(a*10);
            // a桶倒入b桶中
            int x = Math.min(a, 3-b);
            nexts.add((a-x) * 10 + (b+x));
            // b桶倒入a桶中
            int y = Math.min(5-a, b);
            nexts.add((a+y) * 10 + (b-y));

            for (int next: nexts){
                if (!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = current;
                    if (next / 10 == 4 || next % 10 == 4){
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1){
            return res;
        }
        int cur = end;
        while (cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println((new WaterPuzzle()).result());
    }
}
