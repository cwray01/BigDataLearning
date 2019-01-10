package JUnitDemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件
 */
@RunWith(Suite.class) //RunWith修改测试运行器
@Suite.SuiteClasses({DemoTest1.class,DemoTest2.class, DemoTest3.class}) //指定需要批量执行的测试类（数组形式）
public class SuiteTest {



}
