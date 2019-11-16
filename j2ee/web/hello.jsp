<%--JSP指令--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%request.setCharacterEncoding("UTF-8");%>

<%--用JSP动作的方式调用顶部页面文件--%>
<%--jsp动作的方式调用页面由于是访问不同的页面需要参数的传递--%>
<jsp:include page="header.jsp">
	<jsp:param name="name" value="山雨"/>
</jsp:include>

Hello JSP	<br>
<%--java表达式--%>
<%=new Date().toLocaleString()%><br>
<%out.println("This is just for test");%><br>
<%--out为jsp隐式类可直接隐式调用--%>
<%="This is just for test"%>

<%--通过指令的方式调用底部页面文件--%>
<%@include file="footer.jsp"%>