package ch14_synch;
import java.util.*;
import java.util.concurrent.locks.*;

/**
 * A bank with a number of bank accounts.
 */
public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    /**
     * Constructs the bank
     * @param n the number of accounts
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock(); //可重入锁
        sufficientFunds = bankLock.newCondition();

    }

    /**
     * Transfers money from one account to another
     * @param from the account to transfer from
     * @param to the account to transfer to
     * @param amount the amount to transfer
     */
    public void transfer(int from, int to, double amount) throws InterruptedException
    {
        bankLock.lock();
        try
        {
            while(accounts[from] < amount)
            sufficientFunds.await();    //condition
            if (accounts[from] < amount) return;
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();    //在解锁前将状态置为等待线程可执行
        }
        finally
        {
            bankLock.unlock();      //解锁
        }


    }

    /**
     * Gets the sum of all account balances.
     * @return the total balance
     */
    public double getTotalBalance()
    {
        bankLock.lock();    //获取余额的时刻就将bankLock锁起来
        try{
            double sum = 0;
            for (double a : accounts)
                sum += a;
            return sum;
        }
        finally
        {
            bankLock.unlock();
        }

    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size()
    {
        return accounts.length;
    }
}
