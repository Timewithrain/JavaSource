package CellMachine;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class View extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;								//�����жϰ汾�Ƿ�һ��
	private int CELL_SIZE = 20;
	private Field field;
	
	View(Field field)
	{
		this.field = field;
	}
	
	@Override
	public void paint(Graphics g)													//��ϸ����״����ʾ�ڴ�����
	{
		super.paint(g);																//���ط�����Ҫ���ø���ķ������ݲ���
		for(int i=0; i<field.getLength(); i++)
		{
			for(int j=0; j<field.getWidth(); j++)
			{
				field.getCellFromField(i, j).draw(g, i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize(){											//��pack()�������ݴ�������Ĵ�С
		
		return new Dimension(field.getWidth()*CELL_SIZE+1,field.getLength()*CELL_SIZE+1);
	}
	
}
