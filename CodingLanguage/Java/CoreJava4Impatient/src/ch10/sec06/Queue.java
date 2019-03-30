package ch10.sec06;

import java.util.Objects;

/**
 * 条件等待
 * synchronized方法确保这些操作是原子的
 */

public class Queue {
    class Node { Object value; Node next; } //Node 类包含两个属性，一个是value,还有一个是next
    private Node head;
    private Node tail;

    public synchronized void add(Object newValue) {
        Node n = new Node();    //new 一个Node对象
        if ( head == null ) head = n; //head也是一个Node对象，在第一次运行的时候head应为null, 使head = n
        else tail.next = n; //如果 head不是null,那么就把tail的next赋值为 n
        tail = n; //tail 本身也赋值为n
        tail.value = newValue; //将tail的value属性设置为传入的参数newValue
        notifyAll();    // notifyAll()会重新激活等待集合中的所有线程
    }

    public synchronized Object remove() {
        if (head == null) return null;
        Node n = head;
        head = n.next;
        return n.value;
    }

    public synchronized Object take() throws InterruptedException{  //由remove方法演变过来，如果队列为空，take方法会阻塞,取不到，先等一下
        while (head == null ) wait();   //只要其中一个线程成功获取了锁，它会从中断的位置，也就从调用wait的方法继续执行
                                        //此时线程应该再次测试while中的条件，不能保证条件是现在满足的
        Node n = head;
        head = n.next;
        return n.value;
    }
}
