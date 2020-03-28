package SET;

import BST.BSTBesic;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BSTBesic<E> bst;

    public BSTSet(){
        bst = new BSTBesic<>();
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }
}
