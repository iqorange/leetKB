package sol3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Exam {
    HaireFacory haireFacory;
    @Before
    public void before(){
        haireFacory = new HaireFacory();
    }

    @Test
    public void test(){
        TV t = haireFacory.createTV();
        t.display();
    }

    @After
    public void  after(){
        haireFacory = null;
    }
}
