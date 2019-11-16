import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		/*常规方法，分别遍历单值和多值参数*/
		System.out.println("获取单数参数name：" + request.getParameter("name"));
		
		String[] hobits = request.getParameterValues("hobits");
		System.out.println("获取多值参数hobits：" + Arrays.asList(hobits));
		
		/*通过使用getParameterMap方法获取参数表遍历参数*/
		System.out.println("通过getParameterMap方法遍历所有的参数：");
		//获取以参数名为key，参数值为value(包括多值因此为String数组)，生成一个Map
		Map<String,String[]> parameters = request.getParameterMap();
		
		//通过Map获取参数的名字的集合
		Set<String> paraName = parameters.keySet();
		//通过参数名的集合遍历得到所有的参数，并将参数转化为list进行输出
		for(String paraname : paraName) {
			String[] value = parameters.get(paraname);
			System.out.println(paraname + ":" + Arrays.asList(value));
		}
		
	}
	
}
