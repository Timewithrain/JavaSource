package AtomicClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClass {
	static int value = 0;
	static AtomicInteger atomvalue = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		/*
		 * int 类型的变量提供i++/i--这样的自增操作，但这样的语句是通过三条语句构成的，分别是取值，加1/减1，再赋值。在多线程情况下这样的操作是不安全的
		 * 	有可能会使数据产生错误，由此，JDK6之后提供了原子类型的变量类使得操作变得线程安全
		 */
		//不使用线程安全的类实现多线程访问同一个数据
		//存放所有的线程，便于遍历所有线程，使线程全部执行完毕
		ArrayList<Thread> threadgroup = new ArrayList<Thread>();
		for(int i=0;i<10000;i++) {
			Thread t = new Thread() {
				public void run() {
					value++;
				}
			};
			t.start();
			threadgroup.add(t);
		}
		
		//利用迭代器遍历所有线程，使其执行完毕，以便读取value增加后的结果
		Iterator<Thread> threadite = threadgroup.iterator();
		while(threadite.hasNext()) {
			threadite.next().join();
		}
		System.out.println("value自增后结果为：" + value);
		
		//使用线程安全的原子类实现多线程访问同一个数据
		ArrayList<Thread> threadgroup2 = new ArrayList<Thread>();
		for(int i=0;i<10000;i++) {
			Thread t = new Thread() {
				public void run() {
					//incrementAndGet()方法等价于自增
					atomvalue.incrementAndGet();
				}
			};
			t.start();
			threadgroup2.add(t);
		}
		
		threadite = threadgroup2.iterator();
		while(threadite.hasNext()) {
			threadite.next().join();
		}
		//自动调用toString()方法显示其值
		System.out.println("atomvalue自增后结果为：" + atomvalue);
	}

}
