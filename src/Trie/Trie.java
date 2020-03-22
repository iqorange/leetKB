package Trie;

// 引入Map接口和红黑树Map实现类
import java.util.Map;
import java.util.TreeMap;

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
    public void add(String word){
        Node cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)){
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
}
