package leetPack.SolClass;

import java.util.Stack;

// 面试题09. 用两个栈实现队列
class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(value);
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    public int deleteHead() {
        if (stack2.isEmpty()){
            return -1;
        }else{
            return stack2.pop();
        }
    }
}