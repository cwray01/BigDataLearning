package ch14_forkJoin;

import java.util.concurrent.*;
import java.util.function.*;

/**
 * This program demonstrates he fork-join framework.
 */
public class ForkJoinTest {
    public static void main(String[] args)
    {
        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];
        for(int i=0; i<SIZE; i++) numbers[i] = Math.random();
        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool(); //新建一个ForkJoin线程池
        pool.invoke(counter);   //ForkJoin 的invokeAll方法会接收指定大小的tasks, 并阻塞到所有的tasks都完成
        System.out.println(counter.join()); //join方法返回汇总结果
    }

}

class Counter extends RecursiveTask<Integer>
{
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter)
    {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    protected Integer compute()
    {
        if (to - from < THRESHOLD)
        {
            int count = 0;
            for(int i = from; i < to; i++)
            {
                if (filter.test(values[i])) count++;
            }
            return count;
        }
        else
        {
            int mid = (from + to)/2;
            Counter first = new Counter(values, from, mid ,filter);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();    //把first的结果和second的结果汇总起来再返回
        }
    }
}