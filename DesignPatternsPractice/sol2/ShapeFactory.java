package sol2;

public class ShapeFactory {
    public ShapeFactory() {
    }

    public ShapeObj createShape(Type shape) throws UnsupportedShapeException{
        if (shape == Type.triangle){
            return new TriangleObj(shape);
        }
        if (shape == Type.oval){
            return new OvalObj(shape);
        }
        if (shape == Type.rectangle){
            return new RectangleObj(shape);
        }
        throw new UnsupportedShapeException("没有这个图形！");
    }
}
