package HungrySnake;

import java.util.ArrayList;

public class Wall {
	private ArrayList<Cell> Wall = new ArrayList<Cell>();
	
	Wall(int Hight,int Length) {													//初始化围墙，根据Field的大小建立城墙
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
	
	int getsize() {																	//获取Wall的数量
		return Wall.size();
	}
	
	Cell getCell(int i) {															//获取Wall的Cell
		return Wall.get(i);
	}
	
}