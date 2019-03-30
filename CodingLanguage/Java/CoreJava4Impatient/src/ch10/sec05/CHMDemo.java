package ch10.sec05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CHMDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path path) {
        try{
            String contents = new String(Files.readAllBytes(path),
                    StandardCharsets.UTF_8);
            for (String word : contents.split("\\PL+")){
                map.merge(word, 1L, Long::sum); //merge方法的第一个参数用来表示key尚未存在时的初始值
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path p) throws IOException{
        try ( Stream<Path> entries = Files.walk(p)){
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException{
        int processors = Runtime.getRuntime().availableProcessors();    //获取本机线程数
        ExecutorService executor = Executors.newFixedThreadPool(processors);    //设定核数固定的线程池
        Path pathToRoot = Paths.get("."); //设定工作目录
        for (Path p : descendants(pathToRoot)){ //遍历子目录
            executor.execute(() -> process(p));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println(map);
    }
}
