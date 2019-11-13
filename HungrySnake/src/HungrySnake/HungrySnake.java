package HungrySnake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HungrySnake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Field field = new Field(30,30);
		final View view = new View(field);
		
		JPanel playside = new JPanel();
		playside.add(view);
		Font font1 = new Font("΢���ź�",0,20);
		Font font2 = new Font("΢���ź�",0,16);
		JLabel information1 = new JLabel("��·ǧ����,��ֻ��һ��",SwingConstants.CENTER);
		JLabel information2 = new JLabel("��Ϸ���淶,���������",SwingConstants.CENTER);
		JLabel information3 = new JLabel("������A:��ת��D:��ת������ײǽ���ܳ��Լ���",SwingConstants.CENTER);
		JPanel introduction = new JPanel();
		introduction.setLayout(new GridLayout(3,1));
		information1.setFont(font1);
		information2.setFont(font1);
		information3.setFont(font2);
		information3.setForeground(Color.RED);
		introduction.add(information1);
		introduction.add(information2);
		introduction.add(information3);
		JLabel score = new JLabel("�÷֣�0",SwingConstants.RIGHT);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("HungrySnake");
		frame.add(playside,BorderLayout.CENTER);
		frame.add(introduction,BorderLayout.NORTH);
		frame.add(score,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(new KeyAdapter() {										//������̼����Ի�ȡת����Ϣ
			public void keyTyped(KeyEvent e){
				switch(e.getKeyChar()) {
					case KeyEvent.VK_A:{
						field.turnLeft();
					}break;
					case KeyEvent.VK_D:{
						field.turnRight();
					}break;
				}
			}
		});
		
		for(int i=0; i<1000; i++) {													//ѭ��������Ϸ
			try {
				Thread.sleep(200);													//ÿ��ˢ��ҳ����0.2��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String PlayOrNot = field.isplay();
			if(PlayOrNot.equals("false"))											//�ж��Ƿ����������Ϸ��Ϊfalseʱ�˳���Ϸ
			{
				break;
			}else if(PlayOrNot.equals("true")) {									//����ֵΪtrue������Ϸ
				
			}else {																	//����ֵΪ��������ַ���ʱ����÷�
				score.setText(PlayOrNot);;
			}
			frame.repaint();														//���´�ӡҳ��
		}
		
		
	}
}
