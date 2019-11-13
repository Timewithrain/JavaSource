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
				//��¼�Ƿ��ȡ����
				boolean hadLocked = false;
				try {
					//�߳̿�ʼʱ���Ի�ȡ��������tryLock()�����д���ʱ���������ʱ���ٳ��Ի�ȡ�����˻��ƿɱ����������
					if(lock.tryLock(1,TimeUnit.SECONDS)) {
						//��ȡ��hadLock��Ϊtrue
						hadLocked = true;
						System.out.println("�߳�1��ȡ����");
						for(int i=0;i<1000;i++) {
							counter.lockedadd();
						}
						counter.show();
						//���������ʱ�����������ȴ��Ľ���
						condition.signalAll();
					}else {
						//����ռ��ʱ�߳�����
						System.out.println("����ռ�У��߳�1����");
						//ͨ��Condition�����await()���������Լ�
						condition.await();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					//����ȡ�������ͷ�������������IllegalMonitorStateException
					if(hadLocked){
						//lock�������Զ��ͷţ���Ҫ����unlock()�����ͷţ����������������
						lock.unlock();
						System.out.println("�߳�1�ͷ�����");
					}
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				boolean hadLocked = false;
				try {
					//���Ի�ȡ������1���Ժ�δ�����������������߳�
					if(lock.tryLock(1,TimeUnit.SECONDS)) {
						hadLocked = true;
						System.out.println("�߳�2�������");
						for(int i=0;i<1000;i++) {
							counter.lockedsub();
						}
						counter.show();
						condition.signalAll();
					}else {
						//δ����������߳�
						System.out.println("����ռ�У��߳�2����");
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if(hadLocked) {
						lock.unlock();
						System.out.println("�߳�2�ͷ�����");
					}
				}
			}
		};
		t1.start();
		t2.start();
	}

}
