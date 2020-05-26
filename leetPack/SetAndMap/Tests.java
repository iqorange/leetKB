package leetPack.SetAndMap;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Tests {
    @Test
    public void test(){
        Solutions solutions = new Solutions();
        int[] arr = {-3, 4, 3, 16};
        System.out.println(Arrays.toString(solutions.twoSum(arr, 0)));
    }

    @Test
    public void test2(){
//        Solutions solutions = new Solutions();
        int[] arr = {3,0,-2,-1,1,2};
//        System.out.println(solutions.threeSum(arr));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int e: arr){
            queue.add(e);
        }
        while (!queue.isEmpty()){
            System.out.println(queue.remove());
        }
    }
}
