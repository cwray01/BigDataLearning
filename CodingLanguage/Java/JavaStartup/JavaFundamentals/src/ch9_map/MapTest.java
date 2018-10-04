package ch9_map;

import java.util.*;

/**
 * This program demonstrates the use of a map with key type String and value type Employee
 * 先将k/v对添加到映射中
 * 然后从映射中删除一个键，同时与之对应的值也被删除了
 * 接下来，修改与某一个键对应的值，并调用get方法查看这个值
 */
public class MapTest {

    public static void main(String[] args)
    {

        Map<String, Employee> staff = new HashMap<>();
        staff.put("11223344", new Employee("Amy Lee"));
        staff.put("33445566", new Employee("Harry Hacker"));
        staff.put("77889900", new Employee("Gary Cooper"));
        staff.put("88293950", new Employee("Francesca Cruz"));

        //print all entries
        System.out.println(staff);
        //System.out.println(staff.toString());


        //remove an entry
        staff.remove("33445566");

        //replace an entry
        staff.put("88293950", new Employee("Francesca Miller"));

        //look up a value
        System.out.println(staff.get("77889900"));

        //iterate through all entries
        staff.forEach((k,v) ->
            System.out.println("key=" + k + ", value=" + v));
    }
}

class Employee {

    private String name;

    public Employee(String name)
    {
        this.name = name;
    }


    public String getName(){ return name;}


    public String toString()
    {
        return getClass().getName() + "[name=" + name + "]";
    }

}


