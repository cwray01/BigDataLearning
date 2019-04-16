package ch6_lambda;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This program demonstrates the use of lambda expressions
 * @version 1.0
 * @author cwray
 */
public class LambdaTest {
    public static void main(String[] args)
    {
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());  //Lambda表达式
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000,event ->
            System.out.println("The time is" + new Date()));
        t.start();

        //keep program running utill user select "OK"
        JOptionPane.showMessageDialog(null,"Quit programe?");
        System.exit(0);


    }
}
