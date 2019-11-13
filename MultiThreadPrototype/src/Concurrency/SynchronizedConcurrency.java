package Concurrency;

public class SynchronizedConcurrency {

	public static void main(String[] args) {
		Counter counter = new Counter();
		
		//ͨ��synchronized�ؼ�����Ӷ�counter�ķ������ƣ�counter��Ϊͬ�������̶߳�ռcounter�������
		Thread t1 = new Thread() {
			public void run() {
				synchronized(counter) {
					for(int i=0;i<100;i++) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						counter.add();
						counter.show();
					}
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				synchronized(counter) {
					for(int i=0;i<100;i++) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						counter.sub();
						counter.show();
					}
				}
			}
		};
		
		t1.start();
		t2.start();
		
		//��ArrayListת��Ϊ�̰߳�ȫ���࣬��ϸ�ڲ�ʵ�����ݲο����ͣ�https://www.cnblogs.com/yaowen/p/5983136.html
//		List<String> list = java.util.Collections.synchronizedList(new ArrayList<String>());
	}
}
