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
        <p>账&nbsp&nbsp&nbsp&nbsp号：<input type="text" name="loginId" value="${cookie.cookieId.value}">
            <span style="color: red">${msgId}</span>
        </p>
        <P>密&nbsp&nbsp&nbsp&nbsp码：<input type="password" name="loginPwd" value="${cookie.cookiePwd.value}">
            <span style="color: red">${msgPwd}</span>
        </P>
        <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            <input type="checkbox" value="yes" name="check0">记住账号
            <input type="checkbox" value="yes" name="check1">记住密码
        </p>
        <p>验证码:<img src="Account/setPicture">
            <input type="text" name="code" style="width: 85px">
            <span style="color: red">${msgCode}</span>
        </p>
        <p> <input type="submit" value="登陆"></p>
    </form>
</body>
</html>