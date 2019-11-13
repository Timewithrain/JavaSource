package ThreadOption;

import MultiThreadDemo.Role;

public class ThreadOptions {
	static int count = 0;
	
	public static void main(String[] args) {
		//sleep()方法的使用 运行->阻塞
//		Thread t1 = new Thread() {;
//			public void run() {
//				System.out.println("线程开始运行！");
//				try {
//					//线程睡眠3秒钟
//					Thread.sleep(3100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("线程重新运行！");
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
		
		
		//join()方法的使用，调用join后父线程阻塞，等待子线程执行完毕后唤醒父线程
//		Thread t2 = new Thread() {
//			public void run() {
//				for(int i=0;i<3;i++) {
//					try {
//						this.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("t2运行！");
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
//							//join()方法应放在start()方法后
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
		
		/* priority线程优先级,优先级默认为5，最低为1，最高位为10，超过这个范围会抛出异常
		 * 线程执行顺序与调用顺序无关，优先级使得CPU尽量将资源优先给予优先级更高的线程，但是具有不确定性，并非优先级高的一定先执行完毕
		 * 不完全统计显示，较高的优先级会更能有效地占据资源完成执行
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
		
		//yield()让出方法，运行->就绪，调用该方法后线程让出本次CPU时间片，由包括自己在内的所有线程重新竞争
//		Thread t1 = new Thread() {
//			public void run() {
//				for(int i=0;i<20;i++) {
//					if(i==5) {
//						Thread.yield();
//					}
//					System.out.println("线程1  "+i);
//				}
//			}
//		};
//		
//		Thread t2 = new Thread() {
//			public void run() {
//				for(int i=0;i<20;i++) {
//					System.out.println("线程2  "+i);
//				}
//			}
//		};
//		
//		t1.start();
//		t2.start();
		
		//wait()方法(运行->挂起)和notify()方法(挂起->就绪)，wait()操作后会将已有资源以及持有资源的对象锁释放，将进程加入到等待队列中，等待notify()唤醒
		//此处实例的wait()和notifyAll()方法包含在hurt()以及recover()方法中
		/**
		 * wait()方法和notify()/noytifyAll()方法都需要包含在synchronized修饰的方法或语句块中，这是因为wait()、noyify()/notifyAll()方法底层都需要依赖
		 * Monitor实现，而synchronized语义底层包括了monitorentry以及monitorexit命令，因此在synchronized修饰的方法或语句块之外使用上述三个方法会产生
		 * java.lang.IllegalMonitorStateException异常。详情参考博客：https://www.cnblogs.com/paddix/p/5367116.html
		 * 
		 */
		Role wolverine = new Role("金刚狼",30,500);
		//线程1金刚狼受到伤害，生命值减少
		Thread t1 = new Thread() {
			public void run() {
				//对金刚狼造成伤害
				while(true) {
					wolverine.hurt();
					System.out.println("金刚狼受到了伤害，生命值剩余：" + wolverine.getHP());
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
				//金刚狼开始自愈
				while(true) {
					wolverine.recover();
					System.out.println("金刚狼正在自愈，HP恢复至:" + wolverine.getHP());
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


