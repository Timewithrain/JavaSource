<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<head>
    <meta content="text/html;charset=UTF-8">
    <title>添加学生</title>
</head>
<body>
    <table>
        <form action="pageAddStudent" method="post">
            学号 ： <input type="text" name="num"> <br>
            名字 ： <input type="text" name="name"> <br>
            年龄 ： <input type="text" name="age"> <br>
                <input type="submit" value="增加 ">
        </form>
    </table>
</body>