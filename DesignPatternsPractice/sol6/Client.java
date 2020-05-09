package DesignPatternsPractice.sol6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Client {
    CarController carController;
    @Before
    public void before(){
        carController = new PoliceCarAdaoter();
    }
    @Test
    public void test(){
        carController.phonate();
        carController.twinkle();
    }
    @After
    public void after(){
        carController = null;
    }
}
