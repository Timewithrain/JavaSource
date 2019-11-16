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
		
		//根据传入的参数生成一个student对象，将此对象用StudentDAO.update方法存入数据库
		new StudentDAO().update(new Student(num,name,age));
		response.sendRedirect("/listStudent");
	}
}
