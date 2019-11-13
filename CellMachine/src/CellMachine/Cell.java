package CellMachine;

import java.awt.Graphics;

public class Cell {
	private boolean alive;
	
	Cell()																//初始化细胞状态
	{
		alive = false;
	}
	
	boolean getCell()													//获取细胞状态
	{
		return alive;
	}
	
	void live()															//细胞生存
	{
		alive = true;
	}
	
	void die()															//细胞死亡
	{
		alive = false;
	}
	
	void draw(Graphics g, int x, int y, int size)						//画出可视化的细胞状态
	{
		g.drawRect(x, y, size, size);
		if(getCell())
		{
			g.fillRect(x, y, size, size);
		}
	}
}
