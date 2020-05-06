package sol2;

public abstract class ShapeObj {
    private Type shape;

    public ShapeObj(Type shape) {
        this.shape = shape;
    }

    public Type getShape() {
        return shape;
    }

    public void paint(){
        System.out.println("形状创建了");
    }

    public void erase(){
        System.out.println("形状抹掉了");
    }
}
