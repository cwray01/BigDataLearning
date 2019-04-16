package ch14_nonsynch;

/**
 * This program shows data corruption when multiple threads access a data structure.
 */
public class unsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;  //初始余额
    public static final double MAX_AMOUNT = 1000;   //最大数量
    public static final int DELAY = 10; //延迟时间

    public static void main(String[] args){
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE); //初始化Bank对象,传入账户个数和初始余额
        for (int i = 0; i < NACCOUNTS; i++)
        {
            int fromAccount = i;    //被转出账户数
            Runnable r = () -> {
                try
                {
                    while(true)
                    {
                        int toAccount = (int) (bank.size() * Math.random());    //转入账户，取一个随机数
                        double amount = MAX_AMOUNT * Math.random(); //交易金额，并行的交易数*随机数
                        bank.transfer(fromAccount, toAccount, amount); //转账方法， 从fromAccount到toAccount,金额为amount
                        Thread.sleep((int) (DELAY * Math.random()));    //启动线程
                    }
                }
                catch (InterruptedException e){

                }
            };
            Thread t = new Thread(r);
            t.start();
        }

    }
}
