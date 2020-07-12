package leetPack.Recursive;

public class Pair<K extends Comparable<K>, V extends  Comparable<V>> {
    private final K left;
    private final V right;

    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }

    public K getLeft() {
        return left;
    }

    public V getRight() {
        return right;
    }

    public boolean equals(Pair<K, V> o) {
        return this.left.equals(o.left) && this.right.equals(o.right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return left.equals(pair.left) &&
                right.equals(pair.right);
    }
}