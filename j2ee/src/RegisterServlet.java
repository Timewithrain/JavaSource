import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		/*���淽�����ֱ������ֵ�Ͷ�ֵ����*/
		System.out.println("��ȡ��������name��" + request.getParameter("name"));
		
		String[] hobits = request.getParameterValues("hobits");
		System.out.println("��ȡ��ֵ����hobits��" + Arrays.asList(hobits));
		
		/*ͨ��ʹ��getParameterMap������ȡ�������������*/
		System.out.println("ͨ��getParameterMap�����������еĲ�����");
		//��ȡ�Բ�����Ϊkey������ֵΪvalue(������ֵ���ΪString����)������һ��Map
		Map<String,String[]> parameters = request.getParameterMap();
		
		//ͨ��Map��ȡ���������ֵļ���
		Set<String> paraName = parameters.keySet();
		//ͨ���������ļ��ϱ����õ����еĲ�������������ת��Ϊlist�������
		for(String paraname : paraName) {
			String[] value = parameters.get(paraname);
			System.out.println(paraname + ":" + Arrays.asList(value));
		}
		
	}
	
}
