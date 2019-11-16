package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDAO {
	Connection connection;
	PreparedStatement statement;
	
	//��ʼ�����ݿ��������������ݿ�����
	public StudentDAO() { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ�����
	public void getConnection() throws SQLException {
		//�������ݿ�����
		connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/watermelon?characterEncoding=UTF-8", "root", "1050364782");
		connection.setAutoCommit(false);
	}
	
	public void add(Student s) {
		String sql = "insert into student values(?,?,?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, s.getNum());
			statement.setString(2, s.getName());
			statement.setInt(3, s.getAge());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Student s) {
		String sql = "update student set sname=? , sage=? where sno =?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, s.getName());
			statement.setInt(2, s.getAge());
			statement.setString(3, s.getNum());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Student s) {
		String sql = "delete from student where sno=?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, s.getNum());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Student get(String num) {
		int age = 0;
		String name = null;
		String sql = "select * from student where sno=?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, num);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				name = rs.getString("sname");
				age = rs.getInt("sage");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Student(num,name,age);
	}
	
	//��list�����޲�ʱ��Ĭ�ϴӵ�0����¼��ʼ�������ű�
	public List<Student> list() {
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String num = rs.getString("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				list.add(new Student(num,name,age));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//��start��ʼ����length����¼ת��ΪStudent�������list�У�����list
	public List<Student> list(int start,int length) {
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "select * from student limit ?,?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1,start);
			statement.setInt(2,length);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String num = rs.getString("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				list.add(new Student(num,name,age));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//��ȡ���ݿ��м�¼������
	public int getTotalNum() {
		int total = 0;
		String sql = "select count(*) from student";
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	//�ر�sql�������ݿ�����
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
