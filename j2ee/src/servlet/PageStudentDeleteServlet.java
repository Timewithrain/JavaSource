package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class PageStudentDeleteServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String num = request.getParameter("num");
		//���ݻ�ȡ��ѧ��ѧ������һ����ʱѧ���������ڴ���delete����ɾ����Ӧѧ�ŵ�ѧ��
		new StudentDAO().delete(new Student(num,null,0));
		response.sendRedirect("/pageListStudent");
	}
}
