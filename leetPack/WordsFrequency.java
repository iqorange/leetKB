package leetPack;

import java.util.HashMap;

public class WordsFrequency {

    private HashMap<String, Integer> map;
    public WordsFrequency(String[] book) {
        map = new HashMap<>();
        for (String b: book){
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
    }

    public int get(String word) {
        return map.getOrDefault(word, 0);
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */