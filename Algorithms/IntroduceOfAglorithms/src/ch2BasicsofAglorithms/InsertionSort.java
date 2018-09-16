package ch2BasicsofAglorithms;

public class InsertionSort {
    public static void main(String[] args){
        //定义数组
        int A[] = {5,2,4,6,1,3};
        int B[];
        int key = 0;

        System.out.println("Before Sorting");
        //print the array before sorting
        for(int p=0; p< A.length; p++){
            System.out.println(A[p]);
        }
        
        System.out.println("key: " + key);
        System.out.println("A[0] " + A[0]);
        System.out.println("==============");

        for(int j=1; j < A.length; j++){
            key = A[j];
            int i = j-1;
            while (i>=0 && A[i]>key){
                A[i+1] = A[i];
                i = i-1;
                A[i+1] = key;
            }
        }

        //print the array after sorting
        System.out.println("After Sorting");
        for(int p=0; p<A.length;p++){System.out.println(A[p]);}
    }

}
