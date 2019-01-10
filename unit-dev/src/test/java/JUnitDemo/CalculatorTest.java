package JUnitDemo;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import JUnitDemo.Calculator;


public class CalculatorTest {
    private static Calculator calculator; //初始化一个测试类

    @BeforeClass
    public static void BuildCalculator(){
        calculator = new Calculator();
    }

    @Test //标注testAdd()方法为一个测试方法
    public void testAdd(){
        Assert.assertEquals(8, calculator.add(3,5)); //Assert类提供断言方法，能够判断预期结果和程序输出的结果是否一致
        Assert.assertEquals(7, calculator.add(3,5));

    }
}
