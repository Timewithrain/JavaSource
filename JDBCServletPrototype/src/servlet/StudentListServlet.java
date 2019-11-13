package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

@SuppressWarnings("serial")
public class StudentListServlet extends HttpServlet {
	
	public void service(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		
		//获取数据库中所有Student对象list
		List<Student> list = new StudentDAO().list();
		
		//在网页中表格化输出Stduent
        StringBuffer sb = new StringBuffer();
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        sb.append("<tr><td>num</td><td>name</td><td>age</td><td>edit</td><td>delete</td></tr>\r\n");
        
        //设置输出格式
        String sFormat = "<tr><td>%s</td><td>%s</td><td>%d</td><td><a href='editStudent?num=%s'>edit</a></td>"
        		+ "<td><a href='deleteStudent?num=%s'>delete</a></td></tr>\r\n";
        for(Student s:list) {
        	String sform = String.format(sFormat, s.getNum(), s.getName(), s.getAge(), s.getNum(), s.getNum());
        	sb.append(sform);
        }
		sb.append("</table>");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(sb.toString());
	}
}
