package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener ,ServletRequestAttributeListener{

	@Override
	//Request��ʼ��ʱ�Զ�����
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("Request����ʼ��");
	}

	@Override
	//��Request���������������ʱ����
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("��⵽���Attribute��name:" + srae.getName() + ",value:" + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("��⵽Attribute���Ƴ�");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("��⵽Attribute���滻");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("Request������");
	}
	
}
