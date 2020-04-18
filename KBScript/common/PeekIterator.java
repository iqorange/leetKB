package KBScript.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

// 实现流的peek和putBack操作，返回迭代器
public class PeekIterator<E> implements Iterator<E> {

    private Iterator<E> iterator;
    private E endToken = null;

    private final static int CACHE_SIZE = 10;

    // 缓存队列
    private LinkedList<E> queue = new LinkedList<>();
    private LinkedList<E> stackPutBacks = new LinkedList<>();

    public PeekIterator(Stream<E> stream){
        iterator = stream.iterator();
    }

    // 流的后面追加结束符
    public PeekIterator(Stream<E> stream, E endToken){
        iterator = stream.iterator();
        this.endToken = endToken;
    }

    public E peek(){
        if (this.stackPutBacks.size()>0){
            return this.stackPutBacks.getFirst();
        }
        if (!iterator.hasNext()){
            return endToken;
        }
        E val = next();
        this.putBack();
        return val;
    }

    // peek的放回
    public void putBack(){
        if (this.queue.size()>0) {
            this.stackPutBacks.push(this.queue.pollLast());
        }
    }

    @Override
    public boolean hasNext() {
        return endToken != null || this.stackPutBacks.size() > 0 || iterator.hasNext();
    }

    @Override
    public E next() {
        E val = null;
        if (this.stackPutBacks.size()>0){
            val = this.stackPutBacks.pop();
        }else{
            if (!this.iterator.hasNext()){
                E temp = endToken;
                endToken = null;
                return temp;
            }
            val = iterator.next();
        }
        while (queue.size() > CACHE_SIZE - 1) {
            queue.poll();
        }
        queue.add(val);
        return val;
    }
}
