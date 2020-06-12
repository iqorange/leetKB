package GUIAlgorithm.GUISort.mergeSort;

import java.awt.*;
import javax.swing.*;

public class MSortFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public MSortFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public MSortFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private MergeSortData data;
    public void render(MergeSortData data){
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
            //AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
            for(int i = 0 ; i < data.N() ; i ++ ) {
                if ( i >= data.l && i <= data.r)
                    MsortVisHelper.setColor(g2d, MsortVisHelper.Green);
                else
                    MsortVisHelper.setColor(g2d, MsortVisHelper.Grey);

                if( i >= data.l && i <= data.mergeIndex )
                    MsortVisHelper.setColor(g2d, MsortVisHelper.Red);

                MsortVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
