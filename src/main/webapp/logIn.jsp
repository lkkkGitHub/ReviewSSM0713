<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
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
    <form action="Account/login" method="post">
        <p>账号：<input type="text" name="loginId"> ${msg0} </p>
        <P>密码：<input type="password" name="loginPwd"> ${msg1} </P>
        <p> <input type="submit" value="登陆"></p>
    </form>
</body>
</html>