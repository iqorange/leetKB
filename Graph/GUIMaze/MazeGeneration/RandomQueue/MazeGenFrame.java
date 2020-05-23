package Graph.GUIMaze.MazeGeneration.RandomQueue;

import Graph.GUIMaze.MazeGeneration.MazeData;
import Graph.GUIMaze.MazeGeneration.MazeGenFrameVisHelper;

import javax.swing.*;
import java.awt.*;

public class MazeGenFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public MazeGenFrame(String title, int canvasWidth, int canvasHeight){

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

    public MazeGenFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private MazeData data;
    public void render(MazeData data){
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
            // 计算宽与高
            int w = canvasWidth/data.getM();
            int h = canvasHeight/data.getN();
            for (int i=0;i<data.getN();i++){
                for (int j=0;j<data.getM();j++){
                    if (data.inMist[i][j]){
                        MazeGenFrameVisHelper.setColor(g2d, MazeGenFrameVisHelper.Black);
                    }else if (data.maze[i][j] == MazeData.WALL){
                        MazeGenFrameVisHelper.setColor(g2d, MazeGenFrameVisHelper.LightBlue);
                    }else{
                        MazeGenFrameVisHelper.setColor(g2d, MazeGenFrameVisHelper.White);
                    }
                    MazeGenFrameVisHelper.fillRectangle(g2d, j*w, i*h, w, h);
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


