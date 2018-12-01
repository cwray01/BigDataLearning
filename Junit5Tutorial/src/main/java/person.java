public class person {
    private static String name;
    private static String FirstName;
    private static String LastName;
    private int age;

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public static String getFirstName(){
        return FirstName;
    }

    public void setFirstName(){
        this.FirstName = FirstName;
    }

    public static String getLastName(){
        return LastName;
    }

    public void setLastName(){
        this.LastName = LastName;
    }

}
