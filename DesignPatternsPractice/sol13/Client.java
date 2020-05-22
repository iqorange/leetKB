package DesignPatternsPractice.sol13;

public class Client {
    public static void main(String[] args) {
        Subject mySystem = new StockSystem();
        Observer s1 = new StockInvestor("股民1");
        Observer s2 = new StockInvestor("股民2");
        Observer s3 = new StockInvestor("股民3");
        mySystem.attach(s1);
        mySystem.attach(s2);
        mySystem.attach(s3);
        mySystem.notify("监测到某支股票的价格变化幅度达到5%");
    }
}
