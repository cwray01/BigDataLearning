package ch5_enums;

import java.util.*;

/**
 * This program demonstrates enumerated types
 */
public class EnumTest {
    public static void main(String[] args)
    {
        //向用户请求输入
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a size:(SMALL, MEDIUM, LARGE)");
        //将输入内容全部大写
        String input = in.next().toUpperCase();
        //打印输入内容
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.getAbbreviation());
        if (size == Size.EXTRA_LARGE)
            System.out.println("Good Job -- you paid attention to the _.");
    }
}

enum Size
{
    //定义枚举类
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    //定义枚举类的方法
    private Size(String abbreviation) {this.abbreviation = abbreviation;}
    public String getAbbreviation() {return abbreviation;}

    //定义枚举类的域
    private String abbreviation;
}

