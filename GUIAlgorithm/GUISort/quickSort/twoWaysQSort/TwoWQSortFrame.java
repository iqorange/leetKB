package GUIAlgorithm.GUISort.quickSort.twoWaysQSort;

import javax.swing.*;
import java.awt.*;

public class TwoWQSortFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public TwoWQSortFrame(String title, int canvasWidth, int canvasHeight){

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

    public TwoWQSortFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private TwoWQuickSortData data;
    public void render(TwoWQuickSortData data){
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
            for(int i = 0 ; i < data.N() ; i ++ ) {
                if ( i >= data.l && i <= data.r){
                    // 当前正在处理的区间
                    TwoWQSortVisHelper.setColor(g2d, TwoWQSortVisHelper.Green);
                }else{
                    // 不是当前的区间
                    TwoWQSortVisHelper.setColor(g2d, TwoWQSortVisHelper.Grey);
                }
                if( i == data.curPivot )
                    TwoWQSortVisHelper.setColor(g2d, TwoWQSortVisHelper.Indigo);
                if (i >= data.l+1 && i <= data.curL){
                    TwoWQSortVisHelper.setColor(g2d, TwoWQSortVisHelper.LightBlue);
                }
                if (i >= data.curR+1 && i <= data.r){
                    TwoWQSortVisHelper.setColor(g2d, TwoWQSortVisHelper.LightBlue);
                }
                if( data.fixedPivots[i] )
                    TwoWQSortVisHelper.setColor(g2d, TwoWQSortVisHelper.Red);

                TwoWQSortVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}