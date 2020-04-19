package GUISort.quickSort.twoWaysQSort;

import java.awt.*;

// 双路快速排序
public class TwoWQSortVisualizer {

    private static int DELAY = 40;

    private TwoWQuickSortData data;
    private TwoWQSortFrame frame;

    public TwoWQSortVisualizer(int sceneWidth, int sceneHeight, int N, TwoWQuickSortData.Type dataType){

        // 初始化数据
        data = new TwoWQuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new TwoWQSortFrame("Quick Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public TwoWQSortVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, TwoWQuickSortData.Type.Default);
    }

    public void run(){

        setData(-1, -1, -1, -1, -1, -1);

        quickSort2Ways(0, data.N()-1);

        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort2Ways(int l, int r){
        if( l > r ) {
            return;
        }

        if( l == r ){
            setData(l, r, l, -1, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1, -1);

        // 区间有多个元素的时候
        int p = partition(l, r);
        quickSort2Ways(l, p-1 );
        quickSort2Ways(p+1, r);
    }

    private int partition(int l, int r){
        // 随机化处理
        // 在r和l之间随机取一个点
        int p = (int)(Math.random()*(r-l+1)) + l;
        setData(l, r, -1, p, -1, -1);
        data.swap(l, p);

        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        // 双路排序
        int i = l+1, j = r;
        setData(l, r, -1, l, i, j);
        while (true){
            while (i<=r && data.get(i)<v){
                i++;
                setData(l, r, -1, l, i, j);
            }
            while (j>=l+1 && data.get(j)>v){
                j--;
                setData(l, r, -1, l, i, j);
            }
            if (i>j){
                break;
            }
            data.swap(i, j);
            i++;
            j--;
            setData(i, r, -1, l, i, j);
        }

        data.swap(l, j);
        setData(l, r, j, -1, -1, -1);

        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        TwoWQSortVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

//        TWQSortVisualizer vis = new TWQSortVisualizer(sceneWidth, sceneHeight, N, TWQuickSortData.Type.Default);
        TwoWQSortVisualizer vis = new TwoWQSortVisualizer(sceneWidth, sceneHeight, N, TwoWQuickSortData.Type.Identical);

    }
}