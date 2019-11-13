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
//		//通过一个输出流创建一个输出流写入器，第一个参数为输出流，第二个参数为是否自动清空缓存区
//		PrintWriter out = new PrintWriter(outStream,true);
		
		//读取消息线程
		Thread t1 = new Thread() {
			public void run() {
				try {
					while(true) {
						String massage = recive.readUTF();
						if(massage!=null) {
							if(massage.equals("BYE")) {
								System.out.println("连接已关闭，请回复BYE!");
								break;
							}
							System.out.println("服务器端消息：" + massage);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		//输出消息线程
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
