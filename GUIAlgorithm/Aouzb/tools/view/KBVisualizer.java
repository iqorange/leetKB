package GUIAlgorithm.Aouzb.tools.view;

import GUIAlgorithm.Aouzb.tools.network.WSSConnection;
import GUIAlgorithm.Aouzb.tools.network.WebSocketConnector;
import GUIAlgorithm.Aouzb.tools.sound.MusicPlayer;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class KBVisualizer {
    private static int DELAY = 60;
    private KBData data;        // 数据
    private KBFrame frame;    // 视图
    private static final String URL = "wss://localhost:8080/wss";
    private WSSConnection wss;
    private Queue<Integer> drumScore;

    public KBVisualizer(int sceneWidth, int sceneHeight, Queue<Integer> drumScore){

        // 初始化数据
        data = new KBData(sceneWidth/KBData.blockSize, sceneHeight/KBData.blockSize, GameStatus.FrontCover);
        this.drumScore = drumScore;
//        wss = new WebSocketConnector(URL, data);


        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new KBFrame("AouZB", sceneWidth, sceneHeight);
            frame.addKeyListener(new KBKeyListener());
//            new Thread(() -> {
//                connWSS(wss);
//            });
//            MusicPlayer player = new MusicPlayer("./src/resources/music.wav");
//            player.start(false);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        while (true){
            if (data.getGameStatus() == GameStatus.InGame){
//            frame.stopMusic();
                frame.AudioLoader("./src/resources/music.wav");
                frame.playMusic();
                while (!drumScore.isEmpty()){
                    setData(drumScore.remove());
                }
                break;
            }else{
                frame.render(data);
                KBVisHelper.pause(618);
            }
        }
        // 结算分数

    }

    private void setData(int DELAY){
        frame.render(data);
        data.moveRandDistance();
        KBVisHelper.pause(DELAY);
    }

    private void connWSS(WSSConnection wss){
        wss.StartLink();
    }

    private class KBKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && data.getGameStatus() == GameStatus.FrontCover){
                data.setGameStatus(GameStatus.InGame);
            }else if (data.getGameStatus() == GameStatus.InGame){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:
                        data.white = Distance.JumpUp;
                        break;
                    case KeyEvent.VK_S:
                        data.white = Distance.JumpDown;
                        break;
                    case KeyEvent.VK_A:
                        data.white = Distance.JumpLeft;
                        break;
                    case KeyEvent.VK_D:
                        data.white = Distance.JumpRight;
                        break;
                    case KeyEvent.VK_UP:
                        data.black = Distance.JumpUp;
                        break;
                    case KeyEvent.VK_DOWN:
                        data.black = Distance.JumpDown;
                        break;
                    case KeyEvent.VK_LEFT:
                        data.black = Distance.JumpLeft;
                        break;
                    case KeyEvent.VK_RIGHT:
                        data.black = Distance.JumpRight;
                }
            }
        }
    }
}
