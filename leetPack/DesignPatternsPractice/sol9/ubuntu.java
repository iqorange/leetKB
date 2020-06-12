package leetPack.DesignPatternsPractice.sol9;

public class ubuntu extends Player{
    public ubuntu(Formatter formatter) {
        super(formatter);
    }

    @Override
    public void toPlay() {
        System.out.println(super.formatter.getPlayerFormat() + "文件开始在ubuntu上播放了～");
    }
}
