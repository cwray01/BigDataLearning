package ch10.sec1;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CallableDemo {
    public static long occurrences(String word, Path path){ //统计位于path的文件中的word数量，返回的long数目
        try{
            String contents = new String(Files.readAllBytes(path),
                    StandardCharsets.UTF_8);    //以UTF格式读入文件
            return Pattern.compile("\\PL+") //正则表达式匹配字符\\PL,目测是空格
                    .splitAsStream(contents) //以流的形式分割文件内容
                    .filter(Predicate.isEqual(word))    //过滤word字符
                    .count();   //统计word个数
        } catch(IOException ex){
            return 0;
        }
    }

    public static Set<Path> descendants(Path p) throws IOException{
        try (Stream<Path> entries = Files.walk(p)){ //work的用途？？ //返回路径，不带文件后缀
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException{
        String word = "String"; //用来统计所有的String
        Set<Path> paths = descendants(Paths.get("."));  //当前路径
        List<Callable<Long>> tasks = new ArrayList<>();
        for (Path p : paths) tasks.add(
                () -> {
                    return occurrences(word, p); });
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);    //为processors获取线程池，根据本机处理器核数获取
        List<Future<Long>> results = executor.invokeAll(tasks);
        long total = 0;
        for(Future<Long> result : results) total += result.get();   //遍历结果集，将所有结果累加获取总数
        System.out.println("Occurrences of String: " + total);  //打印结果

        String searchWord = "occurrences";  //设定检索字符串为occurrences,只要出现一次就返回
        List<Callable<Path>> searchTasks = new ArrayList<>();
        for (Path p : paths) searchTasks.add( //读取path中的每一个Path对对象，将执行Lambda表达式后的结果存入需要合并的Callable<Path>列表
                () -> { if ( occurrences(searchWord, p) > 0) return p; else throw new RuntimeException(); }); //如果符合判断条件，则返回路径
        Path found = executor.invokeAny(searchTasks);
        System.out.println(found);
        executor.shutdown();

    }
}
