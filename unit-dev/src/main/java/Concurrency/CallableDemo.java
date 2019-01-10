package Concurrency;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CallableDemo {
    //读取文件，并发统计某个单词
    public static long occurrences(String word, Path path){
        try{
            String contents = new String(Files.readAllBytes(path),
                    StandardCharsets.UTF_8);
            return Pattern.compile("\\PL+")
                    .splitAsStream(contents)
                    .filter(Predicate.isEqual(word))
                    .count();
        } catch (IOException ex){
            return 0;
        }
    }

    public static Set<Path> descendants(Path p) throws IOException{
        try(Stream<Path> entries = Files.walk(p)){
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String word = "String";      //将搜索的字符设定为"String"
        Set<Path> paths = descendants(Paths.get("."));
        List<Callable<Long>> tasks = new ArrayList<>();
        /*
        对于每个文件，可以生成一个返回该文件统计结果的Callable<Integer>，然后将它们提交给executor
         */
        for (Path p : paths) tasks.add(
                () -> { return occurrences(word, p);});
        int processors = Runtime.getRuntime().availableProcessors(); //获取可用的处理器数目
        ExecutorService executor = Executors.newFixedThreadPool(processors); //产生数目固定的线程池，当提交任务时，进行排队，知道有可用的线程
        List<Future<Long>> results = executor.invokeAll(tasks); //任务需要等待多个子任务的完成结果，可使用invokeAll方法
        long total = 0;
        for (Future<Long> result : results) total += result.get(); //当所有任务完成时，将得到一组future，且这些future都已经完成工作了，接下来就可以做结果汇总
        System.out.println("Occurrences of String: " + total);

        String searchWord = "occurrences";

        List<Callable<Path>> searchTasks = new ArrayList<>();
        for (Path p : paths) searchTasks.add(
                () -> {if (occurrences(searchWord, p) > 0) return p; else throw new RuntimeException();}
        );
        Path found = executor.invokeAny(searchTasks); //invokeAny:只要提交的所有任务中的任何一个完成了并且没有抛出异常，它就返回Future值，其他任务被取消（只匹配一个就结束）
        System.out.println(found);
        executor.shutdown();



    }



}
