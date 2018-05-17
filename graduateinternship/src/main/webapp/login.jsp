<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>实习管理系统 - 登录</title>
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
</head>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
			<div>
				<h1 class="logo-name">PMS</h1>
			</div>
			<h3>欢迎使用 实习管理系统</h3>

			<form class="m-t" role="form" id="userlogin" method="post"
				name="userlogin" onsubmit="return false" action="##">
				<div class="form-group">
					<input type="email" class="form-control" placeholder="用户名"
						name="username" id="username">

				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码"
						name="password" id="password"
						onkeydown="if(event.keyCode==13) login()">

				</div>
				<button type="button" class="btn btn-primary block full-width m-b"
					onclick="login();">登 录</button>
				<a href="/register.jsp">我想注册实习单位，请点这里</a>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		var usertype = $("#usertype").val();
		if (username == null || username == "") {
			alert("用户名或学号不能为空！");
			return;
		}
		if (password == null || password == "") {
			alert("密码不能为空！");
			return;
		}
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/users/cookie",
			data : $('#userlogin').serialize(),
			success : function(result) {
				if (result.resultCode == 200) {
					setCookie("id", result.data.currentUser.id);
					setCookie("username", result.data.currentUser.username);
					setCookie("usertype", result.data.currentUser.usertype);
					loginRedirect();
				} else {
					alert(result.message);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function loginRedirect() {
		var usertype = getCookie("usertype");
		switch (usertype) {
		case 'admin':
			window.location.href = "${pageContext.request.contextPath}/adminIndex.jsp";
			break;
		case 'company':
			window.location.href = "${pageContext.request.contextPath}/companyIndex.jsp";
			break;
		default:
			window.location.href = "${pageContext.request.contextPath}/index.jsp";
		}
	}
</script>
</html>
