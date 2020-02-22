package leetPack;

import LinkedList.ListNode;

import javax.xml.soap.Node;
import java.util.*;

public class Solution {
    // 1119. 删去字符串中的元音
    public String removeVowels(String S) {
        StringBuilder str = new StringBuilder();
        for(int i=0;i<S.length();i++){
            if(S.charAt(i) != 'a' && S.charAt(i) != 'e' && S.charAt(i) != 'i' && S.charAt(i) != 'o' && S.charAt(i) != 'u'){
                str.append(S.charAt(i));
            }
        }
        return str.toString();
    }

    // 1165. 单行键盘
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for(int i=0;i<keyboard.length();i++){
            hashMap.put(keyboard.charAt(i), i);
        }
        int step = hashMap.get(word.charAt(0));
        for (int i=1;i<word.length();i++){
            step = step + Math.abs(hashMap.get(word.charAt(i))-hashMap.get(word.charAt(i-1)));
        }
        return step;
    }

    // LCP 1. 猜数字
    public int game(int[] guess, int[] answer) {
        int num = 0;
        if (guess[0]==answer[0]){
            num++;
        }
        if (guess[1]==answer[1]){
            num++;
        }
        if (guess[2]==answer[2]){
            num++;
        }
        return num;
    }

    // 1281. 整数的各位积和之差
    public int subtractProductAndSum(int n) {
        String str = String.valueOf(n);
        int sum = 0;
        int acc = 1;
        for(int i=0;i<str.length();i++){
            int num = Integer.parseInt(str.substring(i,i+1));
            sum += num;
            acc *= num;
        }
        return acc-sum;
    }

    // 1295. 统计位数为偶数的数字
    public int findNumbers(int[] nums) {
        int sum = 0;
        for (int n : nums){
            if(String.valueOf(n).length()%2==0){
                sum++;
            }
        }
        return sum;
    }

    // 1313. 解压缩编码列表
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<nums.length;i+=2){
            for(int j=0;j<nums[i];j++){
                list.add(nums[i+1]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 771. 宝石与石头
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<J.length();i++){
            map.put(J.charAt(i), 0);
        }
        for(int i=0;i<S.length();i++){
            if(map.get(S.charAt(i)) != null){
                count++;
            }
        }
        return count;
    }

    // 1108. IP 地址无效化
    public String defangIPaddr(String address) {
        address.replaceAll(".", "[.]");
        return address;
    }

    // 面试题58 - II. 左旋转字符串
    public String reverseLeftWords(String s, int n) {
        return s.substring(n,s.length()) + s.substring(0,n);
    }

    // 1290. 二进制链表转整数
    public int getDecimalValue(ListNode head) {
        int length = 1;
        ListNode prev = head;
        while (prev.next != null){
            prev = prev.next;
            length ++;
        }
        return getDecimalValue(head, length-1);
    }
    public int getDecimalValue(ListNode head, int length) {
        if(head.next == null){
            return 1*head.val;
        }else{
            return getDecimalValue(head.next, length-1) + (int)Math.pow(2, length)*head.val;
        }
    }
}
