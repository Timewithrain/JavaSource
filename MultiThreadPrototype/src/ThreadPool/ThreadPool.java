package ThreadPool;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
	//�̳߳صĴ�С
	private int size;
	//��ҵ�������ڴ����Ҫ�̴߳��������
	private static Queue<Runnable> jobs = new LinkedList<Runnable>();
	
	public ThreadPool(int size) {
		this.size = size;
		init();
	}
	
	//˽�к�������ʼ���̳߳��ڵ��߳�
	private synchronized void init() {
		for(int i=0;i<size;i++) {
			new WorkThread(i).start();
		}
	}
	
	//���̳߳��е���ҵ���������ҵ
	public void addJob(Runnable job) {
		synchronized (jobs) {
			//����ҵ��Ϊ��ʱ����ҵ���뵽��ҵ���У��������߳�
			if(job!=null) {
				jobs.offer(job);
				jobs.notifyAll();
			}
		}
	}
	
	//�����̣߳�ͨ���ڲ���ķ�ʽʵ�֣�ֱ�ӷ���jobs����
	public class WorkThread extends Thread {
		//�̱߳��
		private int num;
		//���߳���Ҫִ�е�����
		Runnable job;
		
		public WorkThread(int num) {
			this.num = num;
			this.job = null;
		}
		
		public void run() {
			while(true) {
				//Ϊjobs��ҵ���м��϶�������ֻ�е�ǰ�߳��ܷ���jobs����֤�߳�ͬ��
				synchronized(jobs) {
					//����ҵ����Ϊ���̵߳ȴ���ֱ����ҵ����������ҵ��Ҫִ�ж�������
					/*�˴�whileѭ���ɸ���Ϊif�жϣ������ѽ���ʱӦ��Ϊnotify()һ�λ���һ�����̣����������̷���jobsʱ���ֶ���null�����*/
					while(jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//����ҵ���в�Ϊ��ʱ������ҵ�����л�ȡһ����ҵ��ʼ����
					job = jobs.poll();
					System.out.print("\n�߳�"+this.num+"�����ҵ����ʼִ�У�");
				}
				job.run();
				System.out.print("�߳�"+this.num+"�����ҵ��"+"\t");
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool(10);
		for(int i=0;i<30;i++) {
			Runnable r = new Runnable() {
				public void run() {
					try {
						//��ʱ5���ʾִ����ҵ
//						Thread.sleep(5000);
						Thread.sleep(200 * (int)(1+Math.random()*10));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			pool.addJob(r);
			//�ȴ�һ��ʱ���������ҵ������ҵ��ӵ�Ƶ��Խ��Խ�죬���0.2�����һ����ҵ
			int x = 0;
			try {
				Thread.sleep(1000-x);
				if(x<900) {
					x += 150;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
