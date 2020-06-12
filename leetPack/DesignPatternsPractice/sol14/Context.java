package leetPack.DesignPatternsPractice.sol14;

public class Context {
    private Strategy strategy;
    private EncryptType type;

    public Context(EncryptType type) {
        this.type = type;
        if (type == EncryptType.Caesar){
            strategy = new Encrypt1();
        }else{
            strategy = new Encrypt2();
        }
    }

    public void algorithm(){
        strategy.algorithm();
    }
}
