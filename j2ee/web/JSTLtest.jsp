<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="name" value="${'watermelon'}" scope="request"/>

通过标签获取name：<c:out value="${name}"/><br>
<c:remove var="name" scope="request"/><br>
remove以后再通过标签获取name：<c:out value="${name}"/><br>

<%--if标签测试--%>
<c:set var="age" value="${19}" scope="request"/>
<c:if test="${age>17}" var="flag">
    <p>我成年了，我的年龄：<c:out value="${age}"/></p>
</c:if>
<c:if test="${not flag}">
    <p>我未成年，我的年龄：<c:out value="${age}"/></p>
</c:if>

<%--choose标签测试(类似于switch case)--%>
<c:set var="score" value="${87}" scope="request"/>
<c:choose>
    <c:when test="${score<101 && score>89}">
        <p>成绩等级为：A</p>
    </c:when>
    <c:when test="${score<90 && score>79}">
        <p>成绩等级为：B</p>
    </c:when>
    <c:when test="${score<80 && score>69}">
        <p>成绩等级为：C</p>
    </c:when>
    <c:when test="${score<70 && score>29}">
        <p>成绩等级为：D</p>
    </c:when>
    <c:when test="${score<60 && score>-1}">
        <p>成绩等级为：E</p>
    </c:when>
    <c:otherwise>
        <p>成绩错误！</p>
    </c:otherwise>
</c:choose>

<%--forEach标签测试--%>
<%
    List<String> roles = new ArrayList<String>();
    roles.add("IronMan");
    roles.add("SpiderMan");
    roles.add("GodofThunder");
    roles.add("Captain");
    roles.add("Hulk");
    request.setAttribute("roles",roles);
%>
<table width="200px" align="left" border="1px" cellspacing="0">
    <tr>
        <td>编号</td>
        <td>英雄</td>
    </tr>
    <c:forEach items="${roles}" var="role" varStatus="st">
        <tr>
            <td><c:out value="${st.count}"/></td>
            <td><c:out value="${role}"/></td>
        </tr>
    </c:forEach>        
</table><br><br><br><br><br><br><br><br>

<%--forTokens标签测试(提取分割内容)--%>
<c:set var="roles" value="钢铁侠,雷神.幻视;黑寡妇|奇异博士:黑豹"/>
<c:forTokens items="${roles}" delims=":;,.|" var="role">
    <c:out value="${role}"/> <br>
</c:forTokens><br>

<%--fmt标签测试(格式化数字)--%>
数字fmt：<br>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix='fmt' %> 
<c:set var="salary" value="12345.6" />
<c:set var="pi" value="3.1415926" />
最少保留小数点后两位:
<fmt:formatNumber type="number" value="${salary}" minFractionDigits="2"/><br>
最多保留小数点后两位：
<fmt:formatNumber type="number" value="${pi}" maxFractionDigits="2"/><br>

时间fmt：<br>
<%
    Date now = new Date();
    pageContext.setAttribute("now",now);
%>
日期格式：<fmt:formatDate value="${now}" pattern="G yyy年MM月dd日 E"/><br>
时间格式：<fmt:formatDate value="${now}" pattern="a HH:mm:ss.S z"/><br>
常见格式：<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/><br>

<%--fn标签(提供String常用处理功能)--%>
<%--
函数标签                                         描述
fn:contains(string, substring)                  如果参数string中包含参数substring，返回true

fn:containsIgnoreCase(string, substring)        如果参数string中包含参数substring（忽略大小写），返回true

fn:endsWith(string, suffix)                     如果参数 string 以参数suffix结尾，返回true

fn:escapeXml(string)                            将有特殊意义的XML (和HTML)转换为对应的XML character entity code，并返回

fn:indexOf(string, substring)                   返回参数substring在参数string中第一次出现的位置

fn:join(array, separator)                       将一个给定的数组array用给定的间隔符separator串在一起，组成一个新的字符串并返回。

fn:length(item)                                 返回参数item中包含元素的数量。参数Item类型是数组、collection或者String。如果是String类型,返回值是String中的字符数。

fn:replace(string, before, after)               返回一个String对象。用参数after字符串替换参数string中所有出现参数before字符串的地方，并返回替换后的结果

fn:split(string, separator)                     返回一个数组，以参数separator 为分割符分割参数string，分割后的每一部分就是数组的一个元素

fn:startsWith(string, prefix)                   如果参数string以参数prefix开头，返回true

fn:substring(string, begin, end)                返回参数string部分字符串, 从参数begin开始到参数end位置，包括end位置的字符

fn:substringAfter(string, substring)            返回参数substring在参数string中后面的那一部分字符串

fn:substringBefore(string, substring)           返回参数substring在参数string中前面的那一部分字符串

fn:toLowerCase(string)                          将参数string所有的字符变为小写，并将其返回

fn:toUpperCase(string)                          将参数string所有的字符变为大写，并将其返回

fn:trim(string)                                 去除参数string 首尾的空格，并将其返回
--%>