package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class StudentEditServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//根据传入的学号从数据库中获取Student对象
		String num = request.getParameter("num");
		Student s = new StudentDAO().get(num);
		
		//返回一个用于编辑学生信息的页面，其中学号不可修改，页面提交以后访问后台updateStudent对应class进行处理
		response.setContentType("text/html;charset=UTF-8");
		StringBuffer formate = new StringBuffer();
		formate.append("<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    "
				+ "<meta content=\"text/html;charset=UTF-8\">\r\n    <title>编辑学生信息</title>\r\n</head>");
		formate.append("<body>\r\n" + 
				"    <form action=\"updateStudent\" method=\"post\">\r\n" + 
				"        学号 ： <input type=\"text\" name=\"num\" readonly=\"readonly\" value=\"%s\"> <br>\r\n" + 
				"        名字 ： <input type=\"text\" name=\"name\" value=\"%s\"> <br>\r\n" + 
				"        年龄 ： <input type=\"text\" name=\"age\" value=\"%d\"> <br>\r\n" + 
				"            <input type=\"submit\" value=\"更新 \">\r\n" + 
				"   </form>\r\n" + 
				"</body>");
		formate.append("</html>");
		String html = String.format(formate.toString(), s.getNum(), s.getName(), s.getAge());
		response.getWriter().write(html);
	}
}
