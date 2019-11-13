package ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionPool {
	int size;
	ArrayList<Connection> cp = new ArrayList<Connection>();
	
	public ConnectionPool(int size) {
		this.size = size;
		init();
	}
	
	//初始化size个数据库连接填满连接池
	void init() {
		String resource = "jdbc:mysql://localhost:3306/watermelon?characterEncoding=UTF-8";
		String user = "root";
		String password = "1050364782";
		Connection c = null;
		try {
			for(int i=0;i<size;i++) {
				Class.forName("com.mysql.jdbc.Driver");
				c = DriverManager.getConnection(resource,user,password);
				cp.add(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取连接，同时刻仅能有一个线程访问以保证线程安全性
	public synchronized Connection getConnection() {
		while(cp.isEmpty()) {
			try {
				//当连接池中没有连接可用时进程进入等待状态并释放其他所有资源，直到有进程释放连接并发出消息
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//当连接池不为空时返回一个连接
		return cp.remove(0);
	}
	
	//归还连接
	public synchronized void returnConnection(Connection c) {
		cp.add(c);
		//归还连接后告知所有在等待的线程
		this.notifyAll();
	}
	
	//连接池测试
	public static void main(String[] args) {
		//传统方式连接数据库插入200条数据
		Statement s = null;
		Connection c = null;
		try {
			long start = System.currentTimeMillis();
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/watermelon?characterEncoding=UTF-8", "root", "1050364782");
			s = c.createStatement();
			for(int i=0;i<500;i++) {
				String sql = "insert into t values(" + (i+1) + ")";
				s.execute(sql);
			}
			long end = System.currentTimeMillis();
			System.out.println("传统方式时间：" + (end - start));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				s.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//连接池方式连接数据库插入200条数据
		ConnectionPool cp = new ConnectionPool(10);
		long start = System.currentTimeMillis();
		for(int i=0;i<500;i++) {
			String sql = "insert into s values(" + (i+1) + ")";
			new SqlThread(cp,sql).start();
		}
		long end = System.currentTimeMillis();
		System.out.println("连接池方式时间：" + (end - start));
	}
	
}

