package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDAO;
import bean.Student;

public class PageStudentListServlet extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//����¼�ɹ���ʱ����ʾ���ݣ����򷵻ص�login.html���е�¼
		String userName = (String)request.getSession().getAttribute("userName");
		if(userName==null) {
			response.sendRedirect("login.html");
			return;
		}
		//ÿһҳ����ʾ5������
		int start = 0;
		int count = 5;
		int total = new StudentDAO().getTotalNum();
		try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // �������û�д�����startʱ���޸�start��ֵ
        }
		List<Student> students = new StudentDAO().list(start,count);
		//����Ϊ��ҳʱ������һҳ����ʼ��¼,������һҳ������ҳ�����任
		int last = start;
		if(start!=0) {
			last = start - count;
		}
		//��������һҳҳ�ף���ҳ�״��ڼ�¼�ܳ���ʱ�����任��ֹͣ����ǰҳ��
		int next = start + count;
		if(next>=total) {
			next = start;
		}
		//��ҳ�ļ�¼����
		int head = 0;
		//��ҳ�ļ�¼����
		int end = 0;
		if(total%5!=0) {
			end = (total/5)*5;
		}else {
			end = (total/5-1)*5;
		}
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("head", head);
		request.setAttribute("last", last);
		request.setAttribute("next", next);
		request.setAttribute("end", end);
		request.setAttribute("students", students);
		//ͨ����������ת��pageListStudent.jspҳ�棬��֮ǰ��ҳ�湲��request
		request.getRequestDispatcher("pageListStudent.jsp").forward(request, response);
	}
}
