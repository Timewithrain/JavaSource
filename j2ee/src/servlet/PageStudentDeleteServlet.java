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
		//根据获取的学生学号生成一个临时学生对象用于传入delete方法删除对应学号的学生
		new StudentDAO().delete(new Student(num,null,0));
		response.sendRedirect("/pageListStudent");
	}
}
