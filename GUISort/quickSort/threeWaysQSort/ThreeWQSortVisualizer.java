package GUISort.quickSort.threeWaysQSort;

import java.awt.*;

// 双路快速排序，Java底层sort模拟
public class ThreeWQSortVisualizer {

    private static int DELAY = 40;

    private ThreeWQuickSortData data;
    private ThreeWQSortFrame frame;

    public ThreeWQSortVisualizer(int sceneWidth, int sceneHeight, int N, ThreeWQuickSortData.Type dataType){

        // 初始化数据
        data = new ThreeWQuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new ThreeWQSortFrame("Quick Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public ThreeWQSortVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, ThreeWQuickSortData.Type.Default);
    }

    public void run(){

        setData(-1, -1, -1, -1, -1, -1);

        quickSort3Ways(0, data.N()-1);

        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort3Ways(int l, int r){
        if( l > r ) {
            return;
        }

        if( l == r ){
            setData(l, r, l, -1, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1, -1);

        // 区间有多个元素的时候

        // 随机化处理
        // 在r和l之间随机取一个点
        int p = (int)(Math.random()*(r-l+1)) + l;
        setData(l, r, -1, p, -1, -1);
        data.swap(l, p);

        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        // 三路快排Partition
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        setData(l, r, -1, l, lt, gt);
        while (i<gt){
            if (data.get(i)<v){
                data.swap(i, lt+1);
                i++;
                lt++;
            }else if (data.get(i)>v){
                data.swap(i, gt-1);
                gt--;
            }else{
                i++;
            }
            setData(l, r, -1, l, i, gt);
        }

        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);

        quickSort3Ways(l, lt-1 );
        quickSort3Ways(gt, r);
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while (i < data.N() && data.get(i)==data.get(fixedPivot)){
                data.fixedPivots[i] = true;
                i++;
            }
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        ThreeWQSortVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

//        ThreeWQSortVisualizer vis = new ThreeWQSortVisualizer(sceneWidth, sceneHeight, N, ThreeWQuickSortData.Type.Default);
//        ThreeWQSortVisualizer vis = new ThreeWQSortVisualizer(sceneWidth, sceneHeight, N, ThreeWQuickSortData.Type.NearlyOrdered);
        ThreeWQSortVisualizer vis = new ThreeWQSortVisualizer(sceneWidth, sceneHeight, N, ThreeWQuickSortData.Type.Identical);
    }
}