package BasicDataStructure.LinkedList.getPi;

import java.awt.*;

public class GPVisualizer {

    private static int DELAY = 40;
    private MonteCarloPiData data;
    private GPFrame frame;
    private int N;

    public GPVisualizer(int sceneWidth, int sceneHeight, int N){
        if (sceneWidth != sceneHeight){
            throw new IllegalArgumentException("This demo must be run in a square window!");
        }
        // 初始化数据
        this.N = N;
        Circle circle = new Circle(sceneWidth/2, sceneHeight/2, sceneWidth/2);
        data = new MonteCarloPiData(circle);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new GPFrame("GetPi", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        for (int i=0;i<N;i++){
            if (i%100 == 0){
                frame.render(data);
                GPVisHelper.pause(DELAY);
                System.out.println(data.estimatePi());
            }
            int x = (int)(Math.random()*frame.getCanvasWidth());
            int y = (int)(Math.random()*frame.getCanvasHeight());
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        // TODO: 根据需要设置其他参数，初始化visualizer
        GPVisualizer visualizer = new GPVisualizer(sceneWidth, sceneHeight, N);
    }
}
