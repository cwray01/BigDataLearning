class Player2 {
	static int playerCount = 0;
	private String name;
	public Player2(String n){
		name = n;
		playerCount++;
	}
}

public class PlayerTestDrive {
	public static void main(String[] args) {
		System.out.println(Player2.playerCount);
		Player2 one = new Player2("Tiger Woods");
		System.out.println(Player2.playerCount);
		
	}
}
