package HungrySnake;

import java.util.ArrayList;

public class Snake {
	private int fieldLength;													//获取场地大小
	private int fieldHight;
	private boolean alive;														//蛇是否存活
	private String Direction = new String("North");								//初始化蛇的前进方向
	private ArrayList<Cell> Snake = new ArrayList<Cell>();						//蛇的身体
	
	Snake(int hight,int length) {												//初始化蛇的长度为3，位置在窗口中央
		fieldLength = length;
		fieldHight = hight;
		alive = true;
		int startX = length/2;													//蛇头起始位置
		int startY = hight/2;
		Cell c;
		c = new Cell(startX,startY);											//建立3个细胞作为蛇的身体
		c.toSnake();
		Snake.add(c);
		c = new Cell(startX,startY+1);
		c.toSnake();
		Snake.add(c);
		c = new Cell(startX,startY+2);
		c.toSnake();
		Snake.add(c);
	}
	
	String getDirection() {														//获取Snake的前进方向
		return Direction;
	}
	
	int getsize() {																//获取Snake的长度
		return Snake.size();
	}
	
	Cell getCell(int i) {														//获取Snake的Cell
		return Snake.get(i);
	}
	
	void Run()																	//蛇的移动
	{
		for(int i=Snake.size()-1; i>0; i--) {									//蛇身前移
			Snake.get(i).changeX(Snake.get(i-1).getX());;
			Snake.get(i).changeY(Snake.get(i-1).getY());;
		}
		if(Direction.equals("North")) {											//蛇头根据方向移动
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
	
	void toNorth() {															//改变蛇的移动方向为向北
		Direction = "North";
	}
	
	void toEast() {																//改变蛇的移动方向为向东
		Direction = "East";
	}
	
	void toWest() {																//改变蛇的移动方向为向西
		Direction = "West";
	}
	
	void toSouth() {															//改变蛇的移动方向为向南
		Direction = "South";
	}
	
	boolean isEatApple(Apple apple) {											//判断是否吃到水果
		boolean isEat = false;
		if(Snake.get(0).getX()==apple.getXfromApple()&&Snake.get(0).getY()==apple.getYfromApple()) {
			Snake.add(0, apple.Eaten());
			isEat = true;
		}
		return isEat;
	}
	
	boolean isHitWall() {														//判断是否撞到墙
		boolean isHit = false;
		if(Snake.get(0).getX()>=fieldHight-1||Snake.get(0).getX()<=0||Snake.get(0).getY()>=fieldLength-1||Snake.get(0).getY()<=0) {
			isHit = true;
		}
		return isHit;
	}
	
	boolean isBiteSelf() {														//判断是否咬到自己
		boolean isBite = false;
		for(int i=1; i<Snake.size()-1; i++) {
			if(Snake.get(0).getX()==Snake.get(i).getX()&&Snake.get(0).getY()==Snake.get(i).getY()) {
				isBite = true;
			}
		}
		return isBite;
	}
	
	boolean isAlive() {															//判断蛇是否存活
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
