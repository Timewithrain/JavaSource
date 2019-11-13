package IPtest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPTest {

	public static void main(String[] args) {
		//获取本机名称和本机地址
//		try {
//			InetAddress host = InetAddress.getLocalHost();
//			System.out.println("本机名称：" + host.getHostName());
//			System.out.println("本机IP：" + host.getHostAddress());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//通过java程序运行DOS指令并显示返回结果
		try {
			Process p = Runtime.getRuntime().exec("ping www.taobao.com");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String content = br.readLine();
			while(content!=null) {
				System.out.println(content);
				content = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
