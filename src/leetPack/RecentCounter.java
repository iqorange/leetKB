package leetPack;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

// 933. 最近的请求次数
class RecentCounter {

    ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

    public RecentCounter() {

    }

    public int ping(int t) {
        while(!queue.isEmpty()){
            int val = queue.peek();
            if(val < t-3000){
                queue.pop();
            }else break;
        }
        queue.add(t);
        return queue.size();
    }
}
