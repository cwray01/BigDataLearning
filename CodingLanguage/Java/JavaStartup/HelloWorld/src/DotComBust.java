import java.util.*;	
public class DotComBust {
	//声明并初始化变量
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	
	private void setrUpGame() {
		//first make some dot coms and five them locations
		//创建3个DotCom对象并指派名称
		DotCom one = new DotCom();
		one.setName("Pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("Goo2.com");

		
		//将上面的三个对象置入ArrayList
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		//列出简短提示
		System.out.println("Your goal is to sink three dot coms.");
		System.out.println("Pet.com, eToys.com, Goo2.com");
		System.out.println("Try to sink them all in the fewest number of guesses");
		
		for (DotCom dotComToSet : dotComsList) {							//对list中的每个DotCom重复一次
			ArrayList<String> newLocation = helper.placeDotCom(3);		//要求DotCom的位置
			dotComToSet.setLocationCells(newLocation);					//调用Dotcom的setter方法来指派刚取得的位置
		} //close for loop
	}	//close setUpGame method
	
	private void startPlaying() {
		while (!dotComsList.isEmpty())	{								//	判断DotCom的list是否为空
			String userGuess = helper.getUserInput("Enter a guess");	// 取得玩家输入
			checkUserGuess(userGuess);									//调用checkUserGuess的方法
		}	 //close while
		finishGame(); 													//调用finishGame方法
	}																	//close startPlaying method
	
	private void checkUserGuess(String userGuess) {
		numOfGuesses++; 												//递增玩家猜测次数的计数
		String result = "miss";											//先假设没有命中
		
		for (DotCom dotComToTest : dotComsList) {						//要求DotCom检查是否命中或者击沉
				result = dotComToTest.checkYourself(userGuess);			//列出结果
				if (result.equals("hit")) {
					break;												//提前跳出循环
				}
				if (result.equals("kill")){
					dotComsList.remove(dotComToTest);					//对list中所有的DotCom重复
					break;
				}						
		} // close for
		System.out.println(result);										//这家伙挂了
	}	//close method
	
	private void finishGame(){
			System.out.println("All Dot Coms are dead! Your stock is now worthless.");
			if (numOfGuesses <= 18){
				System.out.println("It only took you " + numOfGuesses + " guess.");
				System.out.println(" You got out before your options sank.");
			}	else {
				System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
				System.out.println("Fish are dancing with your options.");
			}
	}	// close method
	
	public static void main (String [] args) {
		DotComBust game = new DotComBust();
		game.setrUpGame();
		game.startPlaying();
	}	// close method
}
