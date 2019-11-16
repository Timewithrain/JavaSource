package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("��⵽Session������SessionID��" + se.getSession().getId());
	}

	@Override
	//����������Session���Զ�����ʱ��Ϊ30����
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("��⵽Session���٣�SessionID��" + se.getSession().getId());
	}
}
