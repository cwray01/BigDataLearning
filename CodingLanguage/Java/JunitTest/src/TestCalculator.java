import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestCalculator {

    private static Calculator cal;

    @BeforeClass
    public static void beforeClass(){ //静态方法
        cal = new Calculator();

    }

    @Test
    public void testPlus(){
//        Calculator cal = new Calculator();
        assertEquals(cal.plus(5, 5), 10);
    }

    @Test
    public void testSubtraction(){
//        Calculator cal = new Calculator();
        assertEquals(cal.subtraction(5, 5), 0);
    }

    @Ignore
    @Test
    public void testMultiplication(){
//        Calculator cal = new Calculator();
        assertTrue(cal.multiplication(5, 5) > 20);
    }

    @Test(expected = java.lang.ArithmeticException.class, timeout = 50)
    public void testDivision(){
//        Calculator cal = new Calculator();
        assertEquals(cal.division(8, 0), 4);
    }

}
