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
		
		//����uri�ж���ҳ���Ƿ�Ϊ��¼ҳ�棬������������¼���������������һ���ж�
		String uri = request.getRequestURI();
		//ͨ��uri�����ڲ��ļ��Ƿ���Ҫ���ˣ��˴�����ԴΪ.jpg�ļ��򲻽��й���
		if(uri.endsWith(".jpg")||uri.endsWith("login")||uri.endsWith("login.html")) {
			//������ɺ����
			chain.doFilter(request, response);
			return;
		}
		
		//ͨ��session��ȡuserName�ж��Ƿ�Ϊ��¼״̬�����û���Ϊ��ʱ����δ��¼����������¼ҳ��
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
