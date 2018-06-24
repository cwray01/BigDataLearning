package PackageTest;
// the Employee class is defined in that package
import PackageTest.com.horstmann.corejava.*;
import static java.lang.System.*;

/**
 * This program demonstrates the use of packages
 */


public class PackageTest
{
    public static void main(String[] args){
        //because of the import statement, we don't have to use
        // com.horstmann.corejava.Employee here
        Employee harry = new Employee("Harry Hacker", 5000, 1989, 10, 1);

        harry.raiseSalary(5);

        //because of the imort statement, we don't have to use System.out here
        out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
    }
}
