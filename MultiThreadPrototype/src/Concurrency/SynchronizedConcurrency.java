package Concurrency;

public class SynchronizedConcurrency {

	public static void main(String[] args) {
		Counter counter = new Counter();
		
		//通过synchronized关键字添加对counter的访问限制，counter成为同步对象，线程独占counter避免出错
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
		
		//将ArrayList转换为线程安全的类，详细内部实现内容参考博客：https://www.cnblogs.com/yaowen/p/5983136.html
//		List<String> list = java.util.Collections.synchronizedList(new ArrayList<String>());
	}
}
