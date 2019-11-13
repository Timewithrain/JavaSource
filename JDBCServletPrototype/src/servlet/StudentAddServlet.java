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
		
		//����ҳ��ȡ�ύ��Student��Ϣ
		int age = Integer.parseInt(request.getParameter("age"));
        String num = request.getParameter("num");
        String name = request.getParameter("name");
        
        //�����ݿ������Student
        Student s = new Student(num,name,age);
        new StudentDAO().add(s);
        
        //�����ɺ��Զ���ת��listStudentҳ��鿴Student��Ϣ
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("/JDBCServletPrototype/listStudent");
	}
}
