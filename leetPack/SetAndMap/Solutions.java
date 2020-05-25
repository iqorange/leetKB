package leetPack.SetAndMap;

import java.util.*;

public class Solutions {
    // 242. 有效的字母异位词
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i=0;i<s.length();i++){
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i=0;i<t.length();i++){
            map2.put(t.charAt(i), map2.getOrDefault(t.charAt(i), 0) + 1);
        }
        return map1.equals(map2);
    }
    // 解法2
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        for (int i=0;i<str1.length;i++){
            if (str1[i] != str2[i]){
                return false;
            }
        }
        return true;
    }

    // 290. 单词规律
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length){
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i=0;i<pattern.length();i++){
            if (map.containsKey(pattern.charAt(i))){
                if (!map.get(pattern.charAt(i)).equals(words[i])){
                    return false;
                }
            }else{
                map.put(pattern.charAt(i), words[i]);
                set.add(words[i]);
            }
        }
        return map.size() == set.size();
    }

    // 205. 同构字符串
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i=0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) return false;
            }else{
                map.put(s.charAt(i), t.charAt(i));
                set.add(t.charAt(i));
            }
        }
        return map.size() == set.size();
    }

    // 451. 根据字符出现频率排序
    public String frequencySort(String s) {
        if (s.length()<=2) return s;
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i=0;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        queue.addAll(map.entrySet());
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()){
            Map.Entry<Character, Integer> entry = queue.remove();
            res.append(String.valueOf(entry.getKey()).repeat(Math.max(0, entry.getValue())));
        }
        return res.toString();
    }
}
