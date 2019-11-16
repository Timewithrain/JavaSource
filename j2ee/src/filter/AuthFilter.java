package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Init Filter");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//根据uri判断其页面是否为登录页面，如果是则继续登录操作，否则进行下一个判断
		String uri = request.getRequestURI();
		//通过uri解析内部文件是否需要过滤，此处若资源为.jpg文件则不进行过滤
		if(uri.endsWith(".jpg")||uri.endsWith("login")||uri.endsWith("login.html")) {
			//过滤完成后放行
			chain.doFilter(request, response);
			return;
		}
		
		//通过session获取userName判断是否为登录状态，当用户名为空时则尚未登录，返回至登录页面
		String userName = (String)request.getSession().getAttribute("userName");
		if(userName==null) {
			response.sendRedirect("login.html");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("Destroy Filter");
	}
	
}
