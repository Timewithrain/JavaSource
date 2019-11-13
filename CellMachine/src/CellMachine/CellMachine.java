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
		frame.pack();														//����View�ڵ�getPreferredSize()��������ֵ����Ӧ�������ڴ�С
		frame.setVisible(true);
		
		for(int n=0;n<100;n++)												//����һ�ٴ�ϸ������
		{
			try {
				Thread.sleep(500);											//ÿ������ͣ��0.5��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0; i<f.getLength(); i++)
			{
				for(int j=0; j<f.getWidth(); j++)
				{
					f.liveLaw(i, j);										//������ϸ������ϸ�����������и���
				}
			}
			
			frame.repaint();												//��ʾ�����Ժ�ϸ����״��
		}
	}

}
