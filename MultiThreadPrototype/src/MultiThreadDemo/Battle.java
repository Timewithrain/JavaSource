package MultiThreadDemo;

public class Battle implements Runnable {
	private Role r1;
	private Role r2;
	
	public Battle(Role r1,Role r2) {
		this.r1 = r1;
		this.r2 = r2;
	}
	
	@Override
	public void run() {
		//r1¹¥»÷r2
		while(r2.isAlive()) {
			r1.attack(r2);
		}
	}

}
