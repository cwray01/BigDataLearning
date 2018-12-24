package ch2BasicsofAglorithms;

public class InsertionSort {
    public static void revertSort(int array[]){
        System.out.println("Before Sorting");
        for (int p=0; p < array.length; p++){
            System.out.println(array[p]);
        }
        System.out.println("==============");
        int key = 0;
        for(int j=1; j < array.length; j++){
            key = array[j];
            int i = j-1;
            while (i>=0 && array[i]<key){
                array[i+1] = array[i];
                i = i-1;
                array[i+1] = key;
            }
        }

        System.out.println("After Sorting");
        for(int p=0; p<array.length;p++){System.out.println(array[p]);}


    }
    public static void main(String[] args){
        //定义数组
        int A[] = {5,2,4,6,1,3};
        int B[] = {31,41,59,26,41,58};
        int key = 0;

//        System.out.println("Before Sorting");
        //print the array before sorting
        for(int p=0; p< B.length; p++){
//            System.out.println(A[p]);
        }
        
//        System.out.println("key: " + key);
//        System.out.println("B[0] " + B[0]);
//        System.out.println("==============");

        for(int j=1; j < B.length; j++){
            key = B[j];
            int i = j-1;
            while (i>=0 && B[i]>key){
                B[i+1] = B[i];
                i = i-1;
                B[i+1] = key;
            }
        }

        //print the array after sorting
//        System.out.println("After Sorting");
//        for(int p=0; p<B.length;p++){System.out.println(B[p]);}

        revertSort(B);
    }

}
