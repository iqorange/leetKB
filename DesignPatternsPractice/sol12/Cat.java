package DesignPatternsPractice.sol12;

public class Cat extends Subject {
    @Override
    public void notify(String message) {
        System.out.println("猫：" + message);
        for (Observer o: observers){
            o.update();
        }
    }
}
