package ch14_blockingQueue;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;  //文件队列大小
    private static final int SEARCH_THREADS = 100;  //搜索的线程数
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);   //新建一个阻塞队列

    public static void main(String[] args)
    {
        try(Scanner in = new Scanner(System.in))
        {
            System.out.println("Enter base dir: ");
            String directory = in.nextLine();
            System.out.println("Enter keyword :");
            String keyword = in.nextLine();

            Runnable enumerator = (

            ) -> {
                try
                {
                    enumerate(new File(directory)); //将指定路径下的文件都put到了队列中，等待执行
                    queue.put(DUMMY);   //DUMMY是一个空文件
                }
                catch(InterruptedException e)
                {

                }
            };

            //Lambda表达式结束构造完成

            new Thread(enumerator).start(); //new一个线程，开始执行

            //阻塞，完成以后再进行search

            for(int i = 1; i <= SEARCH_THREADS; i++){   //执行线程不超过100个
                Runnable searcher = () -> {
                    try
                    {
                        boolean done = false;
                        while (!done)
                        {
                            File file = queue.take();   //从队列中取出文件
                            if (file == DUMMY)  //如果文件是空的
                            {
                                queue.put(file);  //将这个文件put到队列中
                                done = true;    //置为真，不做search
                            }
                            else search(file, keyword); //非空文件，执行search
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InterruptedException e)
                    {

                    }
                };
                new Thread(searcher).start();
            }
        }


    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     * @param directory the directory in which to start
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws InterruptedException
    {
        File[] files = directory.listFiles();
        for(File file : files)
        {
            if (file.isDirectory()) enumerate(file);    //如果传入的是文件夹就递归传入file类型
            else queue.put(file);   //将file作为入参put到队列中
        }
    }


    public static void search(File file, String keyword) throws IOException
    {
        try(Scanner in = new Scanner(file, "UTF-8"))
        {
            int lineNumber = 0;
            while(in.hasNextLine())
            {
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
            }

        }
    }
}
