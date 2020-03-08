package Lesson6;

import jdk.jfr.StackTrace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MassivTest {
    Massiv Massivtest;
    Massiv14 Massivtest1;
    Integer [] a = {1,2,3,4,5,6,7,8};
    Integer [] a1 = {5,6,7,8};
    Integer [] b = {1,4,3,4,5,6,7,8};
    Integer [] b1 = {5,6,7,8};
    Integer [] c = {4,2,3,4,5,4,7,8};
    Integer [] c1 = {7,8};

    @Before
    public void startTest() {
        Massivtest = new Massiv();
    }


    @Test
    public void testmassivout(){
        Assert.assertEquals(a1, Massivtest.OutMassiv(a));
    }

    @Test
    public void testmassivout1(){
        Assert.assertEquals(b1, Massivtest.OutMassiv(b));
    }

    @Test
    public void testmassivout2(){
        Assert.assertEquals(c1, Massivtest.OutMassiv(c));
    }

    @Before
    public void startTest1() {
        Massivtest1 = new Massiv14();
    }

    @Test
    public void testmassivtest(){
        Assert.assertEquals(true, Massivtest1.Massivout(a));
    }

    @Test
    public void testmassivtest1(){
        Assert.assertEquals(true, Massivtest1.Massivout(b));
    }

    @Test
    public void testmassivtest2(){
        Assert.assertEquals(false, Massivtest1.Massivout(c));
    }



}
