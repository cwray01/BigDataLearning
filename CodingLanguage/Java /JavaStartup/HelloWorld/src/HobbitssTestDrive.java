
class HobbitssTestDrive {
	public static void main(String [] args){
		
		Hobbitss [] h = new Hobbitss[3];
		int z = -1;
		
		while (z<2){
			z = z + 1;
			h[z] = new Hobbitss();
			h[z].name = "bilbo";
			if (z == 1){
				h[z].name = "frodo";	
			}
			
			if (z == 2){
				h[z].name = "sam";
				
			}
			
			System.out.print(h[z].name + " is a ");
			System.out.println("good Hobbit name");
		}
	}
	
}

	

