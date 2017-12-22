
class TestArrays {

	
	public static void main(String [] args) {
		
		
		int [] index = new int[4];
			index[0] = 1;
			index[1] = 3;
			index[2] = 0;
			index[3] = 2;
		String [] islands = new String[4]; //如果把这段放在开头会被要求把islands变成static
			islands[0] = "Bermuda";
			islands[1] = "Fiji";
			islands[2] = "Azores";
			islands[3] = "Cozumel";
		int ref;
		int y = 0;

		
		while (y<4){
			ref = index[y];
			System.out.print("island = ");
			System.out.println(islands[ref]);
			y = y + 1;
		}
	}
	
}
