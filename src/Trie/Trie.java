package Trie;

// 引入Map接口和红黑树Map实现类
import java.util.Map;
import java.util.TreeMap;

// 208. 实现 Trie (前缀树)
// 字典树多用于如通讯录这样的字符串存储结构，复杂度只与字符串长度有关
// 字典树Trie-使用TreeMap辅助实现
public class Trie {
    // 创建私有节点类
    private class Node{
        // 是否是单词节点
        public boolean isWord;
        // 从字符到node的映射
        public Map<Character, Node> next;

        // 接收单词构造Node
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        // 默认传入flase
        public Node(){
            this(false);
        }
    }

    // 创建私有根节点
    private Node root;
    // Trie中的单词数量
    private int size;

    // 初始化Trie
    public Trie(){
        root = new Node();
        size = 0;
    }

    // 获取单词数量
    public int getSize(){
        return size;
    }

    // 添加字符串单词，拆分字符到Trie中
    /** Initialize your data structure here. */
    public void insert(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 判断一下单词是否添加过
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    // 查询单词是否存在
    /** Returns if the word is in the trie. */
    public boolean search(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 前缀搜索
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix){
        Node cur = root;
        for (int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
