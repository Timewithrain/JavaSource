package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Web ��ʼ��");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Web ������");
	}
	
}
