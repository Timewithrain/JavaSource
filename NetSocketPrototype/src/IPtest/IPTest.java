package IPtest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPTest {

	public static void main(String[] args) {
		//��ȡ�������ƺͱ�����ַ
//		try {
//			InetAddress host = InetAddress.getLocalHost();
//			System.out.println("�������ƣ�" + host.getHostName());
//			System.out.println("����IP��" + host.getHostAddress());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//ͨ��java��������DOSָ���ʾ���ؽ��
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
