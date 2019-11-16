package listener;

import java.util.Enumeration;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	//在Session作用域内添加属性时自动调用函数在后台输出属性名及其数值
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("检测到添加Attribute，name：" + se.getName() + ",value:" + se.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("Session中Attribute被移除");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("Session中Attribute被替换");
	}
}
