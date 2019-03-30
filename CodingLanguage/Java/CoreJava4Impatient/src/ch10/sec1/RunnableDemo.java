package ch10.sec1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class RunnableDemo {
    public static void main(String[] args){
        Runnable hellos = () -> {
            for(int i = 1; i <= 1000; i++ )
                System.out.println("Hello " + i);
        };
        Runnable goodbyes = () -> {
            for (int i = 1; i <= 1000; i++)
                System.out.println("Goodbye " + i);
        };

        ExecutorService executor = Executors.newCachedThreadPool(); //产生一个有很多短暂的任务或者任务会消耗很多时间等待的程序优化过的executor
        executor.execute(hellos);
        executor.execute(goodbyes);
        executor.shutdown(); //当线程池空闲一段时间时，executor会终止这些线程，然后程序就会中止


    }


}
