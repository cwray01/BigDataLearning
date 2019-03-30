package ch10.sec03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceConditionDemo {
    public static volatile int count;   //count这个计数变量是改变可见的，这样多个线程操作这个变量的时候可以察觉count的变化

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newCachedThreadPool();     //分配线程池
        for(int i = 1; i<= 100; i++){   //启动100个线程
            int taskId = i; //线程号
            Runnable task = () -> { //定义每个线程需要运行的任务
                for (int k = 1; k <= 1000; k++)
                    count ++;   //计数
                System.out.println(taskId + ": " + count);
            };
            executor.execute(task); //执行任务
        }
        executor.shutdown(); //关闭线程
        executor.awaitTermination(10, TimeUnit.MINUTES);    //超时结束
        System.out.println("Final value: " + count); //理论上应该需要累加到100000，但是由于Race condition导致多个线程修改共享变量的时候，损坏了数据
    }
}
