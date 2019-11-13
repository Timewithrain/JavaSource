package ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class SqlThread extends Thread{
	ConnectionPool connectionpool;
	String sql;
	
	public SqlThread(ConnectionPool cp,String sql) {
		this.connectionpool = cp;
		this.sql = sql;
	}
	
	public void run() {
		//�����ӳ��л�ȡһ������
		Connection c = connectionpool.getConnection();
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�������ͷŻ����ӳ�
		connectionpool.returnConnection(c);
	}
	
}