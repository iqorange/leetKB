package DesignPatternsPractice.sol7;

public class CatImpl implements Cat{
    @Override
    public void sayVariety() {
        System.out.println("我的品种是猫");
    }

    @Override
    public void catchMouse() {
        System.out.println("我会抓老鼠");
    }
}
