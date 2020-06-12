package GUIAlgorithm.GUISort.selectionSort;

import java.awt.*;
import javax.swing.*;

public class SSortFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public SSortFrame(String title, int canvasWidth, int canvasHeight){

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

    public SSortFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private SelectionSortData data;
    public void render(SelectionSortData data){
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
                if (i<data.orderedIndex){
                    SSortVisHelper.setColor(g2d, SSortVisHelper.Purple);
                }else{
                    SSortVisHelper.setColor(g2d, SSortVisHelper.Grey);
                }
                if (i == data.currentCompareIndex){
                    SSortVisHelper.setColor(g2d, SSortVisHelper.LightBlue);
                }
                if (i == data.currentMinIndex){
                    SSortVisHelper.setColor(g2d, SSortVisHelper.Indigo);
                }
                SSortVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}