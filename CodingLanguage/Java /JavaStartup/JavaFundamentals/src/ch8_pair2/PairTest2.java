package ch8_pair2;

import java.time.*;

public class PairTest2 {
    public static void main(String[] args)
    {
        //存储一个时间数组
        LocalDate[] birthdays =
                {
                        LocalDate.of(1906, 12, 9), //G. Hopper
                        LocalDate.of(1915, 12, 10), //A. Lovelace
                        LocalDate.of(1903, 6, 22), //K. Zuse
                };

        String[] words = {"Mary", "had", "a", "little", "lamb"};


        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
        Pair<String> wordsmm = ArrayAlg.minmax(words);

        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());

        System.out.println("wordsmin = " + wordsmm.getFirst());
        System.out.println("wordsmax = " + wordsmm.getSecond());



    }
}

class ArrayAlg
{
    /**
     * Gets the minimum and maximum of an array of objects of type T
     * @param a an array of objects of type T
     * @retrun a pair with the min and max value, or null if a is null or empty
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a)
    {
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min,max);
    }
}
