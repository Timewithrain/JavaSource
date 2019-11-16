<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript">
	function myfun(){<%response.sendRedirect("hello.jsp");%>console.log("advanced")}
</script>
<form>
        <input id="jump" type="button" value="jump"  onclick="myfun()">
</form>
