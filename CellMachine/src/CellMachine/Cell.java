package CellMachine;

import java.awt.Graphics;

public class Cell {
	private boolean alive;
	
	Cell()																//��ʼ��ϸ��״̬
	{
		alive = false;
	}
	
	boolean getCell()													//��ȡϸ��״̬
	{
		return alive;
	}
	
	void live()															//ϸ������
	{
		alive = true;
	}
	
	void die()															//ϸ������
	{
		alive = false;
	}
	
	void draw(Graphics g, int x, int y, int size)						//�������ӻ���ϸ��״̬
	{
		g.drawRect(x, y, size, size);
		if(getCell())
		{
			g.fillRect(x, y, size, size);
		}
	}
}
