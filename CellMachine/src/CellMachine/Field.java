package CellMachine;

import java.util.ArrayList;

public class Field {
	private int length;
	private int width;
	private ArrayList<Cell> member = new ArrayList<Cell>();
	
	Field(int length,int width)											//��ʼ��Field��С
	{
		this.length = length;
		this.width = width;
	}
	
	int getLength()														//��ȡField����
	{
		return length;
	}
	
	int getWidth()														//��ȡField���
	{
		return width;
	}
	
	Cell getCellFromField(int i,int j)									//��Field��member�л�ȡ��Ӧ��Cell
	{
		return this.member.get(i*width+j);
	}
	
	void place()														//���������С����ϸ�����л�ϸ������Ϊ���֮һ
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
	
	boolean getCell(int row,int col)									//��ȡϸ�����״̬
	{
		return member.get(row*width+col).getCell();
	}
	
	int getNeighbour(int row,int col)									//��ȡ����ϸ�����ĸ���
	{
		int numOfAlive = 0;
		for(int i=row-1; i<=row+1; i++)
		{
			for(int j=col-1; j<=col+1; j++)
			{
				if(i==row&&j==col)										//��ȡ����ϸ�����������������������;
				{
					continue;
				}
				if((i<0||j<0)||(i>length-1||j>width-1))					//����λ�����ܱ�Ե��ϸ������field��Χ������ϸ��
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
	
	void liveLaw(int row,int col)										//ϸ���������
	{
		int AliveNum = getNeighbour(row,col);
		System.out.println(row+","+col+":"+AliveNum);
		if(AliveNum < 2 || AliveNum > 3)								//����ϸ�����������3��С��2ϸ������
		{
			member.get(row*width+col).die();
		}
		if(AliveNum == 3)												//����ϸ�����������3ϸ��������ϸ�����������2ϸ��״̬����
		{
			member.get(row*width+col).live();
		}
	}
	
}
