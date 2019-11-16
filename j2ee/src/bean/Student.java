package bean;

public class Student {
	private String num;
	private String name;
	private int age;
	
	/*启用JSON时需要有无参构造函数*/
	public Student() {
		this.num = null;
		this.name = null;
		this.age = 0;
	}
	
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
	
	public String toString() {
        		return "Student [num=" + num + ", name=" + name + ", age=" + age + "]";
    	}
	
}
