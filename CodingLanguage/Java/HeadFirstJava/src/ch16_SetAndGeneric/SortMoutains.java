package ch16_SetAndGeneric;

import java.io.*;
import java.util.*;

public class SortMoutains {
    LinkedList<Mountain> mtn = new LinkedList<Mountain>();
    class NameCompare implements Comparator<Mountain>{
        public int compare(Mountain one, Mountain two){
            return one.name.compareTo(two.name);
        }
    }

    public static void main(String [] args){
        new SortMoutains().go();
    }

    class HeightCompare implements Comparator<Mountain> {
        public int compare(Mountain one, Mountain two){
            return one.height - two.height;
        }
    }

    public void go(){
        mtn.add(new Mountain("Long", 14255));
        mtn.add(new Mountain("Elbert", 14433));
        mtn.add(new Mountain("Marron", 14156));
        mtn.add(new Mountain("Castle", 14265));
        System.out.println("as entered:\n" + mtn);
        NameCompare nc = new NameCompare();
        Collections.sort(mtn, nc);
        System.out.println("by name:\n" + mtn);

        HeightCompare hc = new HeightCompare();
        Collections.sort(mtn, hc);
        System.out.println("by height:\n" + mtn);
    }

}

class Mountain{
    String name;
    int height;


    Mountain(String n, int h){
        name = n;
        height = h;
    }

    public String toString(){
        return name + " " + height;
    }

}
