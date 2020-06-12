package leetPack.DesignPatternsPractice.sol6;

public class PoliceCarAdaoter extends CarController {
    private PoliceSound sound;
    private PoliceLamp lamp;
    private XMLUtil xmlUtil;

    public PoliceCarAdaoter(){
        sound = new PoliceSound();
        lamp = new PoliceLamp();
        XMLUtil xmlUtil = new XMLUtil();
    }

    @Override
    public void phonate(){
        sound.alarmSound();
    }

    @Override
    public void twinkle(){
        lamp.alarmLamp();
    }
}
