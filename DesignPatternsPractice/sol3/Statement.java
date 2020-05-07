package DesignPatternsPractice.sol3;

public class Statement {
    private int[] state;
    // TODO 语句对象
    public Statement(){
        state = new int[20];
        for (int i=0;i<20;i++){
            state[i] = 0;
        }
    }
    public void state(){
        System.out.println("语句对象");
    }
}
