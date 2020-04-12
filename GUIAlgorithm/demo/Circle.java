package GUIAlgorithm.demo;

import java.awt.*;

// 圆（MVC数据层）
public class Circle {
    // 坐标位置
    public int x, y;
    // 半径
    private int r;
    // 速度
    public int vx,  vy;
    // 是否实心
    public boolean isFilled = false;

    public Circle(int x, int y, int r, int vx, int vy){
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    public void move(int minx, int miny, int maxx, int maxy){
        x += vx;
        y += vy;
        checkCollision(minx, miny, maxx, maxy);
    }

    // 边缘的碰撞检测
    private void checkCollision(int minx, int miny, int maxx, int maxy){
        if (x-r < minx){
            x = r;
            vx = -vx;
        }
        if (x+r >= maxx){
            x = maxx -r;
            vx = -vx;
        }
        if (y-r < miny){
            y = r;
            vy = -vy;
        }
        if (y+r >=maxy){
            y = maxy-r;
            vy = -vy;
        }
    }

    // 是否在圆内
    public boolean contain(Point p){
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) <= r * r;
    }
}
