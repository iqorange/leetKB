package GUIAlgorithm.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

// 圆的画板（MVC视图层）
public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;
    private Circle[] circles;

    private class AlgoCanvas extends JPanel{
        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(hints);

            // 绘制
//            AlgoVisHelper.setStrokeWidth(graphics2D, 5);
//            AlgoVisHelper.setColor(graphics2D, Color.WHITE);
//            AlgoVisHelper.fillCircle(graphics2D, canvasWidth/2, canvasHeight/2, 200);
//            AlgoVisHelper.setColor(graphics2D, Color.GRAY);
//            AlgoVisHelper.strokeCircle(graphics2D, canvasWidth/2, canvasHeight/2, 200);
            AlgoVisHelper.setStrokeWidth(graphics2D, 1);
            AlgoVisHelper.setColor(graphics2D, Color.RED);
            for (Circle circle: circles){
                if (!circle.isFilled){
                    AlgoVisHelper.strokeCircle(graphics2D, circle.x, circle.y, circle.getR());
                }else{
                    AlgoVisHelper.fillCircle(graphics2D, circle.x, circle.y, circle.getR());
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        AlgoCanvas canvas = new AlgoCanvas();
        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        this.setContentPane(canvas);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public AlgoFrame(String title){
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void render(Circle[] circles){
        this.circles = circles;
        this.repaint();
    }

}
