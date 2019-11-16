import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import net.sf.json.JSONObject;

public class SubmitServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		
		//ԭʼ����
		System.out.println("���������յ�����Ϊ��" + data);
		
		//ת��ΪJSON������
		JSONObject json = JSONObject.fromObject(data);
		System.out.println("ת��ΪJSON������Ϊ��" + json);
		
		//ת��ΪStudent���������
		Student student = (Student)JSONObject.toBean(json,Student.class);
		System.out.println("ת��ΪStudent���������Ϊ��" + student.toString());
	}
}
