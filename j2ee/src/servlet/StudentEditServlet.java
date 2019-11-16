package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class StudentEditServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���ݴ����ѧ�Ŵ����ݿ��л�ȡStudent����
		String num = request.getParameter("num");
		Student s = new StudentDAO().get(num);
		
		//����һ�����ڱ༭ѧ����Ϣ��ҳ�棬����ѧ�Ų����޸ģ�ҳ���ύ�Ժ���ʺ�̨updateStudent��Ӧclass���д���
		response.setContentType("text/html;charset=UTF-8");
		StringBuffer formate = new StringBuffer();
		formate.append("<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    "
				+ "<meta content=\"text/html;charset=UTF-8\">\r\n    <title>�༭ѧ����Ϣ</title>\r\n</head>");
		formate.append("<body>\r\n" + 
				"    <form action=\"updateStudent\" method=\"post\">\r\n" + 
				"        ѧ�� �� <input type=\"text\" name=\"num\" readonly=\"readonly\" value=\"%s\"> <br>\r\n" + 
				"        ���� �� <input type=\"text\" name=\"name\" value=\"%s\"> <br>\r\n" + 
				"        ���� �� <input type=\"text\" name=\"age\" value=\"%d\"> <br>\r\n" + 
				"            <input type=\"submit\" value=\"���� \">\r\n" + 
				"   </form>\r\n" + 
				"</body>");
		formate.append("</html>");
		String html = String.format(formate.toString(), s.getNum(), s.getName(), s.getAge());
		response.getWriter().write(html);
	}
}
