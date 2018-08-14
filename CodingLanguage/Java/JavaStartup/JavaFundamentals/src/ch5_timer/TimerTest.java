package ch5_timer;

/**
 * @version
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
// to resolve conflict with java.util.Timer

public class TimerTest {
    public static void main(String[] args)
    {
        ActionListener listener = new TimePrinter();

        //construct a timer that calls the listener
        //one every 10 seconds
        //传递时间10000毫秒和一个对象listener，这个对象有定时播报功能
        Timer t = new Timer(10000, listener);
        //开始计时
        t.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
