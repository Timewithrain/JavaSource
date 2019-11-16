package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("检测到Session创建，SessionID：" + se.getSession().getId());
	}

	@Override
	//服务器设置Session的自动销毁时间为30分钟
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("检测到Session销毁，SessionID：" + se.getSession().getId());
	}
}
