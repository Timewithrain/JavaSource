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
		
		//原始数据
		System.out.println("服务器接收的数据为：" + data);
		
		//转换为JSON的数据
		JSONObject json = JSONObject.fromObject(data);
		System.out.println("转换为JSON后数据为：" + json);
		
		//转换为Student对象的数据
		Student student = (Student)JSONObject.toBean(json,Student.class);
		System.out.println("转换为Student对象后数据为：" + student.toString());
	}
}
