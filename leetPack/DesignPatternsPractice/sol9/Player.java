package leetPack.DesignPatternsPractice.sol9;

public abstract class Player {
    protected Formatter formatter;

    public Player(Formatter formatter){
        this.formatter = formatter;
    }

    public abstract void toPlay();
}
