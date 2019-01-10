package JUnitDemo;
import java.util.Arrays;
import java.util.Collection;


import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit 参数化设置
 */

@RunWith(Parameterized.class) // RunWith注解来改变测试运行器
public class ParameterTest {
    private static Calculator calculator;

    @BeforeClass
    public static void setUpBeforeClass(){
        calculator = new Calculator();
    }

    private int expected;       //声明变量来存放预期值和结果值
    private int input1;
    private int input2;

    @Parameters
    public static Collection<Object[]> setParameters(){ //声明一个返回值为Collection的公共静态方法，并使用@Parameters来修饰
        Object[][] objs = {{8,3,5},{5,3,2}};
        return Arrays.asList(objs);
    }

    public ParameterTest(int expected, int input1, int input2){ //为测试类声明一个带有参数的公共构造方法，并在其中为之声明变量赋值
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Test
    public void testParameters(){
        Assert.assertEquals(expected, calculator.add(input1, input2));
    }

}
