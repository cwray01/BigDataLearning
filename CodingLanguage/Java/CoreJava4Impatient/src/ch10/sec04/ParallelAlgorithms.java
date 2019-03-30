package ch10.sec04;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ParallelAlgorithms {
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

    public static void main(String[] args) throws  IOException{

        System.out.println(Paths.get(".").toString());
        String contents = new String(Files.readAllBytes(Paths.get("alice.txt")),
                StandardCharsets.UTF_8); //用UTF-8的格式读入alice.txt
        List<String> words = Arrays.asList(contents.split("\\PL+"));  //以非字母为分隔符
        long result = words.parallelStream().filter(s -> s.startsWith("A")).count();   //words是一个较大的字符串集合，想要找出其中有多少字符串是以字母A开始的
                                                                                        // parallelStream方法产生一个并行流，流被分解为段，在每段上进行过滤和技术，并合并结果
        System.out.println("Words starting with A: " + result);
        Path pathToRoot = Paths.get(".");

        try (Stream<Path> paths = Files.walk(pathToRoot)){
            long total = paths.parallel()
                    .mapToLong(p -> occurrences("String", p))
                    .sum();
            System.out.println("Occurrences of String " + total);
        }


        //并行数组操作
        //parallelSetAll 将数组分解为片，在片上并行工作， 最后合并结果
        int[] values = new int [1000000];
        Arrays.parallelSetAll(values, i -> i % 10);
        //Filles values with 0 1 2 3 4 5 6 7 8 9 0 1 2 ...

        System.out.println(Arrays.toString(Arrays.copyOf(values, 20)));
        //实际上填充100000组，但打印只打印20组
        //parallelSort方法可以对基本类型值或对象的数组进行排序
        String[] wordArray = words.toArray(new String[words.size()]);
        Arrays.parallelSort(wordArray, Comparator.comparing(String::length));
        System.out.println(Arrays.toString(Arrays.copyOfRange(wordArray, wordArray.length / 2, wordArray.length / 2 + 20)));

        Arrays.parallelSort(values, values.length /2, values.length);
        System.out.println(Arrays.toString(Arrays.copyOfRange(values, values.length / 2, values.length / 2 + 20)));
    }
}
