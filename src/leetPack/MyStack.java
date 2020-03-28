package leetPack;

import java.util.LinkedList;
import java.util.Queue;

// 225. 用队列实现栈
class MyStack {

    private Queue<Integer> queue;
    private Queue<Integer> temp;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
        temp = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue.size()==0){
            throw new IllegalArgumentException("Stack is empty.");
        }
        while (queue.size()>1){
            temp.add(queue.remove());
        }
        int pop = queue.remove();
        while (!temp.isEmpty()){
            queue.add(temp.remove());
        }
        return pop;
    }

    /** Get the top element. */
    public int top() {
        if (queue.size()==0){
            throw new IllegalArgumentException("Stack is empty.");
        }
        while (queue.size()>1){
            temp.add(queue.remove());
        }
        int top = queue.remove();
        temp.add(top);
        while (!temp.isEmpty()){
            queue.add(temp.remove());
        }
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */