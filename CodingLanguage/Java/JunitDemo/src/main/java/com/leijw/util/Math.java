package com.leijw.util;

public class Math {
    /**
     * 阶乘
     * @param n
     * @return
     */
    public int factorial(int n) throws Exception{
        if (n < 0){
            throw new Exception("Negative numbers are not allow to do this");
        } else if (n <= 1){
            return 1;
        } else {
            return n * factorial(n -1);
        }
    }

    /**
     * 斐波那契数列
     * @param n
     * @return
     */
    public int fibonacci(int n) throws Exception{
        if (n <= 0) {
            throw new Exception("fibonacci series should start from 1");
        } else if(n==1){
            return 0;
        } else if (n==2){
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
