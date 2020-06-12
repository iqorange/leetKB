package leetPack.lanqiao;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int n = sca.nextInt();
        int m = sca.nextInt();
        int sub = n-m;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<n;i++){
            int num = sca.nextInt();
            queue.add(num);
            if (priorityQueue.size()<sub){
                priorityQueue.add(num);
            }else if (priorityQueue.peek()>num){
                priorityQueue.remove();
                priorityQueue.add(num);
            }
        }
        Set<Integer> set = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            set.add(priorityQueue.remove());
        }
        while (!queue.isEmpty()){
            Integer temp = queue.remove();
            if (!set.contains(temp)){
                if (queue.size()!=0){
                    System.out.print(temp+" ");
                }else{
                    System.out.println(temp);
                }
            }
        }
    }
}

