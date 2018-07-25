package ch5_equals;

public class Manager extends Employee {
    //定义私有实例域
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day)
    {
        //子类自己的参数（私有域）
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary()
    {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }


    public void setBonus(double bouns)
    {
        this.bonus = bonus;
    }

    //判断是否与其他对象相等的方法，传入参数为其他对象
    public boolean equals(Object otherObject)
    {
        if (!super.equals(otherObject)) return false;
        Manager other = (Manager) otherObject;
        // super.equals checked that this and other belong to the same class
        return bonus == other.bonus;
    }

    public int hashCode()
    {
        return super.hashCode() + 17 * new Double(bonus).hashCode();
    }

    public String toString()
    {
        return super.toString() + "[bonus=" + bonus + "]";
    }




}
