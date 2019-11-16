import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		//由于getHeaderNames方法返回一个Enumeration的集合因此使用Eumeration保存返回值
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + ":" + headerValue);
		}
		
		try {
			/*浏览器不设置缓存*/
//			response.setDateHeader("Expires", 0);
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("pragma", "no-cache");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<h1>This is what I write for my own<h1>");
			response.getWriter().println(new Date().toLocaleString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}