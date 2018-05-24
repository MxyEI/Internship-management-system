<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训计划</title>
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
			<form id="trainForm" method="post" style="">
				<table class="table table-hover table-condensed">
					<tr>
						<th>培训主题</th>
						<td><input name="title" type="text"
							class="easyui-validatebox" id="title"
							data-options="required:true"></td>
					</tr>
					<tr>
						<th>讲师</th>
						<td><input name="teacher" type="text"
							class="easyui-validatebox" data-options="required:true"
							id="teacher"></td>
					</tr>
					<tr>
						<th>场地</th>
						<td><input name="address" type="text" id="address"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>人数</th>
						<td><input name="number" type="text" id="number"
							class="easyui-validatebox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>开始时间</th>
						<td><input name="startTime" type="text" readonly="readonly"
							id="startTime"></td>
					</tr>
					<tr>
						<th>截至时间</th>
						<td><input name="endTime" type="text" readonly="readonly"
							id="endTime"></td>
					</tr>
				</table>
				培训内容
				<textarea id="content" style="visibility: hidden;"></textarea>
				<input type="button" name="button" value="保存" class="button" id="bc"
					onclick="saveTrain();" style="margin: 10px"> <input
					name="id" type="text" id="id" hidden>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	var content;
	var companyId;
	$(function() {
		content = KindEditor.create('textarea[id="content"]', {
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
		setCompanyId();
		//未获取到所在公司的id，退出。
		if (isNull(companyId)) {
			return;
		}
		setInfo();
	});
	function setInfo() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/trains/company/"
					+ companyId,
			success : function(result) {
				if (result.resultCode == 200) {
					$('#id').val(result.data.data.id);
					$('#companyId').val(companyId);
					$('#title').val(result.data.data.title);
					$('#teacher').val(result.data.data.teacher);
					$('#address').val(result.data.data.address);
					$('#number').val(result.data.data.number);
					$('#startTime').val(result.data.data.startTime);
					$('#endTime').val(result.data.data.endTime);
					content.html(result.data.data.content);
					content.edit.setHeight(content.edit.doc.body.scrollHeight);
				}
			},
			error : function() {
				alert("您已被多家单位批准，培训计划暂失效");
			}
		});
	}

	function saveTrain() {
		var method;
		var id = $("#id").val();
		console.log("培训计划的id:"+id);
		if (isNull(id)) {
			method = "POST";
		} else {
			method = "PATCH";
		}
		var data = {
			"id" : $("#id").val(),
			"companyId" : companyId,
			"title" : $("#title").val(),
			"teacher" : $("#teacher").val(),
			"address" : $("#address").val(),
			"number" : $("#number").val(),
			"startTime" : $("#startTime").val(),
			"endTime" : $("#endTime").val(),
			"content" : content.html(),
		};
		$.ajax({
			type : method,
			url : "${pageContext.request.contextPath}/trains",
			data : data,
			success : function(result) {
				if (result.resultCode == 200) {
					alert("更新成功！");
					$("#id").val(id);

				} else {
					alert("更新失败! 错误代码:" + result.resultCode);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	$('#startTime').click(function() {
		var maxDate = $('#endTime').val();
		WdatePicker({
			el : this,
			readOnly : true,
			dateFmt : 'yyyy-MM-dd',
			maxDate : maxDate
		});
	});

	$('#endTime').click(function() {
		var minDate = $('#startTime').val();
		if (isNull(minDate)) {
			alert("请先选择开始日期");
			return;
		}
		WdatePicker({
			el : this,
			readOnly : true,
			dateFmt : 'yyyy-MM-dd',
			minDate : minDate
		});
	});

	/* 根据用户类型来获取companyId */
	function setCompanyId() {
		if (getCookie("usertype") == "student") {
			//学生的话从用户岗位关联表中查companyId
			getCompanyId();
			$("#bc").hide();
			$("#title").attr("disabled", "true");
			$("#teacher").attr("disabled", "true");
			$("#number").attr("disabled", "true");
			$("#address").attr("disabled", "true");
			$("#startTime").attr("disabled", "true");
			$("#endTime").attr("disabled", "true");
			content.readonly(true);
		} else {
			//公司的话直接从cookie中查
			companyId = getCookie("id");
		}
	}

	function getCompanyId() {
		$.ajax({
			type : "GET",
			async : false,
			url : "${pageContext.request.contextPath}/companys/user/"
					+ getCookie("id"),
			success : function(result) {
				if (result.resultCode == 200) {
					if (result.data != null && result.data.data != null
							&& result.data.data.id != null) {
						companyId = result.data.data.id;
					} else {
						alert("不存在被公司审核通过的岗位");
						window.location.href = "${pageContext.request.contextPath}/views/companyRec.jsp";
					}
				} else {
					alert("不存在被公司审核通过的岗位");
					window.location.href = "${pageContext.request.contextPath}/views/companyRec.jsp";
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	};
</script>
</html>