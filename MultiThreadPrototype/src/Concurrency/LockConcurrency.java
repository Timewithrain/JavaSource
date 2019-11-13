package Concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConcurrency {

	public static void main(String[] args) {
		Counter counter = new Counter();
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		counter.show();
		Thread t1 = new Thread() {
			public void run() {
				//记录是否获取过锁
				boolean hadLocked = false;
				try {
					//线程开始时尝试获取锁，可在tryLock()方法中传入时间参数，超时后不再尝试获取锁，此机制可避免程序死锁
					if(lock.tryLock(1,TimeUnit.SECONDS)) {
						//获取锁hadLock置为true
						hadLocked = true;
						System.out.println("线程1获取了锁");
						for(int i=0;i<1000;i++) {
							counter.lockedadd();
						}
						counter.show();
						//当运行完成时，唤醒其他等待的进程
						condition.signalAll();
					}else {
						//锁被占有时线程阻塞
						System.out.println("锁被占有，线程1阻塞");
						//通过Condition对象的await()方法阻塞自己
						condition.await();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					//当获取了锁才释放锁，否则会造成IllegalMonitorStateException
					if(hadLocked){
						//lock锁不会自动释放，需要调用unlock()方法释放，否则容易造成死锁
						lock.unlock();
						System.out.println("线程1释放锁！");
					}
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				boolean hadLocked = false;
				try {
					//尝试获取锁，当1秒以后未获得锁则放弃，阻塞线程
					if(lock.tryLock(1,TimeUnit.SECONDS)) {
						hadLocked = true;
						System.out.println("线程2获得了锁");
						for(int i=0;i<1000;i++) {
							counter.lockedsub();
						}
						counter.show();
						condition.signalAll();
					}else {
						//未获得锁阻塞线程
						System.out.println("锁被占有，线程2阻塞");
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if(hadLocked) {
						lock.unlock();
						System.out.println("线程2释放锁！");
					}
				}
			}
		};
		t1.start();
		t2.start();
	}

}
