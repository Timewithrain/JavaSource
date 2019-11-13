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
	
	String getDirectionfromSnake() {											//从Snake获取蛇的行进方向
		return s.getDirection();
	}
	
	int getScore() {															//获取得分
		return score;
	}
	
	int getWallsize() {															//从Wall获取Cell个数以便打印
		return w.getsize();
	}
	
	int getSnakesize() {														//从Snake获取Cell个数以便打印
		return s.getsize();
	}
	
	Cell getCellfromApple() {													//从Apple获取Cell以便打印
		return a.getCell();
	}
	
	Cell getCellfromWall(int i)	{												//从Wall获取Cell以便打印
		return w.getCell(i);
	}
	
	Cell getCellfromSnake(int i) {												//从Snake获取Cell以便打印
		return s.getCell(i);
	}
	
	void turnLeft() {															//向左转
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
	
	void turnRight() {															//向右转
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
	
	String isplay()																//判断游戏是否继续进行下去，当得分更新时返回得分
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
			play = new String("得分：" + score);
			a = new Apple((int)(1+Math.random()*(Hight-1)),(int)(1+Math.random()*(Length-1)));
		}
		return play;
	}
	
}
