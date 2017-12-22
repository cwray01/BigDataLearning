import java.util.*;

public class DotCom {
	//DotCom的实例变量：保存位置的Array,DotCom的名称
	private ArrayList<String> locationCells;
	private String name;

	//更新DotComwei位置的setter方法
	public void setLocationCells(ArrayList<String> loc){	
		locationCells = loc;
	} // end setLocationCells
	
	
	public void setName(String n){		//基本的setter方法
		name = n;
	}
	
	public String checkYourself(String userInput){
		String result = "miss";		
		int index = locationCells.indexOf(userInput);	//使用到indexOf()方法，如果玩家猜中，这个方法会返回它的位置。如果没有的话会返回-1
		if (index >= 0) {
			locationCells.remove(index);				//删除被猜中的元素
			
			if (locationCells.isEmpty()){				//用这个方法来判别是否击沉
				result = "kill";
				System.out.println("Ouch! You sunk " + name + " : (");
			} else {
				result = "hit";
			} //close if
		} //close outif

			return result;
		
	} //end String

} //end class



