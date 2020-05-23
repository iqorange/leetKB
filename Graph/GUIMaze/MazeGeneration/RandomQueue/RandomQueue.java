package Graph.GUIMaze.MazeGeneration.RandomQueue;

import java.util.ArrayList;
import java.util.LinkedList;

public class RandomQueue<Element> {
    private LinkedList<Element> queue;

    public RandomQueue(){
        queue = new LinkedList<>();
    }

    public void add(Element e){
        if (Math.random() < 0.6){
            queue.addFirst(e);
        }else{
            queue.addLast(e);
        }
    }

    public Element remove(){
        if (queue.size() == 0){
            throw new IllegalArgumentException("There is no element to remove in RandomQueue~");
        }
//        int randIndex = (int)(Math.random() * queue.size());
//        Element randomElement = queue.get(randIndex);
//        // 最后一个元素与随机一个元素交换
//        queue.set(randIndex, queue.get(queue.size()-1));
//        queue.remove(queue.size()-1);
//        return randomElement;
        if (Math.random() > 0.6){
            return queue.removeFirst();
        }else{
            return queue.removeLast();
        }
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.size() == 0;
    }
}
