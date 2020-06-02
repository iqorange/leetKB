package Graph.MineSweeper.mainGame;

import javax.swing.*;
import java.awt.*;

public class MineSweeperFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public MineSweeperFrame(String title, int canvasWidth, int canvasHeight){

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

    public MineSweeperFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private MineSweeperData data;
    public void render(MineSweeperData data){
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
            int width = canvasWidth / data.getM();
            int height = canvasHeight / data.getN();
            for (int i=0;i<data.getN();i++){
                for (int j=0;j<data.getM();j++){
                    if (data.isMine(i, j)){
                        MineSweeperVisHelper.putImage(g2d, j*width, i*height, MineSweeperData.mineImageURL);
                    }else{
                        MineSweeperVisHelper.putImage(g2d, j*width, i*height, MineSweeperData.blockImageURL);
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


