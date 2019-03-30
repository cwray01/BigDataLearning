package ch10.sec06;

/**
 * Java 中所有的对象都有固有锁
 */
public class Counter {
    private int value;
    public synchronized  int increment(){   //通过使用Counter实例的固有锁，无须使用显式锁
        value++;
        return value;
    }
}
