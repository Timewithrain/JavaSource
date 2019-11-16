import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	public LoginServlet() {
		System.out.println("LoginServlet构造方法被调用!");
	}
	
	public void init(ServletConfig config) {
		System.out.println("init方法被调用!");
	}
	
	/*
	 * 在调用doGet或doPost方法之前，程序会先执行service方法来判断应调用doGet/doPost中的哪一个
	 * doGet方法、doPost方法和service方法参数列表相同，因此可以直接重写service方法，将业务逻辑
	 * 写在service方法中，直接调用service方法完成功能，不必再进行判断
	 * 
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println("name:" + name);
		System.out.println("password:" + password);
		
		String html = null;
		//当浏览器传来的name为zhouxu，password为123456时，登录成功
		/*非跳转的方式实现页面转换*/
//		if("zhouxu".equals(name)&&"123456".equals(password)) {
//			html = "<div style='color:slateblue'><h2>登录成功<h2></div>";
//		}else {
//			html = "<div style='color:red'><h2>登录失败<h2></div>";
//		}
//		//返回对应登录成功的html页面
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter pw = response.getWriter();
//		pw.println(html);
		
		//设置Cookie
		Cookie cookieName = new Cookie("name",name);
		Cookie cookiePassword = new Cookie("password",password);
		//设置cookie存留时间
		cookieName.setMaxAge(24*60*60);
		cookiePassword.setMaxAge(24*60*60);
		//设置使用cookie的访问路径"/"表示根路径
		cookieName.setPath("/");
		cookiePassword.setPath("/");
		
		/*跳转的方式实现页面转换*/
		if("zhouxu".equals(name)&&"123456".equals(password)) {
			//登录成功添加cookie
			response.addCookie(cookieName);
			response.addCookie(cookiePassword);
			//登录以后将用户名加入Session，键值为"userName"
			request.getSession().setAttribute("userName", name);
			//登录成功在服务器端进行跳转
//			request.getRequestDispatcher("success.html").forward(request, response);
			response.sendRedirect("pageListStudent");
		}else {
			//登录失败后在客户端进行跳转
//			response.sendRedirect("fail.html");
			response.sendRedirect("login.html");
		}
	}
}
