package DesignPatternsPractice.sol9;

public class Client {
    public static void main(String[] args) {
        Player player = new MacOS(new MOV());
        player.toPlay();
        Player player1 = new Centos(new MP4());
        player1.toPlay();
    }
}
