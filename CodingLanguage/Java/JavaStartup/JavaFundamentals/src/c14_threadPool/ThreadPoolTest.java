package c14_threadPool;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws Exception
    {
        try(Scanner in = new Scanner(System.in))
        {
            System.out.println("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
            String directory = in.nextLine();
            System.out.println("Enter keyword(e.g. volatile): ");
            String keyword = in.nextLine();

            ExecutorService pool = Executors.newCachedThreadPool();

            MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
            Future<Integer> result = pool.submit(counter); //提交后开始并发执行

            try
            {
                System.out.println(result.get() + " matching files.");
            }
            catch(ExecutionException e)
            {
                e.printStackTrace();
            }
            catch(InterruptedException e)
            {

            }

            pool.shutdown();

            int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
            System.out.println("largest pool size=" + largestPoolSize);

        }
    }


}

/**
 * This task counts the files in a directory
 * and its subdirectories that contain a given keyword.
 * 任务是可以重复执行的，以下就是需要重复执行的动作
 */
class MatchCounter implements Callable<Integer> // Callable 接口是一个参数化的类型，只有一个call方法
{
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    /**
     * Counstructs a MatchCounter.
     * @param directory the directory in which to start the search
     * @param keyword the keyword to look for
     * @param pool the thread pool for submitting subtasks 用于提交子任务的线程池
     */
    public MatchCounter(File directory, String keyword, ExecutorService pool)
    {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }


    //MatchCounter implements Callable<Integer>
    // java.util.concurrent.Callable<V<
    // V call() runs a task that yields a result
    public Integer call()
    {
        int count = 0;
        try
        {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();  //执行后得出的结果集
            for(File file : files)
                if(file.isDirectory())
                {
                    MatchCounter counter = new MatchCounter(file, keyword, pool);
                    Future<Integer> result = pool.submit(counter); //result是Integer，由线程执行后得出，提交方式是将执行器放入线程并submit
                                                                    //此处没看懂，counter需要做的是什么？
                    results.add(result); //把结果累加
                }else
                {
                    if (search(file)) count++; //如果不是文件夹就引用search方法
                }

            for(Future<Integer> result : results)
                try
                {
                    count += result.get();  //get 方法获取执行结果
                }catch(ExecutionException e)
                {
                    e.printStackTrace();
                }
        }catch(InterruptedException e)
        {

        }
        return count;
    }

    public boolean search(File file)
    {
        try{
            try (Scanner in = new Scanner(file, "UTF-8"))
            {
                boolean found = false;
                while (!found && in.hasNextLine())
                {
                    String line = in.nextLine();
                    if(line.contains(keyword)) found = true;
                }
                return found;
            }
        }catch(IOException e)
        {
            return false;
        }
    }
}


