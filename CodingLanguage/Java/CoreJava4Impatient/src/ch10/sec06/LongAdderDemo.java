package ch10.sec06;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
    public static LongAdder count = new LongAdder();    //如果程序中可能存在高度竞争的时候，应该使用LongAdder来代替AtomicLong

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 1000; i++) {
            Runnable task = () -> {
                for (int k = 1; k <= 100000; k++)
                    count.increment();
            };
            executor.execute(task);
        }
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.MINUTES);
            System.out.println("Final value: " + count);

            LongAccumulator largest = new LongAccumulator(Long::max, 0);
            largest.accumulate(42L);
            long max = largest.get();
            System.out.println(max);

            ConcurrentHashMap<String, LongAdder> counts = new ConcurrentHashMap<>();    //ConcurrentHashMap是一个线程安全的哈希映射，不管多少线程同时在map上操作，内部结构都不会被破坏
            for (String key : "Row, row ,row a boat".split("\\PL+"))
                counts.computeIfAbsent(key, k -> new LongAdder()).increment();
            System.out.println(counts);
        }

}
