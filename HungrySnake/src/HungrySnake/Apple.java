package HungrySnake;

public class Apple {
	private Cell apple;
	
	Apple(int Hight, int Length){
		apple = new Cell((int)(2+Math.random()*(Hight-2)),(int)(2+Math.random()*(Length-2)));
		apple.toApple();
	}
	
	int getXfromApple() {														//��ȡApple��X����
		return apple.getX();
	}
	
	int getYfromApple() {														//��ȡApple��Y����
		return apple.getY();
	}
	
	Cell getCell() {															//��ȡCell���ڴ�ӡ
		return apple;
	}
	
	Cell Eaten() {																//����ʱ�������Cellת��Ϊ����
		apple.toSnake();
		return apple;
	}
}
