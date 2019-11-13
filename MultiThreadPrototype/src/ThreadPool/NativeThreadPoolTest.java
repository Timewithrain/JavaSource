package ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NativeThreadPoolTest {

	public static void main(String[] args) {
		//����һ������������Ϊ�����������ҵ������ThreadPoolExecutor����ҵ�������ʵ��BlockingQueue�ӿ�
		BlockingQueue<Runnable> jobs = new LinkedBlockingQueue<Runnable>();
		//ThreadPoolExecutor���췽����һ������Ϊ�����߳��������ڶ�������Ϊ����ʼ�̲߳���ʱ���̳߳������������߳���
		//����������Ϊ�߳̿���ʱ���ﵽnʱ�����ո��̣߳����ĸ�����Ϊ������������ʱ�䵥λ�����һ������Ϊ��Ҫ���е���ҵ����
		//�̳߳ؾ���ʹ�ù���ο����ͣ� http://blog.csdn.net/qq_25806863/article/details/71126867
		ThreadPoolExecutor threadpool = new ThreadPoolExecutor(10, 20, 120, TimeUnit.SECONDS, jobs);
		
		Runnable r = new Runnable() {
			public void run() {
				System.out.println("Yes");
			}
		};
		
		//�̳߳�ͨ��execute()����������ҵ
		threadpool.execute(r);
		//��ʼ���ر�����ر��̳߳أ��ر�ʱ�ܾ��µ��߳����������߳�������ִ�У��˷��������Եȴ�������̳߳��Ѿ��ر�����Ӱ��
		threadpool.shutdown();
		try {
			//���������̵߳ȴ����е��̳߳����߳����յ�����ָ������ִ�л�ȴ���ʱʱ�ر��̳߳أ��������Ϊ�ȴ���ʱ��ʱ�䳤�Ⱥ�ʱ�䵥λ
			threadpool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
