package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineNumberListener implements HttpSessionListener {

	@Override
	//����Session������ʱ���ú���online_number��1
	public void sessionCreated(HttpSessionEvent se) {
		//��ȡjspҳ���е�application�����л�ȡonline_number���Ե�ֵ
		ServletContext application = se.getSession().getServletContext();
		Integer online_number = (Integer)application.getAttribute("online_number");
		//��online_numberֵΪ0����δ���û����ӣ�online_number��ʼ��Ϊ0
		if(online_number==null) {
			online_number = 0;
		}
		online_number++;
		application.setAttribute("online_number", online_number);
		System.out.println("����һλ�û�");
	}

	@Override
	//Session������ʱ���ú���online_number��1
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();
		Integer online_number = (Integer) application.getAttribute("online_number");
		//��online_number�Ѿ�Ϊ0ʱ�����仯��������м�1����
		if(online_number==0) {
			online_number = 0;
		}else {
			online_number--;
		}
		application.setAttribute("online_number", online_number);
		System.out.println("һλ�û�����");
	}

}
