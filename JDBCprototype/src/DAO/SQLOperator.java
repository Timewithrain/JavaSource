package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLOperator {
	Connection connection;
	PreparedStatement statement;
	
	//��ȡ���ݿ�����
	public SQLOperator(Connection c) {
		this.connection = c;
		try {
			//�ر��Զ��ύ���ֶ��ύ��ȷ�������������
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ȡѧ��������ѧ����ѧ�Ż�ȡѧ������
	public Student get(String num) {
		int age = 0;
		Student s = null;
		String name = null;
		String sql = "select * from student where sno=?";
		//try-with-resource���Զ��ر�statement
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, num);
			ResultSet rs = statement.executeQuery();
			//���ҵ�ѧ��ʱ����ѧ��,���򷵻�null
			if(rs.next()) {
				name = rs.getString("sname");
				age = rs.getInt("sage");
				s = new Student(num,name,age);
			}else {
				System.err.println("NotFoundStudent");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	//���룬����һ��student���󣬽�����ת��Ϊһ����¼���뵽���ݿ���
	public void add(Student s) {
		String sql = "insert into student values(?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, s.num);
			statement.setString(2, s.name);
			statement.setInt(3, s.age);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�޸ģ�����student��ѧ����Ϊkey���ҵ����޸ĸ�ѧ������������
	public void update(Student s) {
		String sql = "update student set sname=? , sage=? where sno =?";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, s.name);
			statement.setInt(2, s.age);
			statement.setString(3, s.num);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ɾ��������ѧ��ѧ��ɾ�����ݿ��ڶ�Ӧ��¼
	public void delete(Student s) {
		String sql = "delete from student where sno=?";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, s.num);
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ���ѧ��������
	public int totalNumber() {
		int count = 0;
		String sql = "select count(*) from student";
		//try-with-resource���Զ��ر�statement
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	//�����ݿ��е�����ת��Ϊ��������
	public ArrayList<Student> toList(int start,int length) {
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "select * from student limit ?,?";
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//���ݿ������ɺ󷵻����ݿ�����
	public Connection returnConnection() {
		return connection;
	}
	
	//�ر����ݿ�����
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����SQLOperator
	public static void main(String[] args) {
		//��ʼ������
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/watermelon?characterEncoding=UTF-8", "root", "1050364782");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SQLOperator oper = new SQLOperator(c);
//		Student s = new Student("111","������",21);
		//add����
//		oper.add(s);
		//update����
//		oper.update(new Student("001","����",18));
		//get����
//		Student a = oper.get("001");
//		System.out.println(a.num + " " + a.name + " " + a.age);
		//delete����
//		oper.delete(s);
		//totalNumber����
//		System.out.println(oper.totalNumber());
		//tolist����
		ArrayList<Student> array = oper.toList(0, 12);
		for(int i=0;i<array.size();i++) {
			System.out.println(array.get(i).num + " " + array.get(i).name + " " + array.get(i).age);
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

class Student{
	public String num;
	public String name;
	public int age;
	
	public Student(String num,String name,int age) {
		this.num = num;
		this.name = name;
		this.age = age;
	}
}