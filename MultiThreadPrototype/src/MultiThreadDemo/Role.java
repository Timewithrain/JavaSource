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
	
	//����������Ѫ��
	public void damaged(Role r) {
		this.HP -= r.damage; 
		if(HP<=0) {
			HP = 0;
			System.out.println(this.name + "����ɱ��");
		}else {
			System.out.println(this.name + "��" + r.name + "����������ֵʣ��" + this.HP);
		}
	}
	
	//����
	public void attack(Role r) {
		r.damaged(this);
	}
	
	//��ɫ�Ƿ���
	public boolean isAlive() {
		return HP>0?true:false;
	}
	
	//���˼���Ѫ��ֵ
	public synchronized void hurt() {
		HP -= 10;
		//������ֵС��300ʱֹͣ���ˣ����̼߳���ȴ�����
		if(HP<300) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//��������Ѫ��ֵ
	public synchronized void recover() {
		HP += 10;
		//������ֵ����500ʱ���ѵȴ������еĽ���
		if(HP==500) {
			this.notifyAll();
		}
	}
	
	public int getHP() {
		return HP;
	}
	
}
