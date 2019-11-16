package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class StudentUpdateServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		int age = Integer.parseInt(request.getParameter("age"));
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		
		//���ݴ���Ĳ�������һ��student���󣬽��˶�����StudentDAO.update�����������ݿ�
		new StudentDAO().update(new Student(num,name,age));
		response.sendRedirect("/listStudent");
	}
}
