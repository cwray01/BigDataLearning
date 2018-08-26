package ch16_SetAndGeneric;

public class TestGenerics1 {
    public static void main(String[] args){
        new TestGenerics1().go();
    }

    public void go(){
        Animal[] animals = {new Dog(), new Cat(), new Dog()};
        Dog[] dogs = {new Dog(), new Dog(), new Dog()};
        takeAnimals(animals);       //对两种数组进行调用takeAnimal方法
        takeAnimals(dogs);

    }

    public void takeAnimals(Animal[] animals){
        for(Animal a: animals){
            a.eat();
        }
    }
}

abstract class Animal{
    void eat(){
        System.out.println("animal eating");
    }
}
class Dog extends Animal {
    void bark(){}
}
class Cat extends Animal {
    void meow(){}
}
