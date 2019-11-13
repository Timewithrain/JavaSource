package CellMachine;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class View extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;								//用于判断版本是否一致
	private int CELL_SIZE = 20;
	private Field field;
	
	View(Field field)
	{
		this.field = field;
	}
	
	@Override
	public void paint(Graphics g)													//将细胞的状况显示在窗口内
	{
		super.paint(g);																//重载方法需要调用父类的方法传递参数
		for(int i=0; i<field.getLength(); i++)
		{
			for(int j=0; j<field.getWidth(); j++)
			{
				field.getCellFromField(i, j).draw(g, i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize(){											//向pack()方法传递窗口所需的大小
		
		return new Dimension(field.getWidth()*CELL_SIZE+1,field.getLength()*CELL_SIZE+1);
	}
	
}
