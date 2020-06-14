package GUIAlgorithm.Aouzb.game;

import GUIAlgorithm.Aouzb.tools.view.KBVisualizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Entrance {
    public static void main(String[] args) {
        System.out.println("Running!");
        int sceneWidth = 1300;
        int sceneHeight = 720;

        Queue<Integer> queue = new ArrayDeque<>();
        File file = new File("./src/resources/drumScore.txt");
//         JDK7特性，读取失败自动关闭Scanner资源
        try(Scanner scanner = new Scanner(file);) {
            while (scanner.hasNext()){
                queue.add(scanner.nextInt());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        KBVisualizer visualizer = new KBVisualizer(sceneWidth, sceneHeight, queue);
    }
}
