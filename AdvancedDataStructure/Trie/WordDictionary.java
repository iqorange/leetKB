package AdvancedDataStructure.Trie;

import java.util.HashMap;
import java.util.Map;

// 211. 添加与搜索单词 - 数据结构设计
class WordDictionary {
    // 创建私有节点类
    private class Node{
        // 是否是单词节点
        public boolean isWord;
        // 从字符到node的映射
        public Map<Character, Node> next;

        // 接收单词构造Node
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new HashMap<>();
        }

        // 默认传入flase
        public Node(){
            this(false);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index){
        if (index == word.length()){
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.'){
            if (node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c), word, index+1);
        }else{
            for (char nextChar: node.next.keySet()){
                if (match(node.next.get(nextChar), word, index+1)){
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */