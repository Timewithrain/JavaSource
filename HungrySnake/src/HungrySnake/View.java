package HungrySnake;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class View extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int CELL_SIZE = 20;
	private Field field;
	
	View(Field field) {
		this.field = field;
	}
	
	@Override
	public void paint(Graphics g) {												//打印出图像
		super.paint(g);
		for(int i=0; i<field.getWallsize(); i++) {								//画出Wall
			field.getCellfromWall(i).draw(g, CELL_SIZE);
		}
		for(int i=0; i<field.getSnakesize(); i++) {								//画出Snake
			field.getCellfromSnake(i).draw(g, CELL_SIZE);
		}
		field.getCellfromApple().draw(g, CELL_SIZE);							//画出Apple
	}
	
	@Override
	public Dimension getPreferredSize() {										//为pack()提供调整窗口大小的依据
		return new Dimension(field.getHight()*CELL_SIZE,field.getLength()*CELL_SIZE);
	}
	
}
