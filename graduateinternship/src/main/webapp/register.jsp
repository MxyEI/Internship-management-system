<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/29
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实习管理系统 - 注册</title>
    <meta name="keywords" content="perfect-ssm">
    <meta name="description" content="perfect-ssm">
    <link
            href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css"
            rel="stylesheet">
    <link
            href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css"
            rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css"
          rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap-3.3.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">PMS</h1>
        </div>
        <h3>请填写注册信息</h3>

        <form class="m-t" role="form" id="userregister" method="post"
              name="userregister" onsubmit="return false" action="##">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="用户名"
                       name="username" id="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码"
                       name="password" id="password">
            </div>

            <%--<div class="form-group">--%>
                <%--<input type="text" class="form-control" placeholder="公司名称"--%>
                       <%--name="name" id="name">--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
                <%--<input type="text" class="form-control" placeholder="公司地址"--%>
                       <%--name="address" id="address">--%>
            <%--</div>--%>

            <button type="button" class="btn btn-primary block full-width m-b"
                    onclick="reg();">注册</button>

            <button type="button" class="btn btn-primary block full-width m-b"
                    onclick="comeb();">已有账号,去登陆</button>
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    function reg() {

        var data = {
            "username" : $("#username").val(),
            "password" : $("#password").val(),
            "usertype": 'company'
        };

        $.ajax({
            type : "post",
            url : "${pageContext.request.contextPath}/users",
            data : data,
            success : function(result) {
                if (result.resultCode == 200) {
                    alert("注册成功！直接登录...");
                    $.ajax({
                        type : "GET",
                        url : "${pageContext.request.contextPath}/users/cookie",
                        data : data,
                        success : function(result) {
                            if (result.resultCode == 200) {
                                setCookie("id", result.data.currentUser.id);
                                setCookie("username", result.data.currentUser.username);
                                setCookie("usertype", result.data.currentUser.usertype);
                                window.location.href = "${pageContext.request.contextPath}/companyIndex.jsp";
                            } else {
                                alert(result.message);
                            }
                        },
                        error : function() {
                            alert("异常！");
                        }
                    });

                } else {
                    alert("更新失败! 错误代码:" + result.resultCode);
                }
            },
            error : function() {
                alert("异常！");
            }
        });
    }



    function comeb() {
        window.location.href="login.jsp";
    }
</script>
</html>
