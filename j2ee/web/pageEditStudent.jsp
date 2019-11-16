<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.*,java.sql.*"%>

<form action="pageUpdateStudent" method="post">
    学号：<input type="text" name="num" value="${student.num}"> <br>
    姓名：<input type="text" name="name" value="${student.name}"> <br>
    年龄：<input type="text" name="age" value="${student.age}"> <br>
    <input type="submit" value="更新">
</form>