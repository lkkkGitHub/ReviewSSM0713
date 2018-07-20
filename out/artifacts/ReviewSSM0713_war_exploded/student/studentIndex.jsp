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
<p>
    student:
    <c:choose>
        <c:when test="${sessionScope.sessionAccount==null}">
            <a href="Account/login">请登录！</a>
        </c:when>
        <c:otherwise>
            ${sessionScope.sessionAccount.loginId} <a href="Account/exit">注销</a>
        </c:otherwise>
    </c:choose>
</p>
<P><a href="Student/findStuInfo">查询个人信息</a></P>
</body>
</html>