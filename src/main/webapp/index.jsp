<%@ page import="javax.naming.Context" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>The JavaEE Project</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=basePath%>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/style.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/logStyle.css">
    <style>
        body{
            margin: 0 !important;
            background: url("<%=basePath%>/static/img/background.png");
            background-repeat: no-repeat;
            background-size:cover;
            background-attachment: fixed;


        }

    </style>
</head>

<body>

<%

    Cookie emailCookie = null;
    Cookie passwordCookie = null;
    String email = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    //获取姓名和id的cookie
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("email")) {
                emailCookie = cookies[i];
            }
            if (cookies[i].getName().equals("password")) {
                passwordCookie = cookies[i];
            }

        }
    }

    //读取cookie里的值
    if (emailCookie != null) {
        email = emailCookie.getValue();
    }
    if (passwordCookie != null) {
        password = passwordCookie.getValue();
    }
%>

<div class="formBox">
    <form action = "loginServlet" method = "post">
        <div class="form-group">
            <label for="email">姓名</label>
            <input type="text" name="email" value="<%=email%>" class="form-control" id="email">
        </div>
        <div class="form-group">
            <label for="password">学号</label>
            <input type="password" name="password" value="<%=password%>" class="form-control" id="password">
        </div>
        <div class="form-group form-check">
            <input name="remember" type="checkbox" class="form-check-input" checked id="check">
            <label class="form-check-label" for="check">记住密码</label>
        </div>
        <div class="form-group formButton">
            <button type="submit" class="btn btn-success">登录</button>
            <button type="reset" class="btn btn-warning">重置</button>
        </div>

    </form>
</div>


</body>
</html>