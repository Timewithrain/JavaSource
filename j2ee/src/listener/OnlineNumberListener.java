package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineNumberListener implements HttpSessionListener {

	@Override
	//当有Session被创建时调用函数online_number加1
	public void sessionCreated(HttpSessionEvent se) {
		//获取jsp页面中的application并从中获取online_number属性的值
		ServletContext application = se.getSession().getServletContext();
		Integer online_number = (Integer)application.getAttribute("online_number");
		//若online_number值为0则尚未有用户连接，online_number初始化为0
		if(online_number==null) {
			online_number = 0;
		}
		online_number++;
		application.setAttribute("online_number", online_number);
		System.out.println("新增一位用户");
	}

	@Override
	//Session被销毁时调用函数online_number减1
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();
		Integer online_number = (Integer) application.getAttribute("online_number");
		//当online_number已经为0时不作变化，否则进行减1操作
		if(online_number==0) {
			online_number = 0;
		}else {
			online_number--;
		}
		application.setAttribute("online_number", online_number);
		System.out.println("一位用户离线");
	}

}
