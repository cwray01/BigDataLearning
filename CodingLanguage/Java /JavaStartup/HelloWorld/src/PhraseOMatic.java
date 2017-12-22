
public class PhraseOMatic {
	public static void main(String[] args) {
		//可以随意加上其他术语
		String[] wordListOne = {"24/7", "multiTier","30000 foot","B-to-B","win-win","front-end","web-based","pervasive","smart","six-sigma","critical-path","dynamic"};
		
		String[] wordListTwo = {"empowered","sticky","value-added","oriented","centric","distributed","clustered","branded","outside-the-box","positined","networked","focused","leveraged","aligned","targeted","shared","cooperative","accelerated"};
		
		String[] wordListThree = {"process","tippingpoint","solution","archetecture","core competency","strategy","mindshare","portal","space","vision","paradigm","mission"};
		
		//计算每一粟有多少个名词术语
		int oneLength = wordListOne.length;
		int twoLength = wordListTwo.length;
		int threeLength = wordListThree.length;
		
		//产生随机数字
		int rand1 = (int) (Math.random() * oneLength);
		int rand2 = (int) (Math.random() * twoLength);
		int rand3 = (int) (Math.random() * threeLength);
		
		//组合出专家术语
		String phrase = wordListOne[rand1] + " " + wordListTwo[rand2]+ " " + wordListThree[rand3];
		
		//output
		System.out.println("What we need is a " + phrase);
	}
}
