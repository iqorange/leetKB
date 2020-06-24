package leetPack.Stack;

import java.nio.file.Paths;
import java.util.Stack;

public class Solution {
    // 20. 有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char temp = s.charAt(i);
            if (temp == '(' || temp == '[' || temp == '{'){
                stack.push(temp);
            }else{
                if (stack.isEmpty()) return false;
                char c = stack.pop();
                char match = 0;
                switch (temp) {
                    case ')' -> match = '(';
                    case ']' -> match = '[';
                    case '}' -> match = '{';
                }
                if (match == 0 || c != match){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 150. 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token: tokens){
            switch (token){
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> stack.push(- stack.pop() + stack.pop());
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    Integer divisor = stack.pop();
                    stack.push(stack.pop() / divisor);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    // 71. 简化路径
    public String simplifyPath(String path) {
        return Paths.get(path).normalize().toString();
    }
}
