package MultiThreadDemo;

public class Role {
	private String name;
	private int damage;
	private int HP;
	
	public Role(String name,int damage,int HP) {
		this.name = name;
		this.damage = damage;
		this.HP = HP;
	}
	
	//被攻击减少血量
	public void damaged(Role r) {
		this.HP -= r.damage; 
		if(HP<=0) {
			HP = 0;
			System.out.println(this.name + "被击杀！");
		}else {
			System.out.println(this.name + "被" + r.name + "攻击，生命值剩余" + this.HP);
		}
	}
	
	//攻击
	public void attack(Role r) {
		r.damaged(this);
	}
	
	//角色是否存活
	public boolean isAlive() {
		return HP>0?true:false;
	}
	
	//受伤减少血量值
	public synchronized void hurt() {
		HP -= 10;
		//当生命值小于300时停止受伤，将线程加入等待队列
		if(HP<300) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//治愈增加血量值
	public synchronized void recover() {
		HP += 10;
		//当生命值等于500时唤醒等待队列中的进程
		if(HP==500) {
			this.notifyAll();
		}
	}
	
	public int getHP() {
		return HP;
	}
	
}
