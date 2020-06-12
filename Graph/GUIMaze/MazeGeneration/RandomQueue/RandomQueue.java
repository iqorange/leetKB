package Graph.GUIMaze.MazeGeneration.RandomQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// 尝试改写成Deque的形式
public class RandomQueue<Element> {
//    private BasicDataStructure.LinkedList<Element> queue;
    private Deque<Element> deque;

    public RandomQueue(){
//        queue = new BasicDataStructure.LinkedList<>();
        deque = new ArrayDeque<>();
    }

    public void add(Element e){
        if (Math.random() < 0.6){
            deque.addFirst(e);
        }else{
            deque.addLast(e);
        }
    }

    public Element remove(){
        if (deque.size() == 0){
            throw new IllegalArgumentException("There is no element to remove in RandomQueue~");
        }
//        int randIndex = (int)(Math.random() * queue.size());
//        Element randomElement = queue.get(randIndex);
//        // 最后一个元素与随机一个元素交换
//        queue.set(randIndex, queue.get(queue.size()-1));
//        queue.remove(queue.size()-1);
//        return randomElement;
        if (Math.random() > 0.6){
            return deque.removeFirst();
        }else{
            return deque.removeLast();
        }
    }

    public int size(){
        return deque.size();
    }

    public boolean isEmpty(){
        return deque.size() == 0;
    }
}
