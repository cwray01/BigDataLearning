package ch10.sec06;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QueueDemo {
    public static Queue queue = new Queue();
    public static Set<Object> set = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newCachedThreadPool(); //新建一个执行器对象
        for ( int i = 1; i <= 100; i++){  //循环100次
            int taskId = i;
            executor.execute(() -> {
                for(int k=1; k <= 1000; k++)    //第一个执行器，在队列中添加1000个整数，数值与taskID有关
                    queue.add(taskId * 1000 + k);
            });
            executor.execute(() -> {    //第二个执行器，在集合中添加object, object是从队列中取的
                for(int k=1; k <= 1000; k++){
                    try{
                        set.add(queue.take());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Final size: " + set.size());
    }
}
