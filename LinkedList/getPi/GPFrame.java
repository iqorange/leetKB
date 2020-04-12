package LinkedList.getPi;

import javax.swing.*;
import java.awt.*;

public class GPFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public GPFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        setResizable(false);
        pack();    // 在setResizable(false)后进行pack()，防止在Windows下系统修改frame的尺寸
                   // 具体参见：http://coding.imooc.com/learn/questiondetail/26420.html

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public GPFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private MonteCarloPiData data;
    public void render(MonteCarloPiData data){
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
            Circle circle = data.getCircle();
            GPVisHelper.setStrokeWidth(g2d, 3);
            GPVisHelper.setColor(g2d, GPVisHelper.Blue);
            GPVisHelper.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());
            for (int i=0;i<data.getPointsNumber();i++){
                Point p = data.getPoint(i);
                if (circle.contain(p)){
                    GPVisHelper.setColor(g2d, GPVisHelper.Red);
                }else{
                    GPVisHelper.setColor(g2d, GPVisHelper.Green);
                }
                GPVisHelper.fillCircle(g2d, p.x, p.y, 3);
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


