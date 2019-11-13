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
		Font font1 = new Font("微软雅黑",0,20);
		Font font2 = new Font("微软雅黑",0,16);
		JLabel information1 = new JLabel("道路千万条,蛇只有一条",SwingConstants.CENTER);
		JLabel information2 = new JLabel("游戏不规范,玩家两行泪",SwingConstants.CENTER);
		JLabel information3 = new JLabel("帮助：A:左转；D:右转；不能撞墙不能吃自己！",SwingConstants.CENTER);
		JPanel introduction = new JPanel();
		introduction.setLayout(new GridLayout(3,1));
		information1.setFont(font1);
		information2.setFont(font1);
		information3.setFont(font2);
		information3.setForeground(Color.RED);
		introduction.add(information1);
		introduction.add(information2);
		introduction.add(information3);
		JLabel score = new JLabel("得分：0",SwingConstants.RIGHT);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("HungrySnake");
		frame.add(playside,BorderLayout.CENTER);
		frame.add(introduction,BorderLayout.NORTH);
		frame.add(score,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(new KeyAdapter() {										//加入键盘监听以获取转向信息
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
		
		for(int i=0; i<1000; i++) {													//循环进行游戏
			try {
				Thread.sleep(200);													//每次刷新页面间隔0.2秒
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String PlayOrNot = field.isplay();
			if(PlayOrNot.equals("false"))											//判断是否继续进行游戏，为false时退出游戏
			{
				break;
			}else if(PlayOrNot.equals("true")) {									//返回值为true继续游戏
				
			}else {																	//返回值为有意义的字符串时输出得分
				score.setText(PlayOrNot);;
			}
			frame.repaint();														//重新打印页面
		}
		
		
	}
}
