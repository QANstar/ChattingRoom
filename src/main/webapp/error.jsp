<%--
  Created by IntelliJ IDEA.
  User: W4ited
  Date: 2021/11/17
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path2 = request.getContextPath();
    String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>
<html>
<head>
    <title>The JavaEE Project</title>
    <link rel="stylesheet" href="<%=basePath2%>/static/css/bootstrap.min.css">
    <style>
        html, body{
            flex-direction: column;
        }
    </style>
</head>
<body>
<div class="alert alert-danger" role="alert">
    姓名或学号输入错误，请重新输入
</div>
<%@include file="index.jsp"%>
</body>
</html>
