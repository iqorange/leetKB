package GUIAlgorithm.GUISort.insertionSort;

import javax.swing.*;
import java.awt.*;

public class InsertSortFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public InsertSortFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public InsertSortFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private InsertionSortData data;
    public void render(InsertionSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth/data.N();
            for (int i=0;i<data.N();i++){
                if (i<data.orderedIndex){
                    InsertSortVisHelper.setColor(g2d, InsertSortVisHelper.Red);
                }else{
                    InsertSortVisHelper.setColor(g2d, InsertSortVisHelper.Grey);
                }
                if (i == data.currentIndex){
                    InsertSortVisHelper.setColor(g2d, InsertSortVisHelper.LightBlue);
                }
                InsertSortVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


