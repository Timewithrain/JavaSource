package HungrySnake;

import java.util.ArrayList;

public class Wall {
	private ArrayList<Cell> Wall = new ArrayList<Cell>();
	
	Wall(int Hight,int Length) {													//��ʼ��Χǽ������Field�Ĵ�С������ǽ
		Cell c;
		for(int i=0; i<Hight; i++) {
			if(i==0||i==Hight-1) {
				for(int j=0;j<Length;j++) {
					c = new Cell(i,j);
					c.toWall();
					Wall.add(c);
				}
			}
			else {
				c = new Cell(i,0);
				c.toWall();
				Wall.add(c);
				c = new Cell(i,Length-1);
				c.toWall();
				Wall.add(c);
			}
		}
	}
	
	int getsize() {																	//��ȡWall������
		return Wall.size();
	}
	
	Cell getCell(int i) {															//��ȡWall��Cell
		return Wall.get(i);
	}
	
}