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
	
	//��ʼ��size�����ݿ������������ӳ�
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
	
	//��ȡ���ӣ�ͬʱ�̽�����һ���̷߳����Ա�֤�̰߳�ȫ��
	public synchronized Connection getConnection() {
		while(cp.isEmpty()) {
			try {
				//�����ӳ���û�����ӿ���ʱ���̽���ȴ�״̬���ͷ�����������Դ��ֱ���н����ͷ����Ӳ�������Ϣ
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//�����ӳز�Ϊ��ʱ����һ������
		return cp.remove(0);
	}
	
	//�黹����
	public synchronized void returnConnection(Connection c) {
		cp.add(c);
		//�黹���Ӻ��֪�����ڵȴ����߳�
		this.notifyAll();
	}
	
	//���ӳز���
	public static void main(String[] args) {
		//��ͳ��ʽ�������ݿ����200������
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
			System.out.println("��ͳ��ʽʱ�䣺" + (end - start));
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
		
		//���ӳط�ʽ�������ݿ����200������
		ConnectionPool cp = new ConnectionPool(10);
		long start = System.currentTimeMillis();
		for(int i=0;i<500;i++) {
			String sql = "insert into s values(" + (i+1) + ")";
			new SqlThread(cp,sql).start();
		}
		long end = System.currentTimeMillis();
		System.out.println("���ӳط�ʽʱ�䣺" + (end - start));
	}
	
}

