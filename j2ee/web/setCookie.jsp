<%@ page language="java"  contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8" import="javax.servlet.http.Cookie"%>

<%
	Cookie c = new Cookie("name","watermelon");
	c.setMaxAge(24*60*60);
	c.setPath("/");
	response.addCookie(c);
%>