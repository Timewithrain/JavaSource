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
		//从连接池中获取一个连接
		Connection c = connectionpool.getConnection();
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将连接释放回连接池
		connectionpool.returnConnection(c);
	}
	
}