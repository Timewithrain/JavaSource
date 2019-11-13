package Concurrency;

public class Counter {
	private int count;
	
	public Counter() {
		this.count = 0;
	}
	
	//当一个类的所有方法前都加上了synchronized修饰则这个类是线程安全的类，例如StringBuffer
	public synchronized void sub() {
		count--;
	}
	
	public synchronized void add() {
		count++;
	}
	
	//用于体现使用Lock类和使用synchronized关键词实现同步的区别
	public void lockedsub() {
		count--;
	}
	
	public void lockedadd() {
		count++;
	}
	
	public synchronized void show() {
		System.out.println(count);
	}
}
