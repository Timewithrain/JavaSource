<%@ page language="java"  contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8" import="javax.servlet.http.Cookie"%>

<%
	Cookie[] cookies = request.getCookies();
	for(int i=0;i<cookies.length;i++){
		out.print(cookies[i].getName() + ":" + cookies[i].getValue() + "\r\n");
	}
%>