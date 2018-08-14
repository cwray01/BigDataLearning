import javax.swing.*;
import java.awt.event.*;

public class SimpleGui1B implements ActionListener{ 		//实现Actionlistener这个接口
	JButton button;
	
	public static void main( String[] args){
		SimpleGui1B gui = new SimpleGui1B();
		gui.go();
	}
	
	public void go() {
		JFrame frame = new JFrame();
		button = new JButton("click me");
		
		button.addActionListener(this); //  向按钮注册 
		
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {	//定义处理事件的方法
		button.setText("I've been clicked!");
	}


}
	