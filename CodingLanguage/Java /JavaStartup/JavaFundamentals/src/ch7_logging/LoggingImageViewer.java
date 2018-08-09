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

        EventQueue.invokeLater(() ->        //lambda表达式，事件队列,事件分派线程
        {   System.out.println("Now in EventQueue");
            Handler windowHandler = new WindowHandler();        //自定义定义一个窗口处理器
            windowHandler.setLevel(Level.ALL);                  //该窗口处理器记录的级别设为Level.ALL
            Logger.getLogger("com.horstmann.corejava").addHandler(windowHandler);   //将日志传送到自定义的窗口处理器

            JFrame frame = new ImageViewerFrame();              //初始化一个Frame
            frame.setTitle("LoggingImageViewer");               //Frame的Title
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户单击关闭按钮时的操作
//            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//用户单击关闭按钮时的操作

            Logger.getLogger("com.hortmann.corejava").info("Showing frame");
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
    private static Logger logger = Logger.getLogger("com.horstmann.corejava"); //选择一个日志记录器，并把日志记录器命名为与主应用程序包一样的名字
                                                                                //利用一些日志操作将下面的静态域添加到类中
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

            // show file chooser dialog 展示文件选择窗的对话框 Frame
            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            // if image file accepted, set it as icon of the label
            if (r==JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath(); //定义展示文件名为已选择文件的path
                logger.log(Level.FINE, "Reading file {0}", name); //定义该事件的日志级别，Fine，报文展示被选文件路径
                label.setIcon(new ImageIcon(name));
            } else logger.fine("File open dialog canceled");
            logger.exiting("ImageViewerFrame.FileOpenListener","actionPerformed"); //actionPerformed结束

        }
    }
}

/**
 * A handler for displaying log records in a window.
 *
 */
class WindowHandler extends StreamHandler   //自定义处理器，扩展自StreamHandler类，用于在窗口显示日志记录
{
    private JFrame frame; //声明窗口frame

    public WindowHandler()
    {
        frame = new JFrame();   //定义窗口
        final JTextArea output = new JTextArea();   //定义文字显示区
        output.setEditable(false);   //设置文字显示区为可编辑状态
        frame.setSize(400,400); //设置日志窗口大小
        frame.add(new JScrollPane(output)); //滚动窗格 scroll pane，参考12.3.5滚动窗格
        frame.setFocusableWindowState(true); //setFocusableWindowState打开以后可以选择日志中的文字，做复制操作
        frame.setVisible(true); //设置日志窗口可见开关
        setOutputStream(new OutputStream() //打印流
        {

            public void write(int b)
            {

            }// not called，如果只传入一个参数，那么就无响应

            public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));

            } //传入三个参数，输出
        });
    }

    public void publish(LogRecord record)       //覆盖publish方法，以便在处理器获得每个记录之后刷新缓存区
    {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}
