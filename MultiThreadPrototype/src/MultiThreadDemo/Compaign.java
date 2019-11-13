package MultiThreadDemo;

public class Compaign {

	public static void main(String[] args) {
		
		Role IronMan = new Role("������",50,300);
		Role Thanos = new Role("���",50,500);
		
		Role SpiderMan = new Role("֩����",25,300);
		Role Mysterio = new Role("���ؿ�",20,200);
		
		//ʹ��Runnable�ӿڱ�ֱ֮�Ӽ̳�Thread�ܹ�ʵ�ֶ�̳��Լ���Դ����Ĺ���
		Battle infinitywar = new Battle(IronMan,Thanos);
		Battle farfromhome = new Battle(SpiderMan,Mysterio);
		
		//start()��������һ���̣߳��߳��ڲ�����run()������run()������һ����ͨ�İ����߳�ҵ���߼��ķ�����������ʵ�ֲ���
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
