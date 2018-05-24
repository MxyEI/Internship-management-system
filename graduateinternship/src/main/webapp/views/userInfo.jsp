<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/kindeditor-all.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"
	charset="utf-8"></script>
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
			<form id="userForm" method="post" style="">
				<table class="table table-hover table-condensed">
					<tr>
						<th>用户名</th>
						<td><input name="username" type="text" id="username"
							class="easyui-validatebox" data-options="required:true" disabled></td>
					</tr>
					<tr>
						<th>真实姓名</th>
						<td><input name="realname" type="text" id="realname"></td>
					</tr>
					<tr>
						<th>性别</th>
						<td><span class="radioSpan" id="sex"> <input
								type="radio" name="sex" value="男">男</input> <input type="radio"
								name="sex" value="女">女</input> <input type="radio" name="sex"
								value="保密">保密</input>
						</span>
					</tr>
					<tr>
						<th>出生年月</th>
						<td><input name="birthday" id="birthday"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
							readonly="readonly" /></td>
					</tr>
					<%--<tr>--%>
						<%--<th>联系电话</th>--%>
						<%--<td><input name="phoneNumber" type="text"--%>
							<%--validType='phoneNum' class="easyui-numberbox"--%>
							<%--data-options="min:0" id="phoneNumber"></td>--%>
					<%--</tr>--%>
					<tr>
						<th>email</th>
						<td><input name="email" type="text" validType='email'
							id="email" class="easyui-validatebox"
							data-options="required:true"></td>
					</tr>
				</table>
				<input type="button" name="button" value="保存" class="button" id="bc"
					onclick="saveInfo();" style="margin: 10px">
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	checkCookie();
	setUserInfo();
	function setUserInfo() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/users/"
					+ getCookie("id"),
			success : function(result) {
				if (result.resultCode == 200) {
					$('#username')
							.val(result.data.currentUser.username);
					$('#realname')
							.val(result.data.currentUser.realname);
					$('#email').val(result.data.currentUser.email);
					$('#birthday')
							.val(result.data.currentUser.birthday);
					$('#phoneNumber').val(
							result.data.currentUser.phoneNumber);
					if (isNull(result.data.currentUser.sex)) {
						$("input[name='sex'][value='保密']").attr(
								"checked", true);
					} else {
						$("input[name='sex'][value="
								+ result.data.currentUser.sex
								+ "]").attr("checked", true);
											}
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
	function saveInfo() {
		var data = {
			"id" : getCookie("id"),
			"username" : getCookie("username"),
			"realname" : $("#realname").val(),
			"sex" : $("input[name='sex']:checked").val(),
			"birthday" : $("#birthday").val(),
            "phoneNumber" : $('#phoneNumber').val(),
			"email" : $('#email').val()
		};
		console.log(data);
        $.ajax({
            type : "PATCH",
            url : "${pageContext.request.contextPath}/users",
            data : data,
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
</script>
</html>