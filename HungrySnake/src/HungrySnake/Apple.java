package HungrySnake;

public class Apple {
	private Cell apple;
	
	Apple(int Hight, int Length){
		apple = new Cell((int)(2+Math.random()*(Hight-2)),(int)(2+Math.random()*(Length-2)));
		apple.toApple();
	}
	
	int getXfromApple() {														//获取Apple的X坐标
		return apple.getX();
	}
	
	int getYfromApple() {														//获取Apple的Y坐标
		return apple.getY();
	}
	
	Cell getCell() {															//获取Cell用于打印
		return apple;
	}
	
	Cell Eaten() {																//被吃时返回这个Cell转化为蛇身
		apple.toSnake();
		return apple;
	}
}
