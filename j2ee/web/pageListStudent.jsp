<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<script src="jquery.min.js"></script>
<link href="bootstrap.min.css" rel="stylesheet">
<script src="bootstrap.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
    $(function(){
        $("a").addClass("btn btn-default btn-xs");
    });
</script>

<table style="width:500px; margin:44px auto" 
class="table table-striped table-bordered table-hover  table-condensed"
align="center" border="1" cellspacing="0">
    <tr>
        <td>num</td>
        <td>name</td>
        <td>age</td>
        <td>edit</td>
        <td>delete</td>
    </tr>
    <c:forEach items="${students}" var="student" varStatus="st">
        <tr>
            <td>${student.num}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td><a href="editStudent?num=${student.num}">edit</a></td>
            <td><a href="pageDeleteStudent?num=${student.num}">delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5" align="center">
            <a href="pageAddStudent.jsp">添加</a>
        </td>
    </tr>
    <tr>
        <td colspan="5" align="center">
            <a href="?start=${head}">[首 页]</a>
            <a href="?start=${last}">[上一页]</a>
            <a href="?start=${next}">[下一页]</a>
            <a href="?start=${end}">[末 页]</a>
        </td>
    </tr>
</table>