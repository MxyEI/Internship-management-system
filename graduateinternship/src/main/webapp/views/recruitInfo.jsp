<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实习单位招聘信息</title>
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
			<form id="recruitForm" method="post" style="">
				<table class="table table-hover table-condensed">
					<tr>
						<th>宣讲会地址</th>
						<td><input name="address" type="text"
							class="easyui-validatebox" id="address"
							data-options="required:true"></td>
					</tr>
					<tr>
						<th>宣讲会及招聘开始时间</th>
						<td><input name="startTime" type="text" readonly="readonly"
							id="startTime"></td>
					</tr>
					<tr>
						<th>招聘截止时间</th>
						<td><input name="endTime" type="text" readonly="readonly"
							id="endTime"></td>
					</tr>
					<tr>
						<th>联系人</th>
						<td><input name="contractUser" type="text" id="contractUser"></td>
					</tr>
					<tr>
						<th>联系电话</th>
						<td><input name="contractNumber" type="text"
							class="easyui-numberbox" id="contractNumber"></td>
					</tr>
					<tr>
						<th>联系邮箱</th>
						<td><input name="contractEmail" type="text" validType='email'
							id="contractEmail"></td>
					</tr>
					<tr>
						<th>联系地址</th>
						<td><input name="contractAddress" type="text"
							id="contractAddress"></td>
					</tr>
					<tr>
						<th>简历投递地址</th>
						<td><input name="sendResumeUrl" type="text"
							id="sendResumeUrl"></td>
					</tr>
					<tr>
						<th>状态</th>
						<td><span class="radioSpan" id="lapse"> <input
								type="radio" name="lapse" value="false">生效</input> <input
								type="radio" name="lapse" value="true">失效</input>
						</span>
					</tr>
				</table>
				招聘简章
				<textarea id="motoley" style="visibility: hidden;"></textarea>
				<input type="button" name="button" value="保存" class="button" id="bc"
					onclick="saveInfo();" style="margin: 10px"> <input
					name="id" type="text" id="id" hidden>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		//详情编辑器
		motoley = KindEditor.create('textarea[id="motoley"]', {
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
		setInfo();
	});

	function setInfo() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/recruits/"
					+ getCookie("id"),
			success : function(result) {
				if (result.resultCode == 200) {
					$('#id').val(result.data.currentRecruit.id);
					$('#address').val(result.data.currentRecruit.address);
					$('#startTime').val(result.data.currentRecruit.startTime);
					$('#endTime').val(result.data.currentRecruit.endTime);
					motoley.html(result.data.currentRecruit.motoley);
					motoley.edit.setHeight(motoley.edit.doc.body.scrollHeight);
					$('#contractUser').val(
							result.data.currentRecruit.contractUser);
					$('#contractNumber').val(
							result.data.currentRecruit.contractNumber);
					$('#contractEmail').val(
							result.data.currentRecruit.contractEmail);
					$('#contractAddress').val(
							result.data.currentRecruit.contractAddress);
					$('#sendResumeUrl').val(
							result.data.currentRecruit.sendResumeUrl);
					$(
							"input[name='lapse'][value="
									+ result.data.currentRecruit.lapse + "]")
							.attr("checked", true);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function saveInfo() {
		var method;
		var id = $('#id').val();
		if (isNull(id)) {
			method = "POST";
		} else {
			method = "PATCH";
		}
		var data = {
			"id" : id,
			"companyId" : getCookie("id"),
			"address" : $("#address").val(),
			"startTime" : $("#startTime").val(),
			"endTime" : $("#endTime").val(),
			"address" : $("#address").val(),
			"motoley" : motoley.html(),
			"contractUser" : $("#contractUser").val(),
			"contractNumber" : $("#contractNumber").val(),
			"contractEmail" : $("#contractEmail").val(),
			"sendResumeUrl" : $("#sendResumeUrl").val(),
			"contractAddress" : $("#contractAddress").val(),
			"lapse" : $("input[name='lapse']:checked").val()
		};
		$.ajax({
			type : method,
			url : "${pageContext.request.contextPath}/recruits",
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
			dateFmt : 'yyyy-MM-dd HH:mm',
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
			dateFmt : 'yyyy-MM-dd HH:mm',
			minDate : minDate
		});
	});
</script>
</html>