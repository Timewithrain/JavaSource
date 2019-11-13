package bean;

public class Student {
	private String num;
	private String name;
	private int age;
	
	public Student(String num,String name,int age) {
		this.num = num;
		this.name = name;
		this.age = age;
	}
	
	public String getNum() {
		return num;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
