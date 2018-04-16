<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
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
</head>
<body style="margin: 1px;" id="ff">
	<div class="middle-box text-center loginscreen  animated fadeInDown"
		style="float: left; margin-left: 100px">
		<div>
			<form id="userForm">
				<table class="table table-hover table-condensed">
					<tr>
						<th>原密码</th>
						<td><input name="password" type="text" id="password"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>新密码</th>
						<td><input name="npassword" type="text" id="npassword"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>确认新密码</th>
						<td><input name="rpassword" type="text" id="rpassword"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
				</table>
				<input type="button" name="button" value="保存" class="button" id="bc"
					onclick="save();" style="margin: 10px">
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	function save() {
		if (!checkPwd()) {
			return;
		}
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/users/verifyPwd",
			data : {
				"id" : getCookie("id"),
				"password" : $("#password").val()
			},
			success : function(result) {
				if (result.resultCode == 200) {
					saveInfo();
				} else {
					alert(result.message);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function saveInfo() {
		$.ajax({
			type : "PATCH",
			url : "${pageContext.request.contextPath}/users",
			data : {
				"id" : getCookie("id"),
				"password" : $("#npassword").val()
			},
			success : function(result) {
				if (result.resultCode == 200) {
					alert("更新成功！");
				} else {
					alert("更新失败! 错误代码:" + result.resultCode);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function checkPwd() {
		if ($("#npassword").val() != $("#rpassword").val()) {
			alert("两次密码不一致");
			return false;
		}
		return true;
	}
</script>

</html>