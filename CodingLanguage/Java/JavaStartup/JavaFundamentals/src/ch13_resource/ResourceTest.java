package ch13_resource;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;


public class ResourceTest {
    public static void main(String[] args){
        //Lambda表达式
        EventQueue.invokeLater(() -> {
            JFrame frame = new ResourceTestFrame();                 //唤起JFrame，初始化ResourceTestFrame
            frame.setTitle("ResourceTest");                         //对话框标题
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //默认选项：退出键
            frame.setVisible(true);                                 //可见性开关
        });
    }
}

/**
 * A frame that loads image and text resources
 */
class ResourceTestFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public ResourceTestFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);                                 //设置大小
        URL aboutURL = getClass().getResource("about.gif");               //设定gif的路径，通过getResource方法获取
        Image img = new ImageIcon(aboutURL).getImage();                         //读取git路径，映射到对象img中
        System.out.println("Now where is URL?" + aboutURL.getPath());
        setIconImage(img);                                                      //设置对象

        JTextArea textArea = new JTextArea();                                   //新建一个文本对象
        InputStream stream = getClass().getResourceAsStream("about.txt"); //获取about.txt的路径信息，并作为流初始化到对象stream
        try (Scanner in = new Scanner(stream, "UTF-8"))             //读取about.txt的每一行信息
        {
            while (in.hasNext())
                textArea.append(in.nextLine() + "\n");
        }
        add(textArea);                                                          //在文本框中显示

    }
}
