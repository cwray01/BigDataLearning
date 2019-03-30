package ch10.sec06;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock; //可重入锁

public class LockDemo {
    public static int count;
    public static Lock countLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newCachedThreadPool(); //用于合并计算结果
        for(int i = 1; i <= 100; i++){
            Runnable task = () -> {
                for (int k = 1; k <= 1000; k++){
                    countLock.lock();   //将countLock对象锁住，如果另一线程调用countLock,就会被阻塞
                    try{
                        count++;    //Critical section,必须完整地、没有中断地执行代码块

                    }finally{
                        countLock.unlock();
                    }
                }
            };
            executor.execute(task);
        }
        executor.shutdown();;
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final value: " + count);
    }
}
