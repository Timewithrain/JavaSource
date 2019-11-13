package HungrySnake;

public class Field {
	private int Length;
	private int Hight;
	private static int score = 0;
	private Snake s;
	private Apple a;
	private Wall w;
	
	Field(int length,int hight) {
		Length = length;
		Hight = hight;
		a = new Apple(Hight,Length);
		s = new Snake(Hight,Length);
		w = new Wall(Hight,Length);
	}
	
	int getLength()
	{
		return Length;
	}
	
	int getHight()
	{
		return Hight;
	}
	
	String getDirectionfromSnake() {											//��Snake��ȡ�ߵ��н�����
		return s.getDirection();
	}
	
	int getScore() {															//��ȡ�÷�
		return score;
	}
	
	int getWallsize() {															//��Wall��ȡCell�����Ա��ӡ
		return w.getsize();
	}
	
	int getSnakesize() {														//��Snake��ȡCell�����Ա��ӡ
		return s.getsize();
	}
	
	Cell getCellfromApple() {													//��Apple��ȡCell�Ա��ӡ
		return a.getCell();
	}
	
	Cell getCellfromWall(int i)	{												//��Wall��ȡCell�Ա��ӡ
		return w.getCell(i);
	}
	
	Cell getCellfromSnake(int i) {												//��Snake��ȡCell�Ա��ӡ
		return s.getCell(i);
	}
	
	void turnLeft() {															//����ת
		if(getDirectionfromSnake().equals("North")) {
			s.toWest();
		}
		else if(getDirectionfromSnake().equals("West")) {
			s.toSouth();
		}
		else if(getDirectionfromSnake().equals("East")) {
			s.toNorth();
		}
		else if(getDirectionfromSnake().equals("South")) {
			s.toEast();
		}
	}
	
	void turnRight() {															//����ת
		if(getDirectionfromSnake().equals("North")) {
			s.toEast();
		}
		else if(getDirectionfromSnake().equals("West")) {
			s.toNorth();
		}
		else if(getDirectionfromSnake().equals("East")) {
			s.toSouth();
		}
		else if(getDirectionfromSnake().equals("South")) {
			s.toWest();
		}
	}
	
	String isplay()																//�ж���Ϸ�Ƿ����������ȥ�����÷ָ���ʱ���ص÷�
	{
		String play = "true";
		s.Run();
		if(s.isAlive()) {
			play = "true";
		}else {
			play = "false";
		}
		if(s.isEatApple(a)) {
			score++;
			play = new String("�÷֣�" + score);
			a = new Apple((int)(1+Math.random()*(Hight-1)),(int)(1+Math.random()*(Length-1)));
		}
		return play;
	}
	
}
