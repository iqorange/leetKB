package GUIAlgorithm.Aouzb.tools.view;

import GUIAlgorithm.Aouzb.tools.sound.MusicPlayer;

import java.awt.*;
import javax.swing.*;

public class KBFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;
    private MusicPlayer musicPlayer;

    public KBFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        KBCanvas canvas = new KBCanvas();
        setContentPane(canvas);

        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public KBFrame(String title){

        this(title, 1024, 768);
    }

    public void AudioLoader(String url){
        this.musicPlayer = new MusicPlayer(url);
    }

    public void playMusic(Boolean loop){
        musicPlayer.start(loop);
    }

    public void playMusic(){
        musicPlayer.start(false);
    }

    public void stopMusic(){
        musicPlayer.stop();
    }

    public void continueMusic(){
        musicPlayer.continues();
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private KBData data;
    public void render(KBData data){
        this.data = data;
        repaint();
    }

    private class KBCanvas extends JPanel{

        public KBCanvas(){
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
            int block = KBData.blockSize;
            int jumpBlock = KBData.jumpSize;
            if (data.getGameStatus() == GameStatus.InGame){
//                System.out.println("游戏进行中～");
                // 绘制游戏地毯
                for (int i=0;i<data.getCarpet().length;i++){
                    if (data.getCarpet()[i] == -1){
                        continue;
                    }
                    int w = i / data.getM();
                    int h = i % data.getM();
                    KBVisHelper.setColor(g2d, KBVisHelper.cColors[data.getCarpet()[i]]);
                    KBVisHelper.fillRectangle(g2d, w*block, h*block, block, block);
                }
                // 绘制用户位置
                for (int i=0;i<data.getUsers().length;i++){
                    int place = data.getUsers()[i];
                    int w = place / data.getM();
                    int h = place % data.getM();
                    KBVisHelper.setColor(g2d, KBVisHelper.colors[i]);
                    KBVisHelper.fillCircle(g2d, w*block+block/2, h*block+block/2, block/2);
                }
                // 绘制分数
                KBVisHelper.drawText(g2d, "Red   : " + data.whiteScore, 40, 15);
                KBVisHelper.drawText(g2d, "Black: " + data.blackScore, 40, 30);
            }else if (data.getGameStatus() == GameStatus.FrontCover){
//                AudioLoader("./src/resources/font.wav");
//                playMusic(true);
//                System.out.println("游戏封面");
                KBVisHelper.putImage(g2d, 0, 0, "./src/resources/aouzbp.png");
//                KBVisHelper.drawText(g2d, "玩家：0/2", 320, 420);
            }else if (data.getGameStatus() == GameStatus.Scores){
                System.out.println("游戏分数结算");
            }
        }

        private void randDraw(Graphics2D g2d){
            for (int i=1;i<data.getN()-1;i++){
                for (int j=1;j<data.getM()-1;j++){
                    double rand = Math.random();
                    if (rand<0.02 || rand>0.98){
                        int block = KBData.blockSize;
//                        KBVisHelper.fillCircle(g2d, i*block+block/2, j*block+block/2, block/2);
                        if (Math.random()<0.3){
                            KBVisHelper.setColor(g2d, KBVisHelper.colors[(int)(Math.random()*16)]);
                            KBVisHelper.fillCircle(g2d, i*block+block/2, j*block+block/2, block/2);
                        }else{
                            KBVisHelper.setColor(g2d, KBVisHelper.cColors[(int)(Math.random()*16)]);
                            KBVisHelper.fillRectangle(g2d, i*block, j*block, block, block);
                        }
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


