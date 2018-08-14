import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;


public class wordCount {
    //Create a Java Spark Context
    SparkConf conf = new SparkConf().setMaster("local").setAppName("My App");
    JavaSparkContext sc = new JavaSparkContext(conf);
    //Load our input data
    JavaRDD<String> input = sc.textFIle(inputFile);
    //Split up into words.
    JavaRDD<String> words = input.flatMap(
            new FlatMapFunction<String,String>(){
                public Iterable<String> call(String x){
                    return Arrays.asList(x.split(""));
                }
            }
    );

    //Transform into pairs and count.
    JavaPairRDD<String, Integer> counts = words.mapToPair(
            new PairFunction<String, String, Integer>(){
                public Tuple2<String, integer> call(String x){
                    return new Tuple2(x,1);
                }
            }
    )
}
