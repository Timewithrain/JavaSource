package LambdaProtptype;

import java.util.ArrayList;

public class LambdaProtptype {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Student> a = new ArrayList<Student>();
		for(int i=0;i<5;i++) {
			a.add(new Student("00"+(i+1),""+(i+1)+(i+1)+(i+1),(i+1)));
		}
		//匿名类方式实现过滤器
//		StudentFilter sf = new StudentFilter() {
//			public Student filter(Student s) {
//				if(s.score>3) {
//					return s;
//				}else {
//					return null;
//				}
//			}
//		};
//		for(int i=0;i<a.size();i++) {
//			socrefilter(a.get(i),sf);
//		}
		
		//简化匿名类方式实现,通过箭头访问类内部方法,省略filter方法名以及构造函数名
//		StudentFilter sf = (Student s)-> {if(s.score>3)return s; else return null;};
//		for(int i=0;i<a.size();i++) {
//			scorefilter(a.get(i),sf);
//		}
		
		//进一步简化仅需要传入表达式
		for(int i=0;i<a.size();i++) {
			scorefilter(a.get(i),s-> {if(s.score>3)return s; else return null;});
		}
		
	}
	
	//成绩筛选
	public static void scorefilter(Student s,StudentFilter sf) {
		if(sf.filter(s)!=null) {
			System.out.println(s.sno + " " + s.name + " " + s.score);
		}
	}
	
}

class Student{
	String sno;
	String name;
	int score;
	
	public Student(String sno,String name ,int score){
		this.sno = sno;
		this.name = name;
		this.score = score;
	}
	
}

interface StudentFilter{
	public Student filter(Student s);
}
