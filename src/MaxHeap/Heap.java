package MaxHeap;

public interface Heap {
    int size();
    boolean isEmpty();
    int parent(int index);
    int leftChild(int index);
    int rightChile(int index);

}
