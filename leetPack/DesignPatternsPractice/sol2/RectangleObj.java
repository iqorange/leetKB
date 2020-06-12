package leetPack.DesignPatternsPractice.sol2;

public class RectangleObj extends ShapeObj{
    public RectangleObj(Type shape) {
        super(shape);
    }

    @Override
    public void paint(){
        System.out.println("长方形创建了");
    }

    @Override
    public void erase(){
        System.out.println("长方形抹掉了");
    }
}

