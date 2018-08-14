class Dogg {
	String name;
	public static void main (String[] args){
	
	//创建Dog对象	
	Dogg dog1 = new Dogg();
	dog1.bark();
	dog1.name = "Bart";
	
	//创建Dog数组
	Dogg[] myDogs = new Dogg[3];
	
	//关门放狗
	myDogs[0] = new Dogg();
	myDogs[1] = new Dogg();
	myDogs[2] = dog1;
	
	//通过数组饮用存取Dog
	myDogs[0].name = "Fred";
	myDogs[1].name = "Marge";
	
	//myDog[2] 's name
	System.out.print("Last dog's name is ");
	System.out.println(myDogs[2].name);
	
	//逐个对Dog执行Bark（）
	int x = 0;
	while (x< myDogs.length) {
		myDogs[x].bark();
		x = x + 1;
	}
	}
	
	public void bark(){
		System.out.println(name + " says Ruff!" );
	}
	public void eat() {}
	public void chaseCat(){}
	
	
}
