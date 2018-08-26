/**
 * This program just counts the number of lines containing ‘a’
 * and the number containing ‘b’ in the Spark README.
 * Note that you’ll need to replace YOUR_SPARK_HOME with the location
 * where Spark is installed.
 */

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;

public class SimpleApp {
    public static void main(String[] args){
        String logFile = "/Users/cwray/Documents/0.Learning/Spark/Preparation/spark-2.3.0-bin-hadoop2.7/README.md"; // Should be some file on my System
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter(s -> s.contains("a")).count();
        long numBs = logData.filter(s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        spark.stop();
    }
}


