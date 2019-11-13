package ThreadOption;

import MultiThreadDemo.Role;

public class ThreadOptions {
	static int count = 0;
	
	public static void main(String[] args) {
		//sleep()������ʹ�� ����->����
//		Thread t1 = new Thread() {;
//			public void run() {
//				System.out.println("�߳̿�ʼ���У�");
//				try {
//					//�߳�˯��3����
//					Thread.sleep(3100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("�߳��������У�");
//			}
//		};
//		
//		t1.start();
//		for(int i=0;i<3;i++) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(i+1);
//		}
		
		
		//join()������ʹ�ã�����join���߳��������ȴ����߳�ִ����Ϻ��Ѹ��߳�
//		Thread t2 = new Thread() {
//			public void run() {
//				for(int i=0;i<3;i++) {
//					try {
//						this.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("t2���У�");
//				}
//			}
//		};
//		
//		Thread t3 = new Thread() {
//			public void run() {
//				int count = 0;
//				while(count<=5) {
//					try {
//						this.sleep(1000);
//						if(count==2) {
//							//join()����Ӧ����start()������
//							t2.start();
//							t2.join();
//						}
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println(count++);
//					
//				}
//			}
//		};
//		
//		t3.start();
		
		/* priority�߳����ȼ�,���ȼ�Ĭ��Ϊ5�����Ϊ1�����λΪ10�����������Χ���׳��쳣
		 * �߳�ִ��˳�������˳���޹أ����ȼ�ʹ��CPU��������Դ���ȸ������ȼ����ߵ��̣߳����Ǿ��в�ȷ���ԣ��������ȼ��ߵ�һ����ִ�����
		 * ����ȫͳ����ʾ���ϸߵ����ȼ��������Ч��ռ����Դ���ִ��
		 */
//		Thread t1 = new Thread() {
//			public void run() {
//				int count = 0;
//				while(count<5) {
//					System.out.println(this.getPriority());
//					count++;
//				}
//			}
//		};
//		
//		Thread t2 = new Thread() {
//			public void run() {
//				int count = 0;
//				while(count<5) {
//					System.out.println(this.getPriority());
//					count++;
//				}
//			}
//		};
//		t1.setPriority(1);
//		t2.setPriority(2);
//		t1.start();
//		t2.start();
		
		//yield()�ó�����������->���������ø÷������߳��ó�����CPUʱ��Ƭ���ɰ����Լ����ڵ������߳����¾���
//		Thread t1 = new Thread() {
//			public void run() {
//				for(int i=0;i<20;i++) {
//					if(i==5) {
//						Thread.yield();
//					}
//					System.out.println("�߳�1  "+i);
//				}
//			}
//		};
//		
//		Thread t2 = new Thread() {
//			public void run() {
//				for(int i=0;i<20;i++) {
//					System.out.println("�߳�2  "+i);
//				}
//			}
//		};
//		
//		t1.start();
//		t2.start();
		
		//wait()����(����->����)��notify()����(����->����)��wait()������Ὣ������Դ�Լ�������Դ�Ķ������ͷţ������̼��뵽�ȴ������У��ȴ�notify()����
		//�˴�ʵ����wait()��notifyAll()����������hurt()�Լ�recover()������
		/**
		 * wait()������notify()/noytifyAll()��������Ҫ������synchronized���εķ����������У�������Ϊwait()��noyify()/notifyAll()�����ײ㶼��Ҫ����
		 * Monitorʵ�֣���synchronized����ײ������monitorentry�Լ�monitorexit��������synchronized���εķ���������֮��ʹ�������������������
		 * java.lang.IllegalMonitorStateException�쳣������ο����ͣ�https://www.cnblogs.com/paddix/p/5367116.html
		 * 
		 */
		Role wolverine = new Role("�����",30,500);
		//�߳�1������ܵ��˺�������ֵ����
		Thread t1 = new Thread() {
			public void run() {
				//�Խ��������˺�
				while(true) {
					wolverine.hurt();
					System.out.println("������ܵ����˺�������ֵʣ�ࣺ" + wolverine.getHP());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				//����ǿ�ʼ����
				while(true) {
					wolverine.recover();
					System.out.println("���������������HP�ָ���:" + wolverine.getHP());
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		t2.start();
	}
	
}


