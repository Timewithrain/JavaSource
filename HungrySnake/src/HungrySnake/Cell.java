package HungrySnake;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private int x;
	private int y;
	private boolean isSnake;													//是否为蛇的身体，以便打印时着色
	private boolean isApple;													//是否为苹果，以便打印时着色
	private boolean isWall;
	
	Cell(int x,int y) {
		this.x = x;
		this.y = y;
		isSnake = false;
		isApple = false;
		isWall = false;
	}
	
	void toSnake() {															//设置为蛇的身体
		isSnake = true;
		isApple = false;
	}
	
	void toApple() {															//设置为苹果
		isApple = true;
	}
	
	void toWall() {																//设置为墙
		isWall = true;
	}
	
	boolean isSnake() {															//获取是否为蛇的身体的信息
		return isSnake;
	}
	
	boolean isApple() {															//获取是否为苹果的信息
		return isApple;
	}
	
	boolean isWall() {															//获取是否为墙的信息
		return isWall;
	}
	
	void moveE() {																//向东移动
		x++;
	}
	
	void moveN() {																//向北移动
		y--;
	}
	
	void moveW() {																//向西移动
		x--;
	}
	
	void moveS() {																//向南移动
		y++;
	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	void changeX(int x) {														//改变Cell的X坐标
		this.x = x;
	}
	
	void changeY(int y) {														//改变Cell的Y坐标
		this.y = y;
	}
	
	void draw(Graphics g,int CELL_SIZE){										//画出Cell
		g.drawRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		if(isSnake()){
			g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
		if(isApple()) {
			g.setColor(Color.PINK);
			g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			g.setColor(Color.BLACK);
		}
		if(isWall()) {
			g.setColor(Color.lightGray);
			g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			g.setColor(Color.BLACK);
		}
	}
	
	
}
