package DesignPatternsPractice.sol2;

public class TriangleObj extends ShapeObj {
    public TriangleObj(Type shape) {
        super(shape);
    }

    @Override
    public void paint(){
        System.out.println("三角形创建了");
    }

    @Override
    public void erase(){
        System.out.println("三角形抹掉了");
    }
}
