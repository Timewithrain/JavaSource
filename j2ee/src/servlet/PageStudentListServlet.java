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
		//当登录成功的时候显示内容，否则返回到login.html进行登录
		String userName = (String)request.getSession().getAttribute("userName");
		if(userName==null) {
			response.sendRedirect("login.html");
			return;
		}
		//每一页仅显示5条数据
		int start = 0;
		int count = 5;
		int total = new StudentDAO().getTotalNum();
		try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {
            // 当浏览器没有传参数start时不修改start的值
        }
		List<Student> students = new StudentDAO().list(start,count);
		//当不为首页时计算上一页的起始记录,否则上一页就是首页不做变换
		int last = start;
		if(start!=0) {
			last = start - count;
		}
		//当计算下一页页首，当页首大于记录总长度时不做变换，停止到当前页面
		int next = start + count;
		if(next>=total) {
			next = start;
		}
		//首页的记录条数
		int head = 0;
		//首页的记录条数
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
		//通过服务器跳转至pageListStudent.jsp页面，与之前的页面共享request
		request.getRequestDispatcher("pageListStudent.jsp").forward(request, response);
	}
}
