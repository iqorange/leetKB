package AdvancedDataStructure.HashTable;

import java.util.TreeMap;

// 哈希表
public class HashTable<K extends Comparable<K>, V> {
    // 定义素数表
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6451, 12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    // 设置上界
    private static final int upperTol = 10;
    // 设置下界
    private static final int lowerTol = 2;
    // 初始容量
    private static int capacityIndex = 0;
    // 红黑树数组解决哈希冲突
    private TreeMap<K, V>[] hashTable;
    // 最大素数
    private int M;
    // 哈希表的元素数
    private int size;

    public HashTable(){
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];
        for (int i=0;i<M;i++){
            hashTable[i] = new TreeMap<>();
        }
    }

    private int hash(K key){
        // 通过位运算取hashCode的绝对值
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)){
            map.put(key, value);
        }else{
            map.put(key, value);
            size++;
            // 判断是否需要扩容(避免浮点)，同时判断边界
            if (size >= upperTol * M && capacityIndex+1 < capacity.length){
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)){
            ret = map.remove(key);
            size--;
            // 判断是否需要缩容
            if (size < lowerTol * M && capacityIndex-1 >=0){
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, value);
    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }

    private void resize(int newM){
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i=0;i<newM;i++){
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        // 维护一下M
        this.M = newM;
        for (int i=0;i<oldM;i++){
            TreeMap<K, V> map = hashTable[i];
            for (K key:map.keySet()){
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashTable = newHashTable;
    }
}
