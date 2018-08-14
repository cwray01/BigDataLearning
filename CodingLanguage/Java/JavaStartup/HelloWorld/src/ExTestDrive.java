class MyEx extends Exception{ }

public class ExTestDrive{
	public static void main(String [] args) {
		String test = args[0];
		try{
			System.out.print("t");
			doRisky(test);
			System.out.print("o");
		} catch (MyEx e){
			if ( "yes".equals("t")){
				System.out.print("a");
			}
		} finally{
			System.out.print("w");
		}
			System.out.print("s");
		} //end try
		
	static void doRisky(String t) throws MyEx{
		System.out.print("h");
		if ("yes".equals(t)){
			throw new MyEx();
		}
		System.out.print("r");
	
		
	} //end main


}