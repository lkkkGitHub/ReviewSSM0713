<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
</head>
<body>
<form action="Student/updateStu" method="post">
    <p><input type="text" name="studentName" value="${student.studentName}"></p>
    <p><input type="text" name="studentAge" value="${student.studentAge}"></p>
    <p>
        <c:choose>
            <c:when test="${student.studentSex=='男'}">
                <input type="radio" value="男" name="studentSex" checked>男
                <input type="radio" value="女" name="studentSex">女
            </c:when>
            <c:otherwise>
                <input type="radio" value="男" name="studentSex">男
                <input type="radio" value="女" name="studentSex" checked>女
            </c:otherwise>
        </c:choose>
    </p>
    <p><input type="submit" value="提交"></p>
</form>
<p></p>
</body>
</html>