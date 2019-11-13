package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class StudentAddServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		//从网页获取提交的Student信息
		int age = Integer.parseInt(request.getParameter("age"));
        String num = request.getParameter("num");
        String name = request.getParameter("name");
        
        //向数据库中添加Student
        Student s = new Student(num,name,age);
        new StudentDAO().add(s);
        
        //添加完成后自动跳转到listStudent页面查看Student信息
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("/JDBCServletPrototype/listStudent");
	}
}
