package LambdaProtptype;

import java.util.ArrayList;

public class LambdaProtptype {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Student> a = new ArrayList<Student>();
		for(int i=0;i<5;i++) {
			a.add(new Student("00"+(i+1),""+(i+1)+(i+1)+(i+1),(i+1)));
		}
		//�����෽ʽʵ�ֹ�����
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
		
		//�������෽ʽʵ��,ͨ����ͷ�������ڲ�����,ʡ��filter�������Լ����캯����
//		StudentFilter sf = (Student s)-> {if(s.score>3)return s; else return null;};
//		for(int i=0;i<a.size();i++) {
//			scorefilter(a.get(i),sf);
//		}
		
		//��һ���򻯽���Ҫ������ʽ
		for(int i=0;i<a.size();i++) {
			scorefilter(a.get(i),s-> {if(s.score>3)return s; else return null;});
		}
		
	}
	
	//�ɼ�ɸѡ
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
