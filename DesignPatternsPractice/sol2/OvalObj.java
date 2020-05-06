package sol2;

public class OvalObj extends ShapeObj{
    public OvalObj(Type shape) {
        super(shape);
    }

    @Override
    public void paint(){
        System.out.println("椭圆形创建了");
    }

    @Override
    public void erase(){
        System.out.println("椭圆形抹掉了");
    }
}
