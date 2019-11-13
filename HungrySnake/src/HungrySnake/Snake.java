package HungrySnake;

import java.util.ArrayList;

public class Snake {
	private int fieldLength;													//��ȡ���ش�С
	private int fieldHight;
	private boolean alive;														//���Ƿ���
	private String Direction = new String("North");								//��ʼ���ߵ�ǰ������
	private ArrayList<Cell> Snake = new ArrayList<Cell>();						//�ߵ�����
	
	Snake(int hight,int length) {												//��ʼ���ߵĳ���Ϊ3��λ���ڴ�������
		fieldLength = length;
		fieldHight = hight;
		alive = true;
		int startX = length/2;													//��ͷ��ʼλ��
		int startY = hight/2;
		Cell c;
		c = new Cell(startX,startY);											//����3��ϸ����Ϊ�ߵ�����
		c.toSnake();
		Snake.add(c);
		c = new Cell(startX,startY+1);
		c.toSnake();
		Snake.add(c);
		c = new Cell(startX,startY+2);
		c.toSnake();
		Snake.add(c);
	}
	
	String getDirection() {														//��ȡSnake��ǰ������
		return Direction;
	}
	
	int getsize() {																//��ȡSnake�ĳ���
		return Snake.size();
	}
	
	Cell getCell(int i) {														//��ȡSnake��Cell
		return Snake.get(i);
	}
	
	void Run()																	//�ߵ��ƶ�
	{
		for(int i=Snake.size()-1; i>0; i--) {									//����ǰ��
			Snake.get(i).changeX(Snake.get(i-1).getX());;
			Snake.get(i).changeY(Snake.get(i-1).getY());;
		}
		if(Direction.equals("North")) {											//��ͷ���ݷ����ƶ�
			Snake.get(0).moveN();
		}
		if(Direction.equals("West")) {
			Snake.get(0).moveW();
		}
		if(Direction.equals("East")) {
			Snake.get(0).moveE();
		}
		if(Direction.equals("South")) {
			Snake.get(0).moveS();
		}
	}
	
	void toNorth() {															//�ı��ߵ��ƶ�����Ϊ��
		Direction = "North";
	}
	
	void toEast() {																//�ı��ߵ��ƶ�����Ϊ��
		Direction = "East";
	}
	
	void toWest() {																//�ı��ߵ��ƶ�����Ϊ����
		Direction = "West";
	}
	
	void toSouth() {															//�ı��ߵ��ƶ�����Ϊ����
		Direction = "South";
	}
	
	boolean isEatApple(Apple apple) {											//�ж��Ƿ�Ե�ˮ��
		boolean isEat = false;
		if(Snake.get(0).getX()==apple.getXfromApple()&&Snake.get(0).getY()==apple.getYfromApple()) {
			Snake.add(0, apple.Eaten());
			isEat = true;
		}
		return isEat;
	}
	
	boolean isHitWall() {														//�ж��Ƿ�ײ��ǽ
		boolean isHit = false;
		if(Snake.get(0).getX()>=fieldHight-1||Snake.get(0).getX()<=0||Snake.get(0).getY()>=fieldLength-1||Snake.get(0).getY()<=0) {
			isHit = true;
		}
		return isHit;
	}
	
	boolean isBiteSelf() {														//�ж��Ƿ�ҧ���Լ�
		boolean isBite = false;
		for(int i=1; i<Snake.size()-1; i++) {
			if(Snake.get(0).getX()==Snake.get(i).getX()&&Snake.get(0).getY()==Snake.get(i).getY()) {
				isBite = true;
			}
		}
		return isBite;
	}
	
	boolean isAlive() {															//�ж����Ƿ���
		if(isHitWall()) {
			alive = false;
		}
		else if(isBiteSelf()) {
			alive = false;
		}
		else {
			alive = true;
		}
//		System.out.println(alive);
		return alive;
	}
	
	
}
