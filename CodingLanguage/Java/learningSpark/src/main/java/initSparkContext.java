import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class initSparkContext {
    SparkConf conf = new SparkConf().setMaster("local").setAppName("My app");
    JavaSparkContext sc = new JavaSparkContext(conf);
}
