<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" import="bean.*" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="name" value="${'watermelon'}" scope="request"/>
通过标签获取name：<c:out value="${name}"/><br>
通过 EL 获取name：${name}<br>

<%--通过EL获取Javabean类的属性--%>
<%
    Student s = new Student();
    s.setNum("001");
    s.setName("时无诳语");
    s.setAge(19);
    request.setAttribute("student",s);
%>
学生学号：${student.num}<br>
学生姓名：${student.name}<br>
学生年龄：${student.age}<br>

<%--通过EL获取parameter的值--%>
${param.name}

<%--eq判断--%>
是否成年：${student.age ge 18?"成年":"未成年"}
<%--
eq表达式：       含义：
eq              等于
ne(neq)         不等于
gt              大于
lt              小于
ge(gte)         大于等于
le(lte)         小于等于
not             非
mod             模
is [not] div by 是否能被某数整除 
is [not] even   是否为偶数 
is [not] odd    是否为奇
--%>