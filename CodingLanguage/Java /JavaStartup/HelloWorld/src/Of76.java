interface Nose1 {
	public int iMethod();
}

abstract class Picasso1 implements Nose1{
	public int iMethod(){
		return 7;
	}
}

class Clowns extends Picasso1{}
class Acts extends Picasso1{
	public int iMethod(){
		return 5;
	}
}



public class Of76 extends Clowns {
	public static void main(String [] args){
		Nose1 [] i = new Nose1 [3];
		i[0] = new Acts();
		i[1] = new Clowns();
		i[2] = new Of76();
		for (int x = 0; x < 3; x++){
			System.out.println(i[x].iMethod() + " " + i[x].getClass());
		}
	}
}
