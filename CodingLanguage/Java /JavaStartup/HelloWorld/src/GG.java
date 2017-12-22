
public class GG {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfGuesses = 0; //记录玩家猜测次数的变量
		GameHelper helper = new GameHelper();  //后面会写出这个方法
		
		SimpleDotCom theDotCom = new SimpleDotCom(); //创建dot com 对象
		int randomNum = (int) (Math.random() * 5); //用随机数产生第一格位置，然后以此制作数组
		
		int[] locations = {randomNum, randomNum+1, randomNum+2};
		theDotCom.setLocationCells(locations);//赋值位置
		boolean isAlive = true; //创建出记录游戏是否继续进行的boolean变量，用于while循环
		
		while(isAlive == true) {
			String guess = helper.getUserInput("enter a number"); //取得玩家输入
			String result = theDotCom.checkYourself(guess); //检查玩家的猜测并将结果存储在String中
			numOfGuesses++;	//increment guess count
			if (result.equals("kill")) { //是否击沉？如果击沉，则设定isAlive为false并列印出猜测次数
				isAlive = false;
				System.out.println("YOu took" + numOfGuesses + " guesses");
			}
		}
	}

}
