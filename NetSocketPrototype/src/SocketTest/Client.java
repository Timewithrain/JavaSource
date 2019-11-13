package SocketTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Client {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Scanner in = new Scanner(System.in);
		Socket s = new Socket("localhost",9999);
		InputStream inStream = s.getInputStream();
		DataInputStream recive = new DataInputStream(inStream);
		OutputStream outStream = s.getOutputStream();
		DataOutputStream out = new DataOutputStream(outStream);
//		//ͨ��һ�����������һ�������д��������һ������Ϊ��������ڶ�������Ϊ�Ƿ��Զ���ջ�����
//		PrintWriter out = new PrintWriter(outStream,true);
		
		//��ȡ��Ϣ�߳�
		Thread t1 = new Thread() {
			public void run() {
				try {
					while(true) {
						String massage = recive.readUTF();
						if(massage!=null) {
							if(massage.equals("BYE")) {
								System.out.println("�����ѹرգ���ظ�BYE!");
								break;
							}
							System.out.println("����������Ϣ��" + massage);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		//�����Ϣ�߳�
		Thread t2 = new Thread() {
			public void run() {
				String send = null;
				do {
					send = in.nextLine();
					try {
						out.writeUTF(send);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}while(!send.equals("BYE"));
			}
		};
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		recive.close();
		out.close();
		inStream.close();
		outStream.close();
		recive.close();
		out.close();
		s.close();
		
	}

}
