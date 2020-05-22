package DesignPatternsPractice.sol13;

public class StockSystem extends Subject {
    @Override
    public void notify(String message) {
        System.out.println("----- 股票系统：" + message + " -----");
        for (Observer o: observers){
            o.update();
        }
    }
}
