package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class PageStudentEditServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		new StudentDAO().update(new Student(num,name,age));
		request.getRequestDispatcher("pageEditStudent.jsp").forward(request, response);
	}
}
