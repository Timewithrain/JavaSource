package CellMachine;

import javax.swing.JFrame;

public class CellMachine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Field f = new Field(30,30);
		
		f.place();
		
		View v = new View(f);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("CellMachine");
//		frame.setLocationRelativeTo(null);
		frame.add(v);
		frame.pack();														//根据View内的getPreferredSize()方法传的值自适应调整窗口大小
		frame.setVisible(true);
		
		for(int n=0;n<100;n++)												//进行一百次细胞周期
		{
			try {
				Thread.sleep(500);											//每个周期停顿0.5秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0; i<f.getLength(); i++)
			{
				for(int j=0; j<f.getWidth(); j++)
				{
					f.liveLaw(i, j);										//将所有细胞按照细胞生存规则进行更新
				}
			}
			
			frame.repaint();												//显示更新以后细胞的状况
		}
	}

}
