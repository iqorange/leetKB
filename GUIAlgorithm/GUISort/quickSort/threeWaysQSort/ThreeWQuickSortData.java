package GUIAlgorithm.GUISort.quickSort.threeWaysQSort;

import java.util.Arrays;

// 快速排序
public class ThreeWQuickSortData {

    // 使用枚举类加速快速排序性能
    public enum Type{
        // 默认情况
        Default,
        // 近乎有序的情况
        NearlyOrdered,
        // 几乎一致的情况
        Identical
    }

    private int[] numbers;
    // 递归处理每个区间
    public int l, r;
    // 标记标定好的元素
    public boolean[] fixedPivots;
    // 当前处理的标定点
    public int curPivot;
    // 当前正在处理的元素
    public int curL, curR;

    public ThreeWQuickSortData(int N, int randomBound, Type dataType){
        // dataType是Default的情况
        numbers = new int[N];
        fixedPivots = new boolean[N];

        int lBound = 1;
        int rBound = randomBound;
        // 这里测试极端情况，完全相等的测试用例
        if (dataType == Type.Identical){
            int avgNumber = (lBound + rBound)/2;
            lBound = avgNumber;
            rBound = avgNumber;
        }

        for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*(rBound-lBound+1)) + lBound;
            fixedPivots[i] = false;
        }
        // 近乎有序的情况
        if (dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01*N);
            for (int i = 0;i<swapTime;i++){
                int a = (int)(Math.random()*N);
                int b = (int)(Math.random()*N);
                swap(a, b);
            }
        }
    }

    // 用户默认数据传入Default
    public ThreeWQuickSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
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