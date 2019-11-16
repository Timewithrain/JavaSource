package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener ,ServletRequestAttributeListener{

	@Override
	//Request初始化时自动调用
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("Request被初始化");
	}

	@Override
	//在Request作用域内添加属性时调用
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("检测到添加Attribute，name:" + srae.getName() + ",value:" + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("检测到Attribute被移除");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("检测到Attribute被替换");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("Request被销毁");
	}
	
}
