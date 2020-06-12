package GUIAlgorithm.GUISort.mergeSort;


public class MergeSortData {

    public int[] numbers;
    // 每次递归进去处理的区间
    public int l, r;
    // 每次逐渐merge索引
    public int mergeIndex;

    public MergeSortData(int N, int randomBound){

        numbers = new int[N];

        for( int i = 0 ; i < N ; i ++)
            numbers[i] = (int)(Math.random()*randomBound) + 1;
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
