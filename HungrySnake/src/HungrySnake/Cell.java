package HungrySnake;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private int x;
	private int y;
	private boolean isSnake;													//�Ƿ�Ϊ�ߵ����壬�Ա��ӡʱ��ɫ
	private boolean isApple;													//�Ƿ�Ϊƻ�����Ա��ӡʱ��ɫ
	private boolean isWall;
	
	Cell(int x,int y) {
		this.x = x;
		this.y = y;
		isSnake = false;
		isApple = false;
		isWall = false;
	}
	
	void toSnake() {															//����Ϊ�ߵ�����
		isSnake = true;
		isApple = false;
	}
	
	void toApple() {															//����Ϊƻ��
		isApple = true;
	}
	
	void toWall() {																//����Ϊǽ
		isWall = true;
	}
	
	boolean isSnake() {															//��ȡ�Ƿ�Ϊ�ߵ��������Ϣ
		return isSnake;
	}
	
	boolean isApple() {															//��ȡ�Ƿ�Ϊƻ������Ϣ
		return isApple;
	}
	
	boolean isWall() {															//��ȡ�Ƿ�Ϊǽ����Ϣ
		return isWall;
	}
	
	void moveE() {																//���ƶ�
		x++;
	}
	
	void moveN() {																//���ƶ�
		y--;
	}
	
	void moveW() {																//�����ƶ�
		x--;
	}
	
	void moveS() {																//�����ƶ�
		y++;
	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	void changeX(int x) {														//�ı�Cell��X����
		this.x = x;
	}
	
	void changeY(int y) {														//�ı�Cell��Y����
		this.y = y;
	}
	
	void draw(Graphics g,int CELL_SIZE){										//����Cell
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
