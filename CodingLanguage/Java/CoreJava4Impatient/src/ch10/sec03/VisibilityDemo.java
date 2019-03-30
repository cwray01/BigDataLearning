package ch10.sec03;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
//    private static boolean done = false;
    private static volatile boolean done = false;
    public static void main(String[] args) throws InterruptedException {
        Runnable hellos = () -> {
            for ( int i = 1; i <= 1000; i++)
                System.out.println("Hello " + i);
            done = true;
        };

        Runnable goodbye = () -> {
            int i= 1;
            while (!done) i++;
            System.out.println("Goodbye " + i);

        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(hellos);
        executor.execute(goodbye);
        TimeUnit.MILLISECONDS.sleep(10);

    }
}
