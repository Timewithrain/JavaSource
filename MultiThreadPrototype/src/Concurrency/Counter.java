package Concurrency;

public class Counter {
	private int count;
	
	public Counter() {
		this.count = 0;
	}
	
	//��һ��������з���ǰ��������synchronized��������������̰߳�ȫ���࣬����StringBuffer
	public synchronized void sub() {
		count--;
	}
	
	public synchronized void add() {
		count++;
	}
	
	//��������ʹ��Lock���ʹ��synchronized�ؼ���ʵ��ͬ��������
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
