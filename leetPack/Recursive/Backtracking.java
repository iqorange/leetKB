package leetPack.Recursive;

import java.util.*;

public class Backtracking {
    // 17. 电话号码的字母组合
    List<String> letterRes = new ArrayList<>();
    Map<Character, String> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) return letterRes;
        map.put('2', "abc"); map.put('3', "def"); map.put('4', "ghi");
        map.put('5', "jkl"); map.put('6', "mno"); map.put('7', "pqrs");
        map.put('8', "tuv"); map.put('9', "wxyz");
        findCombination(digits, 0, "");
        return letterRes;
    }
    private void findCombination(final String digits, int index, final String s){
        if (index == digits.length()){
            letterRes.add(s);
            return;
        }
        char c = digits.charAt(index);
        assert c >= '0' && c <='9' && c != '1';
        String letters = map.get(c);
        for (int i=0;i<letters.length();i++){
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
    }

    // 93. 复原IP地址 **
    List<String> list = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        turnStep(s, 0, 0, new StringBuilder());
        return list;
    }
    private void turnStep(String s, int start, int pos, StringBuilder builder){
        if (pos == 4){
            if (start == s.length()){
                list.add(builder.toString().substring(0, builder.length() - 1));
            }
            return;
        }
        for (int i=start;i<start+3&&i<s.length();i++){
            String sub = s.substring(start, i + 1);
            int num = Integer.parseInt(sub);
            if (num > 255) continue;
            builder.append(s.substring(start, i + 1) + ".");
            turnStep(s, i + 1, pos +1, builder);
            builder.delete(builder.length() - (i - start + 2), builder.length());
            if (s.charAt(start) == '0') break;
        }
    }

    // 131. 分割回文串 ***
    public List<List<String>> partition(String s) {
        int length = s.length();
        List<List<String>> lists = new ArrayList<>();
        if (length == 0){
            return lists;
        }
        Deque<String> deque = new ArrayDeque<>();
        backtracking(s, 0, length, deque, lists);
        return lists;
    }
    private void backtracking(String s, int start, int length, Deque<String> path, List<List<String>> lists){
        if (start == length){
            lists.add(new ArrayList<>(path));
        }
        for (int i=start;i<length;i++){
            if (!checkPalindrome(s, start, i)){
                continue;
            }
            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, length, path, lists);
            path.removeLast();
        }
    }
    private boolean checkPalindrome(String str, int left, int right){
        while (left < right){
            if (str.charAt(left) != str.charAt(right)){
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}
