package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class PageStudentAddServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		int age = Integer.parseInt(request.getParameter("age"));
        String num = request.getParameter("num");
        String name = request.getParameter("name");
        
        Student s = new Student(num,name,age);
        new StudentDAO().add(s);
        
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("/pageListStudent");
	}
}
