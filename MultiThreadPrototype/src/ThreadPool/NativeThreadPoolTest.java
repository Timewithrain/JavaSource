package ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NativeThreadPoolTest {

	public static void main(String[] args) {
		//定义一个阻塞队列作为缓冲区存放作业，传入ThreadPoolExecutor的作业缓冲必须实现BlockingQueue接口
		BlockingQueue<Runnable> jobs = new LinkedBlockingQueue<Runnable>();
		//ThreadPoolExecutor构造方法第一个参数为核心线程数量，第二个参数为当初始线程不足时，线程池内允许的最大线程数
		//第三个参数为线程空闲时长达到n时，回收该线程，第四个参数为第三个参数的时间单位，最后一个参数为需要运行的作业队列
		//线程池具体使用规则参考博客： http://blog.csdn.net/qq_25806863/article/details/71126867
		ThreadPoolExecutor threadpool = new ThreadPoolExecutor(10, 20, 120, TimeUnit.SECONDS, jobs);
		
		Runnable r = new Runnable() {
			public void run() {
				System.out.println("Yes");
			}
		};
		
		//线程池通过execute()方法运行作业
		threadpool.execute(r);
		//初始化关闭命令关闭线程池，关闭时拒绝新的线程请求，若有线程依旧在执行，此方法不予以等待，如果线程池已经关闭则无影响
		threadpool.shutdown();
		try {
			//阻塞调用线程等待所有的线程池内线程在收到请求指令后完成执行或等待超时时关闭线程池，传入参数为等待超时的时间长度和时间单位
			threadpool.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
