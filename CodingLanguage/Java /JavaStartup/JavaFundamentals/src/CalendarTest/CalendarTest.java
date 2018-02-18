package CalendarTest;
import java.time.*;
// This programme uses the class java.time.LocalDate

/**
 *  @version 1.5
 *  @Autor cwray
 *  */

public class CalendarTest {

    public static void main(String[] args)
    {
        LocalDate date = LocalDate.now();

        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        System.out.println(date);
        System.out.println(month);
        System.out.println(today);


        date = date.minusDays(today - 1); // Set to start of month

        System.out.println(date);

        DayOfWeek weekday = date.getDayOfWeek();    //得到当前日期是星期几，作为DayOfWeek类的一个势力返回，调用getValue得到1-7之间的输

        System.out.println(weekday);

        int value = weekday.getValue(); // 1 = Monday, ... 7 = Sunday

        System.out.println(value); //本月第一天是星期几

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i< value; i++)
            System.out.print("    ");
        while (date.getMonthValue() == month)
        {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today)  //得到当前的年月日，做标记*
                System.out.print("*");
            else
                System.out.print(" ");  //不是当前年月日，跳过
            date = date.plusDays(1);    //往后加一天

            if (date.getDayOfWeek().getValue() == 1) System.out.println(); //换行

        }
        if (date.getDayOfWeek().getValue() != 1) System.out.println();
    }
}
