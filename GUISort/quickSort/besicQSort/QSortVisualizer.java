package GUISort.quickSort.besicQSort;

import java.awt.*;

public class QSortVisualizer {

    private static int DELAY = 40;

    private QuickSortData data;
    private QSortFrame frame;

    public QSortVisualizer(int sceneWidth, int sceneHeight, int N, QuickSortData.Type dataType){

        // 初始化数据
        data = new QuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new QSortFrame("Quick Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public QSortVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }

    public void run(){

        setData(-1, -1, -1, -1, -1);

        quickSort(0, data.N()-1);

        setData(-1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r){
        if( l > r ) {
            return;
        }

        if( l == r ){
            setData(l, r, l, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1);

        // 区间有多个元素的时候
        int p = partition(l, r);
        quickSort(l, p-1 );
        quickSort(p+1, r);
    }

    private int partition(int l, int r){
        // 随机化处理
        // 在r和l之间随机取一个点
        int p = (int)(Math.random()*(r-l+1)) + l;
        setData(l, r, -1, p, -1);
        data.swap(l, p);

        int v = data.get(l);
        setData(l, r, -1, l, -1);

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ ){
            setData(l, r, -1, l, i);
            if( data.get(i) < v ){
                j ++;
                data.swap(j, i);
                setData(l, r, -1, l, i);
            }
        }

        data.swap(l, j);
        setData(l, r, j, -1, -1);

        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1) {
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curElement = curElement;

        frame.render(data);
        QSortVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        QSortVisualizer vis = new QSortVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.Identical);

    }
}