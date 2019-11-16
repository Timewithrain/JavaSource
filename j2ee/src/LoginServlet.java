import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	public LoginServlet() {
		System.out.println("LoginServlet���췽��������!");
	}
	
	public void init(ServletConfig config) {
		System.out.println("init����������!");
	}
	
	/*
	 * �ڵ���doGet��doPost����֮ǰ���������ִ��service�������ж�Ӧ����doGet/doPost�е���һ��
	 * doGet������doPost������service���������б���ͬ����˿���ֱ����дservice��������ҵ���߼�
	 * д��service�����У�ֱ�ӵ���service������ɹ��ܣ������ٽ����ж�
	 * 
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println("name:" + name);
		System.out.println("password:" + password);
		
		String html = null;
		//�������������nameΪzhouxu��passwordΪ123456ʱ����¼�ɹ�
		/*����ת�ķ�ʽʵ��ҳ��ת��*/
//		if("zhouxu".equals(name)&&"123456".equals(password)) {
//			html = "<div style='color:slateblue'><h2>��¼�ɹ�<h2></div>";
//		}else {
//			html = "<div style='color:red'><h2>��¼ʧ��<h2></div>";
//		}
//		//���ض�Ӧ��¼�ɹ���htmlҳ��
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter pw = response.getWriter();
//		pw.println(html);
		
		//����Cookie
		Cookie cookieName = new Cookie("name",name);
		Cookie cookiePassword = new Cookie("password",password);
		//����cookie����ʱ��
		cookieName.setMaxAge(24*60*60);
		cookiePassword.setMaxAge(24*60*60);
		//����ʹ��cookie�ķ���·��"/"��ʾ��·��
		cookieName.setPath("/");
		cookiePassword.setPath("/");
		
		/*��ת�ķ�ʽʵ��ҳ��ת��*/
		if("zhouxu".equals(name)&&"123456".equals(password)) {
			//��¼�ɹ����cookie
			response.addCookie(cookieName);
			response.addCookie(cookiePassword);
			//��¼�Ժ��û�������Session����ֵΪ"userName"
			request.getSession().setAttribute("userName", name);
			//��¼�ɹ��ڷ������˽�����ת
//			request.getRequestDispatcher("success.html").forward(request, response);
			response.sendRedirect("pageListStudent");
		}else {
			//��¼ʧ�ܺ��ڿͻ��˽�����ת
//			response.sendRedirect("fail.html");
			response.sendRedirect("login.html");
		}
	}
}
