package leetPack;

import java.util.Stack;

// 面试题 03.04. 化栈为队
class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(x);
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!stack2.isEmpty()){
            return stack2.pop();
        }else{
            return 0;
        }
    }

    /** Get the front element. */
    public int peek() {
        return stack2.get(stack2.size()-1);
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack2.isEmpty();
    }
}
