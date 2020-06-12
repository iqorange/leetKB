package leetPack.DesignPatternsPractice.sol13;

// 股民
public class StockInvestor implements Observer {
    private String name;

    public StockInvestor(String name){
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name + ": 收到了股票监测系统信息～");
    }
}
