<%--
  Created by IntelliJ IDEA.
  User: 14353
  Date: 2021/11/17
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String onlineNum = (String) request.getServletContext().getAttribute("online");
%>
<html>
<head>
    <title>聊天室</title>
    <link rel="stylesheet" href="<%=basePath%>/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/style.css">
    <style>
        body {
            background: url(<%=basePath%>/static/img/talkBackground3.jpg);
            background-repeat: no-repeat;
            background-size:cover;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<div style="width: 100%">
    <div class="top">
        <div style="margin-left: 50px;">
            509 | <span style="font-size: 1rem;font-weight: 600;">聊天室</span>
        </div>
    </div>
    <div class="main">
        <div class="talkContent">
            <div id="talkMain" class="talkMain">

            </div>
            <div class="inputMain">
                <textarea id="userInput" class="form-control textInput" id="inputText" rows="3"></textarea>
                <button onclick="putClick()" class="btn btn-success">发送</button>
                <button onclick="clearText()" class="btn btn-warning">清空</button>
            </div>
        </div>
        <div class="messageContent">
            <div class="peopleTop">
                <div style="font-weight: 600;font-size: 1.3rem">
                    当前在线
                </div>
                <div style="font-weight: 600;font-size: 1rem;color: #FF0033;">
                    人数：<span id="peopleNum"></span>
                </div>
            </div>
            <div id="userList" class="onlineList">
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath%>/static/js/jquery-3.6.0.js"></script>
<script>
    //显示用户列表
    function showUserList() {
        $.ajax({
            type: "POST",
            url: "showUserServlet",
            // dataType: "json",
            dataType: "text",
            success: function (data) {
                let html = "";
                let num = 0;
                if (data != 'null\r\n') {
                    let user = new Array();
                    user = data.split(",");
                    num = user.length;
                    for (let i = 0; i < user.length; i++) {
                        html += '<div class="onlineEle"> <img src="<%=basePath%>/static/img/' + user[i] + '.bmp"> <span>' + user[i] + '</span> </div>';
                    }
                }


                $("#userList").html(html);
                $("#peopleNum").html(num);
            },
            error: function (e) {
                alert("错误");
            }

        })
    }

    //发送消息
    function putClick() {
        <%--let html = '<div class="chat-receiver"><div><img src="<%=basePath%>/static/img/me.png"></div><div>量星</div><div><div class="chat-right_triangle"></div><span>' + $("#userInput").val() + '</span></div></div>';--%>
        <%--$("#talkMain").append(html);--%>

        let name = "${sessionScope.login}";
        let message = $("#userInput").val();
        $.ajax({
            type: "POST",
            url: "addServlet",
            data: {name: name, message: message},
            // dataType: "json",
            dataType: "text",
            success: function (data) {

                //alert(JSON.stringify(data));//使用JSON方法，需引入jar包，json-java.jar、json-lib.jar
                // alert(data);
            },
            error: function (e) {
                alert("错误");
            }

        })
        $("#userInput").val("");
    }

    //清空输入
    function clearText() {
        $("#userInput").val("");
        showMessage();
    }

    //定时刷新
    setInterval("refrash()", 1000);
    function refrash(){
        showMessage();
        showUserList();
    }

    let messNum = 0;
    //展示消息
    function showMessage() {
        $.ajax({
            type: "POST",
            url: "showServlet",
            // dataType: "json",
            dataType: "text",
            success: function (data) {
                let name = "${sessionScope.login}";
                let html = "";
                if (data != 'null\r\n') {
                    let messageStr = data;
                    let messageList = new Array();
                    messageList = messageStr.split(";");
                    for (let i = messNum; i < messageList.length; i++) {
                        if (messageList[i].split(",")[0] == name) {
                            html += '<div class="toastAnim chat-receiver"><div><img src="<%=basePath%>/static/img/' + messageList[i].split(",")[0] + '.bmp"></div><div>' + messageList[i].split(",")[0] + '</div><div><div class="chat-right_triangle"></div><span>' + messageList[i].split(",")[1] + '</span></div></div>';
                        } else {
                            html += '<div class="toastAnim chat-sender"><div><img src="<%=basePath%>/static/img/' + messageList[i].split(",")[0] + '.bmp"></div><div>' + messageList[i].split(",")[0] + '</div><div><div class="chat-left_triangle"></div><span>' + messageList[i].split(",")[1] + '</span></div></div>';
                        }
                    }
                    messNum = messageList.length;
                }
                if (html!=""){
                    $("#talkMain").append(html);
                    let scrollHeight = $('#talkMain').prop("scrollHeight");
                    $('#talkMain').animate({scrollTop:scrollHeight}, 400);
                }

            },
            error: function (e) {
                alert("错误");
            }

        })
    }
</script>
</body>
</html>
