package ch13_properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

/**
 * A program to test propertis. The program remembers the frame position, size and title
 */
public class PropertiesTest {
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            PropertiesFrame frame = new PropertiesFrame();
            frame.setVisible(true);
        });
    }
}

class PropertiesFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;       //定义对话框宽度
    private static final int DEFAULT_HEIGHT = 200;      //定义对话框高度

    private File propertiesFile;                        //定义属性文件类
    private Properties settings;                        //定义配置类

    public PropertiesFrame()
    {
        //get position, size, title from properties

        String userDir = System.getProperty("user.home");           //定义用户主目录
        File propertiesDir = new File(userDir, "corejava");    //设定配置文件目录
        System.out.println("userDir: " + userDir);
        System.out.println("propertiesDir: " + propertiesDir.getPath());
        if (!propertiesDir.exists()) propertiesDir.mkdir();         //若路径不存在则新建
        propertiesFile = new File(propertiesDir, "program.properties");     //新建一个路径，指定为配置文件的名字和路径

        //配置默认值
        Properties defaultSettings = new Properties();
        defaultSettings.setProperty("left", "0");
        defaultSettings.setProperty("top", "0");
        defaultSettings.setProperty("width", "" + DEFAULT_WIDTH);
        defaultSettings.setProperty("height", "" + DEFAULT_HEIGHT);
        defaultSettings.setProperty("title", "");

        //默认值配置完成后，读取真正配置里面的值
        settings = new Properties(defaultSettings);

        if(propertiesFile.exists())
            try (InputStream in = new FileInputStream(propertiesFile))
            {
                settings.load(in);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        //从properties读取settings配置,并赋值给新建的变量
        int left = Integer.parseInt(settings.getProperty("left"));
        int top = Integer.parseInt(settings.getProperty("top"));
        int width = Integer.parseInt(settings.getProperty("width"));
        int height = Integer.parseInt(settings.getProperty("height"));
        setBounds(left, top, width, height);

        //if no title given ask user

        String title = settings.getProperty("title");
        if (title.equals(""))
            title = JOptionPane.showInputDialog("Please supply a frame title:");
        if (title == null) title = "";
        setTitle(title);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event)
            {
                settings.setProperty("left", "" + getX());
                settings.setProperty("top", "" + getY());
                settings.setProperty("width", "" + getWidth());
                settings.setProperty("height", "" + getHeight());
                settings.setProperty("title", "" + getTitle());
                try (OutputStream out = new FileOutputStream(propertiesFile))
                {
                    settings.store(out, "Program Properteis");
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
    }


}
