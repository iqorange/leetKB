package leetPack.Stack;

import java.nio.file.Paths;
import java.util.*;

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

    // 144. 二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
            list.add(node.val);
        }
        return list;
    }

    // 94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null){
                list.add(node.val);
                continue;
            }
            if (node.right != null){
                stack.push(node.right);
            }
            stack.push(new TreeNode(node.val));
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }

    // 145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null){
                list.add(node.val);
                continue;
            }
            stack.push(new TreeNode(node.val));
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }

    // 341. 扁平化嵌套列表迭代器
    public class NestedIterator implements Iterator<Integer> {
        Deque<Integer> deque;
        public NestedIterator(List<NestedInteger> nestedList) {
            deque = new ArrayDeque<>();
            flat(nestedList);
        }

        private void flat(List<NestedInteger> nestedList){
            for (NestedInteger nestedInteger: nestedList){
                if (nestedInteger.isInteger()){
                    deque.addFirst(nestedInteger.getInteger());
                }else{
                    flat(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return deque.removeLast();
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }
    }
}
