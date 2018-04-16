<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价</title>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/kindeditor-all.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/lang/zh_CN.js"></script>
</head>
<body style="margin: 1px;" id="ff">
	<div class="middle-box text-center loginscreen  animated fadeInDown"
		style="float: left; margin-left: 100px">
		<div>
			<form id="userForm">
				<table class="table table-hover table-condensed">
					<tr>
						<th>用户名</th>
						<td><input name="username" type="text" id="username"
							class="easyui-validatebox" data-options="required:true" disabled></td>
					</tr>
					<tr>
						<th>真实姓名</th>
						<td><input name="realname" type="text" id="realname" disabled></td>
					</tr>
					<tr>
						<th>公司评分</th>
						<td><input name="cscore" id="cscore" disabled /></td>
					</tr>
					<tr>
						<th>教师评分</th>
						<td><input name="tscore" id="tscore" disabled /></td>
					</tr>
					<tr>
						<th>总分</th>
						<td><input name="sum" id="sum" disabled /></td>
					</tr>
				</table>
				公司评语
				<textarea id="cComment" style="width: 780px; visibility: hidden;"></textarea>
				教师评语
				<textarea id="tComment" style="width: 780px; visibility: hidden;"></textarea>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	var cComment;
	var tComment;
	$(function() {
		cComment = KindEditor.create('textarea[id="cComment"]', {
			items : []
		});
		tComment = KindEditor.create('textarea[id="tComment"]', {
			items : []
		});
		setUserInfo();
	})

	function setUserInfo() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/scores/user/"
					+ getCookie("id"),
			success : function(result) {
				if (result.resultCode == 200 && result.data != null
						&& result.data.user != null) {
					$('#username').val(result.data.user.username);
					$('#realname').val(result.data.user.realname);
					$('#tscore').val(result.data.tscore);
					$('#cscore').val(result.data.cscore);
					$('#sum').val(result.data.sum);
					//无语了，为啥又变成ccomment,在公司页面是CC，在后端是cC
					cComment.html(result.data.ccomment);
					tComment.html(result.data.tcomment);
					cComment.edit
							.setHeight(cComment.edit.doc.body.scrollHeight);
					tComment.edit
							.setHeight(tComment.edit.doc.body.scrollHeight);
					cComment.readonly(true);
					tComment.readonly(true);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}
</script>
</html>