package MultiThreadDemo;

public class Compaign {

	public static void main(String[] args) {
		
		Role IronMan = new Role("钢铁侠",50,300);
		Role Thanos = new Role("灭霸",50,500);
		
		Role SpiderMan = new Role("蜘蛛侠",25,300);
		Role Mysterio = new Role("神秘客",20,200);
		
		//使用Runnable接口比之直接继承Thread能够实现多继承以及资源共享的功能
		Battle infinitywar = new Battle(IronMan,Thanos);
		Battle farfromhome = new Battle(SpiderMan,Mysterio);
		
		//start()方法启动一个线程，线程内部调用run()方法，run()方法是一个普通的包含线程业务逻辑的方法，本身并不实现并发
		new Thread(infinitywar).start();
		new Thread(farfromhome).start();
		
	}

}

class WorkThread extends Thread{
    
    private Role h1;
    private Role h2;
 
    public WorkThread(Role h1, Role h2){
        this.h1 = h1;
        this.h2 = h2;
    }
 
    public void run(){
        while(!h2.isAlive()){
            h1.attack(h2);
        }
    }
}
