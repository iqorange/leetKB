package leetPack;

import java.util.Stack;

// 1381. 设计一个支持增量操作的栈
public class CustomStack {

    private Stack<Integer> stack;
    private Stack<Integer> temp;
    private int maxSize;

    public CustomStack(int maxSize) {
        this.stack = new Stack<>();
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (stack.size()<maxSize){
            stack.push(x);
        }
    }

    public int pop() {
        if (stack.isEmpty()){
            return -1;
        }
        return stack.pop();
    }

    public void increment(int k, int val) {
        if (!stack.isEmpty()){
            temp = new Stack<>();
            while (!stack.isEmpty()){
                temp.push(stack.pop());
            }
            while (!temp.isEmpty()){
                if (k != 0){
                    stack.push(temp.pop()+val);
                    k--;
                }else{
                    stack.push(temp.pop());
                }
            }
            temp = null;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */