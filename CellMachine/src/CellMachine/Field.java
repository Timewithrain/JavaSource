package CellMachine;

import java.util.ArrayList;

public class Field {
	private int length;
	private int width;
	private ArrayList<Cell> member = new ArrayList<Cell>();
	
	Field(int length,int width)											//初始化Field大小
	{
		this.length = length;
		this.width = width;
	}
	
	int getLength()														//获取Field长度
	{
		return length;
	}
	
	int getWidth()														//获取Field宽度
	{
		return width;
	}
	
	Cell getCellFromField(int i,int j)									//从Field的member中获取对应的Cell
	{
		return this.member.get(i*width+j);
	}
	
	void place()														//根据区域大小放置细胞其中活细胞个数为五分之一
	{
		for(int i=0; i<length; i++)
		{
			for(int j=0; j<width; j++)
			{
				Cell c = new Cell();
				if((Math.random()*(10-0+1))<2.5)
				{
					c.live();
				}
				member.add(c);
			}
		}
	}
	
	boolean getCell(int row,int col)									//获取细胞存活状态
	{
		return member.get(row*width+col).getCell();
	}
	
	int getNeighbour(int row,int col)									//获取相邻细胞存活的个数
	{
		int numOfAlive = 0;
		for(int i=row-1; i<=row+1; i++)
		{
			for(int j=col-1; j<=col+1; j++)
			{
				if(i==row&&j==col)										//获取相邻细胞生存数量，因此跳过自身;
				{
					continue;
				}
				if((i<0||j<0)||(i>length-1||j>width-1))					//跳过位于四周边缘的细胞超过field范围的相邻细胞
				{
					continue;
				}
				if(member.get(i*width+j).getCell() == true)
				{
					numOfAlive++;
				}
			}	
		}
		return numOfAlive;
	}
	
	void liveLaw(int row,int col)										//细胞生存规则
	{
		int AliveNum = getNeighbour(row,col);
		System.out.println(row+","+col+":"+AliveNum);
		if(AliveNum < 2 || AliveNum > 3)								//相邻细胞存活数大于3或小于2细胞死亡
		{
			member.get(row*width+col).die();
		}
		if(AliveNum == 3)												//相邻细胞存活数等于3细胞存活；相邻细胞存活数等于2细胞状态不变
		{
			member.get(row*width+col).live();
		}
	}
	
}
