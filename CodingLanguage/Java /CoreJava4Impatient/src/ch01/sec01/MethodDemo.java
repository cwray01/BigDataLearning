package ch01.sec01;
import java.util.Random;

public class MethodDemo {
    public static void main(String[] args){
        Random generator = new Random();
        System.out.println(generator.nextInt());
        System.out.println(generator.nextInt());
        String location = "Java";
        String greeting = "Hello " + location;
        int age = 42;
        String output = age + "years";
        System.out.println(greeting);
        System.out.println(output);

    }
}
