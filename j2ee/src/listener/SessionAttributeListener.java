package listener;

import java.util.Enumeration;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	//��Session���������������ʱ�Զ����ú����ں�̨���������������ֵ
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("��⵽���Attribute��name��" + se.getName() + ",value:" + se.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("Session��Attribute���Ƴ�");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("Session��Attribute���滻");
	}
}
