package ch16_SetAndGeneric2;


import java.util.ArrayList;

public class TestGenerics2 {
    public static void main(String[] args) {
        new TestGenerics2().go();
    }

    public void go() {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Dog()); //数组列表只能一个一个add，没有数组的语法

        takeAnimals(animals);   //除了animal这个变量引用ArrayList而不是数组以外，程序代码都相同

        ArrayList<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        takeAnimals(dogs);


    }

    public void takeAnimals(ArrayList<? extends Animal> animals) {  //重点在于takeAnimal()能够取用Animal[]或Dog[]参数，因为Dog是一个Animal，多态再次发挥作用
        for (Animal a : animals) {
            a.eat();    //只能调用声明在Animal中的方法，因为它的参数是Animal数组，且无需任何类型转换
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

class Cat extends Animal{
    void meow(){}
}

