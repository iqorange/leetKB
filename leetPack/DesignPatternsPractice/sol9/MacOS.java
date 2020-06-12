package leetPack.DesignPatternsPractice.sol9;

public class MacOS extends Player{
    public MacOS(Formatter formatter) {
        super(formatter);
    }

    @Override
    public void toPlay() {
        System.out.println(super.formatter.getPlayerFormat() + "文件开始在macOS上播放了～");
    }
}
