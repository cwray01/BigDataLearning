package ch7_logging;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

/**
 * A modification of the image viewer program that logs various events
 */
public class LoggingImageViewer {
    public static void main(String[] args)
    {
        if (System.getProperty("java.util.logging.config.class") == null
                && System.getProperty("java.util.logging.config.file") == null)
        {
            try
            {
                Logger.getLogger("com.horstmann.corejava").setLevel(Level.ALL); //用getLogger的方法创建或获取记录器，Level.ALL开启所有级别的记录
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);  //%h日志记录文件模式变量，%h表示系统属性user.home的值
                Logger.getLogger("com.horstmann.corejava").addHandler(handler);
            }
            catch (IOException e)
            {
                Logger.getLogger("com.horstmann.corejava").log(Level.SEVERE,    //logger.log(Level.FINE,message);指定log级别
                        "Can't create log file handler", e);
            }
        }

        EventQueue.invokeLater(() ->
        {
            Handler windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);

            JFrame frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("com.hortmann.corejava").fine("Showing frame");
            frame.setVisible(true);
        });
    }

}

/**
 * The frame that shows the image
 */
class ImageViewerFrame extends JFrame
{
    private static final int DEFALUT_WIDTH = 300;
    private static final int DEFALUT_HEIGHT = 400;

    private JLabel label;
    private static Logger logger = Logger.getLogger("com.horstmann.corejava"); //初始化日志处理器

    public ImageViewerFrame()  //定义弹窗界面
    {
        logger.entering("ImageViewerFrame", "<init>");
        setSize(DEFALUT_WIDTH,DEFALUT_HEIGHT);

        //set up manu var
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menuBar.add(openItem);
        openItem.addActionListener(new FileOpenListener());  //监听Open按键，响应结果为new FileOpenListener

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()     //监听退出按键，最终响应结果为System.exit
        {
            public void actionPerformed(ActionEvent event)
            {
                logger.fine("Exiting.");
                System.exit(0);
            }
        });

        //use a label to display the images
        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame", "<init>");

    }

    private class FileOpenListener implements ActionListener  //为本程序定制的响应方法
    {
        public void actionPerformed(ActionEvent event)
        {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", event);

            //set up file chooser
            JFileChooser chooser = new JFileChooser();  //选择文件的窗口
            chooser.setCurrentDirectory(new File(".")); //选择文件窗口初始化的路径，"."表示在当前路径

            //accept all files ending with .gif
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() //文件过滤器
                                  {
                                      public boolean accept(File f)    //过滤所有.gif后缀
                                      {
                                          return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory(); //如果选中的是文件夹，则进入进入这一层文件夹
                                      }

                                      public String getDescription()
                                      {
                                          return "GIF Images";
                                      }
                                  }
            );

            // show file chooser dialog
            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            // if image file accepted, set it as icon of the label
            if (r==JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            } else logger.fine("File open dialog canceled");
            logger.exiting("ImageViewerFrame.FileOpenListener","actionPerformed");

        }
    }
}

/**
 * A handler for displaying log records in a window.
 *
 */
class WindowHandler extends StreamHandler   //自定义处理器，扩展自StreamHandler类，用于在窗口显示日志记录
{
    private JFrame frame; //定义窗口frame

    public WindowHandler()
    {
        frame = new JFrame();
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.setSize(200,200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream()
        {

            public void write(int b)
            {

            }// not called

            public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));

            }
        });
    }

    public void publish(LogRecord record)       //覆盖publish方法，以便在处理器获得每个记录之后刷新缓存区
    {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}