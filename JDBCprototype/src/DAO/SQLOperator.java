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
	
	//获取数据库连接
	public SQLOperator(Connection c) {
		this.connection = c;
		try {
			//关闭自动提交，手动提交以确保事务的完整性
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取学生，依照学生的学号获取学生对象
	public Student get(String num) {
		int age = 0;
		Student s = null;
		String name = null;
		String sql = "select * from student where sno=?";
		//try-with-resource可自动关闭statement
		try (PreparedStatement statement = connection.prepareStatement(sql);) {
			statement.setString(1, num);
			ResultSet rs = statement.executeQuery();
			//当找到学生时返回学生,否则返回null
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
	
	//插入，传入一个student对象，将对象转换为一条记录插入到数据库中
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
	
	//修改，依照student的学号作为key，找到并修改该学生的其他属性
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
	
	//删除，依照学生学号删除数据库内对应记录
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
	
	//获取数据库中学生的总数
	public int totalNumber() {
		int count = 0;
		String sql = "select count(*) from student";
		//try-with-resource可自动关闭statement
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
	
	//将数据库中的数据转换为对象数组
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
	
	//数据库操作完成后返回数据库连接
	public Connection returnConnection() {
		return connection;
	}
	
	//关闭数据库连接
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试SQLOperator
	public static void main(String[] args) {
		//初始化连接
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
//		Student s = new Student("111","啦啦啦",21);
		//add测试
//		oper.add(s);
		//update测试
//		oper.update(new Student("001","子鼠",18));
		//get测试
//		Student a = oper.get("001");
//		System.out.println(a.num + " " + a.name + " " + a.age);
		//delete测试
//		oper.delete(s);
		//totalNumber测试
//		System.out.println(oper.totalNumber());
		//tolist测试
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