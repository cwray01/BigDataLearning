package ch6_anonymousInnerClass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This program demonstrates anonymous inner classes.
 * @Version
 * @Author cwray
 */
public class AnonymousInnerClassTest {
    public static void main(String[] args)
    {
        TalkingClock clock = new TalkingClock();
        clock.start(1000,false);
        System.out.println("I haven't click Quit Button.");

        //keep program running until user click OK
        JOptionPane.showMessageDialog(null, "Quit?");
        System.out.println("We are finished!");
        System.exit(0);
        System.out.println("We did finished!");
    }
}

/**
 * A clock that prints the time in regular intervals.
 *
 */

class TalkingClock //局部类不能用public或private访问说明符进行声明
{
    public void start(int interval, boolean beep)
    {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("At the tone, the time is " + new Date());
                if (!beep) Toolkit.getDefaultToolkit().beep();
            }
        };
            Timer t = new Timer(interval, listener);        //listener是一个对象，
            t.start();
    }

}

