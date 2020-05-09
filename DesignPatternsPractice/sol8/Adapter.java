package DesignPatternsPractice.sol8;

public class Adapter implements Adap{
    Enc enc;

    public Adapter(Enc enc){
        this.enc = enc;
    }

    @Override
    public void toEncrypt() {
        enc.encrypt();
    }
}
