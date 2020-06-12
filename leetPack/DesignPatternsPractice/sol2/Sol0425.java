package leetPack.DesignPatternsPractice.sol2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Sol0425 {
    ShapeFactory shapeFactory;
    ShapeObj myShap;
    @Before
    public void before(){
        shapeFactory = new ShapeFactory();
    }

    @Test
    public void test() throws UnsupportedShapeException{
        try {
            myShap = shapeFactory.createShape(Type.triangle);
        }catch (Exception e){
            throw new UnsupportedShapeException("没有这个图形～");
        }
        myShap.paint();
        myShap.erase();
    }

    @After
    public void after(){

    }
}
