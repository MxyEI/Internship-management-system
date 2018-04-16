<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
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
			<form id="companyForm">
				<table class="table table-hover table-condensed">
					<tr>
						<th>公司名称</th>
						<td><input name="name" type="text" class="easyui-validatebox"
							id="name" data-options="required:true"></td>
					</tr>
					<tr>
						<th>领域</th>
						<td><input name="world" type="text"
							class="easyui-validatebox" data-options="required:true"
							id="world"></td>
					</tr>
					<tr>
						<th>规模</th>
						<td><input name="scale" type="text" id="scale"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>地址</th>
						<td><input name="address" type="text" id="address"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
				</table>
				简介
				<textarea id="about" style="visibility: hidden;"></textarea>
				<input type="button" name="button" value="保存" class="button" id="bc"
					onclick="saveInfo();" style="margin: 10px"> <input
					name="id" type="text" id="id" hidden>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	var about;
	$(function() {
		setInfo();
		about = KindEditor.create('textarea[id="about"]', {
			items : [ 'cut', 'copy', 'paste', 'justifyleft', 'justifycenter',
					'justifyright', 'justifyfull', 'insertorderedlist',
					'insertunorderedlist', 'indent', 'outdent', 'subscript',
					'superscript', 'clearhtml', 'quickformat', 'selectall',
					'|', 'fullscreen', '/', 'formatblock', 'fontname',
					'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', '|', 'table', 'hr', 'emoticons',
					'pagebreak' ]
		});
	});
	function setInfo() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/companys/"
					+ getCookie("id"),
			success : function(result) {
				if (result.resultCode == 200) {
					$('#id').val(result.data.currentCompany.id);
					$('#name').val(result.data.currentCompany.name);
					$('#world').val(result.data.currentCompany.world);
					$('#scale').val(result.data.currentCompany.scale);
					$('#address').val(result.data.currentCompany.address);
					about.html(result.data.currentCompany.about);
					about.edit.setHeight(about.edit.doc.body.scrollHeight);
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
			"name" : $("#name").val(),
			"world" : $("#world").val(),
			"scale" : $("#scale").val(),
			"address" : $("#address").val(),
			"about" : about.html(),
		};
		$.ajax({
			type : "PATCH",
			url : "${pageContext.request.contextPath}/companys",
			data : data,
			success : function(result) {
				if (result.resultCode == 200) {
					alert("更新成功！");
					about.edit.setHeight(about.edit.doc.body.scrollHeight);
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