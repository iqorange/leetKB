package GUISort.insertionSort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class InsertSortVisualizer {

    private static final int DELAY = 40;
    private InsertionSortData data;
    private InsertSortFrame frame;    // 视图

    public InsertSortVisualizer(int sceneWidth, int sceneHeight, int N, InsertionSortData.Type dataType){

        // 初始化数据
        data = new InsertionSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new InsertSortFrame("InsertionSort", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public InsertSortVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, InsertionSortData.Type.Default);
    }

    // 动画逻辑
    private void run(){
        setData(0, -1);
        for (int i=0;i<data.N();i++){
            setData(i, i);
            for (int j=i;j>0 && data.get(j)<data.get(j-1);j--){
                data.swap(j, j-1);
                setData(i+1, j-1);
            }
        }
        setData(data.N(), -1);
    }

    private void setData(int orderIndex, int currentIndex){
        data.orderedIndex = orderIndex;
        data.currentIndex = currentIndex;
        frame.render(data);
        InsertSortVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        InsertSortVisualizer visualizer = new InsertSortVisualizer(sceneWidth, sceneHeight, N, InsertionSortData.Type.NearlyOrdered);
    }
}
