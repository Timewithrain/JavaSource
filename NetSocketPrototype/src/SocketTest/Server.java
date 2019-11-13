package SocketTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner in = new Scanner(System.in);
		
		try {
			ServerSocket ss = new ServerSocket(9999);
			Socket s = ss.accept();
			System.out.println("收到连接请求");
			InputStream inStream = s.getInputStream();
			DataInputStream recive = new DataInputStream(inStream);
			OutputStream outStream = s.getOutputStream();
			DataOutputStream out = new DataOutputStream(outStream);
			
			//读取消息线程
			Thread t1 = new Thread() {
				public void run() {
					while(true) {
						String massage = null;
						try {
							massage = recive.readUTF();
							if(massage!=null) {
								System.out.println("客户端消息：" + massage);
								if(massage.equals("BYE")) {
									s.close();
									break;
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}	
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
			if(!s.isClosed()) {
				s.close();
			}
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
