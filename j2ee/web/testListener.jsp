<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
    request.setAttribute("test", 123);
    request.setAttribute("test", 321);
    request.removeAttribute("test");
%>