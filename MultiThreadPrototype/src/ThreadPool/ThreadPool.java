package ThreadPool;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
	//线程池的大小
	private int size;
	//作业队列用于存放需要线程处理的任务
	private static Queue<Runnable> jobs = new LinkedList<Runnable>();
	
	public ThreadPool(int size) {
		this.size = size;
		init();
	}
	
	//私有函数，初始化线程池内的线程
	private synchronized void init() {
		for(int i=0;i<size;i++) {
			new WorkThread(i).start();
		}
	}
	
	//向线程池中的作业队列添加作业
	public void addJob(Runnable job) {
		synchronized (jobs) {
			//当作业不为空时将作业加入到作业队列，并唤醒线程
			if(job!=null) {
				jobs.offer(job);
				jobs.notifyAll();
			}
		}
	}
	
	//工作线程，通过内部类的方式实现，直接访问jobs队列
	public class WorkThread extends Thread {
		//线程编号
		private int num;
		//此线程需要执行的任务
		Runnable job;
		
		public WorkThread(int num) {
			this.num = num;
			this.job = null;
		}
		
		public void run() {
			while(true) {
				//为jobs作业队列加上对象锁，只有当前线程能访问jobs，保证线程同步
				synchronized(jobs) {
					//当作业队列为空线程等待，直到作业队列中有作业需要执行而被唤醒
					/*此处while循环可更改为if判断，但唤醒进程时应改为notify()一次唤醒一个进程，避免多个进程访问jobs时出现读到null的情况*/
					while(jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//当作业队列不为空时，从作业队列中获取一个作业开始运行
					job = jobs.poll();
					System.out.print("\n线程"+this.num+"获得作业，开始执行！");
				}
				job.run();
				System.out.print("线程"+this.num+"完成作业！"+"\t");
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool(10);
		for(int i=0;i<30;i++) {
			Runnable r = new Runnable() {
				public void run() {
					try {
						//延时5秒表示执行作业
//						Thread.sleep(5000);
						Thread.sleep(200 * (int)(1+Math.random()*10));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			pool.addJob(r);
			//等待一段时间再添加作业，且作业添加的频率越来越快，最后0.2秒添加一次作业
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
