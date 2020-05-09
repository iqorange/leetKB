package DesignPatternsPractice.sol9;

public class Centos extends Player{
    public Centos(Formatter formatter) {
        super(formatter);
    }

    @Override
    public void toPlay() {
        System.out.println(super.formatter.getPlayerFormat() + "文件开始在CentOS上播放了～");
    }
}
