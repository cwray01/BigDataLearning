import java.util.*;
import static java.lang.System.out;

class FullMoons {
	static int DAY_IM = 1000 * 60 * 60 * 24;
	
	public static void main(String [] args){
		
		Calendar c = Calendar.getInstance();		//Calendar是抽象类，要用静态的方法来定义
		c.set(2004, 0,7,15,40);						//设定时间为2004年1月7日15:40
		long day1 = c.getTimeInMillis();			//将目前时间转换为以millisecond表示
		
		for (int x = 0; x < 60; x++){
			day1 += (DAY_IM * 29.25);					
			c.setTimeInMillis(day1); 					//将c的时间加上一个周期
			out.println(String.format("full moon on %tc", c));//tc：完整的时间日期
		}
		
	}
	
}
