package ch9_linkedList;
import java.util.*;


/**
 * This program demonstrates operations on linked lists
 */
public class LinkedListTest {
    public static void main(String[] args)
    {
        List<String> a = new LinkedList<>();    //新建一个List a用于存储String值
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();    //新建另一个List b用于存储String值
        b.add("Bob");
        b.add("Dong");
        b.add("France");
        b.add("Gloria");

        //merge the words from b into a

        ListIterator<String> aIter = a.listIterator();  //ListItertor<E> listIterator()返回一个列表迭代器，用于访问列表中的元素
        Iterator<String> bIter = b.iterator();  //Iterator<E> iterator()返回一个用于访问集合中每个元素的迭代器

        while (bIter.hasNext()) //当迭代器bIter存在下一位元素时进入该循环
        {
            if(aIter.hasNext()) aIter.next();   //如果aIter也有下一个元素，也进入下一个循环
            aIter.add(bIter.next()); //将bIter中的元素追加加到aIter中
        }

        System.out.println(a);  //打印此时的列表a

        //remove every second word from b

        bIter = b.iterator();   //返回b集合的迭代器对象
        while (bIter.hasNext())    //当迭代器bIter存在下一位元素时进入该循环
        {
            bIter.next(); //skip next element
            if (bIter.hasNext());
            {
                bIter.next(); //skip next element
                bIter.remove(); //remove that element
            }
        }
        System.out.println(b);

        //bulk operation: remove all words in b from a
        a.removeAll(b);
        System.out.println(a);
    }

}
