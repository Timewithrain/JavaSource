import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import net.sf.json.JSONObject;

public class GetServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Student student = new Student();
		student.setNum("2333");
		student.setName("������");
		student.setAge(18);
		//ͨ��JSON����ʽ��������
		JSONObject json = new JSONObject();
		json.put("student", JSONObject.fromObject(student));
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(json);
	}
}
