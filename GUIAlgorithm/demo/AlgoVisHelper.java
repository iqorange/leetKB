package GUIAlgorithm.demo;

import java.awt.*;
import java.awt.geom.Ellipse2D;

// GUI画板工具类
public class AlgoVisHelper {
    private AlgoVisHelper(){

    }

    // 设置绘画线条的参数
    public static  void  setStrokeWidth(Graphics2D graphics2D, int w){
        int strokeWidth = w;
        // 让绘制的线条更加平滑
        graphics2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void setColor(Graphics2D graphics2D, Color color){
        graphics2D.setColor(color);
    }

    // 画一个圆
    public static void strokeCircle(Graphics2D graphics2D, int x, int y, int r){
        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        graphics2D.draw(circle);
    }

    // 画一个实心圆
    public static void fillCircle(Graphics2D graphics2D, int x, int y, int r){
        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        graphics2D.fill(circle);
    }

    // 封装Sleep动画
    public static void pause(int t){
        try {
            Thread.sleep(t);
        }catch (InterruptedException e){
            System.out.println("Error in sleeping");
        }
    }
}
