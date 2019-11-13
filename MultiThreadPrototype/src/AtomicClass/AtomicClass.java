package AtomicClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClass {
	static int value = 0;
	static AtomicInteger atomvalue = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		/*
		 * int ���͵ı����ṩi++/i--�����������������������������ͨ��������乹�ɵģ��ֱ���ȡֵ����1/��1���ٸ�ֵ���ڶ��߳�����������Ĳ����ǲ���ȫ��
		 * 	�п��ܻ�ʹ���ݲ��������ɴˣ�JDK6֮���ṩ��ԭ�����͵ı�����ʹ�ò�������̰߳�ȫ
		 */
		//��ʹ���̰߳�ȫ����ʵ�ֶ��̷߳���ͬһ������
		//������е��̣߳����ڱ��������̣߳�ʹ�߳�ȫ��ִ�����
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
		
		//���õ��������������̣߳�ʹ��ִ����ϣ��Ա��ȡvalue���Ӻ�Ľ��
		Iterator<Thread> threadite = threadgroup.iterator();
		while(threadite.hasNext()) {
			threadite.next().join();
		}
		System.out.println("value��������Ϊ��" + value);
		
		//ʹ���̰߳�ȫ��ԭ����ʵ�ֶ��̷߳���ͬһ������
		ArrayList<Thread> threadgroup2 = new ArrayList<Thread>();
		for(int i=0;i<10000;i++) {
			Thread t = new Thread() {
				public void run() {
					//incrementAndGet()�����ȼ�������
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
		//�Զ�����toString()������ʾ��ֵ
		System.out.println("atomvalue��������Ϊ��" + atomvalue);
	}

}
