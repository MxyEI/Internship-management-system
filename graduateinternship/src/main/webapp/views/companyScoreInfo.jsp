<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩信息</title>
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
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/kindeditor-all.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/lang/zh_CN.js"></script>
</head>

<body style="margin: 1px;" id="ff">
	<div id="tb">
		<div>
			&nbsp; 真实姓名：&nbsp;<input type="text" id="realname" size="20"
				onkeydown="if(event.keyCode==13) searchUserScore()" />&nbsp;&nbsp;<a
				onclick="searchUserScore()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<table id="dg"></table>
	<div id="dlg" class="easyui-dialog"
		style="width: 850px; height: 555px; padding: 10px 20px; position: relative; z-index: 1000;"
		closed="true" buttons="#dlg-buttons">
		<div class="middle-box text-center loginscreen  animated fadeInDown">
			<div>
				<form id="scoreForm">
					<table class="table table-hover table-condensed">
						<tr>
							<th>用户名</th>
							<td><input name="username" type="text"
								class="easyui-validatebox" id="username"
								data-options="required:true" disabled> <input
								id="scoreId" name="id" hidden></td>
						</tr>
						<tr>
							<th>真实姓名</th>
							<td><input name="trealname" type="text"
								class="easyui-validatebox" data-options="required:true" disabled
								id="trealname"></td>
						</tr>
						<tr>
							<th>成绩</th>
							<td><input name="cscore" id="cscore" type="text"
								class="easyui-numberbox"></td>
						</tr>
						<input name="scoreId" type="text" id="scoreId" hidden>
					</table>
					教师评语
					<textarea id="tComment" style="width: 780px; visibility: hidden;"></textarea>
					公司评语
					<textarea id="cComment"
						style="width: 780px; height: 200px; visibility: hidden;"></textarea>
				</form>
			</div>
		</div>
	</div>

	<div id="dlg-buttons">
		<a onclick="saveScore()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a onclick="closeScoreDialog()" class="easyui-linkbutton"
			iconCls="icon-cancel">关闭</a>
	</div>
</body>
<script type="text/javascript">
	var cComment;
	var tComment;
	$(function() {
		dataGrid = $('#dg').datagrid({
			url : '${pageContext.request.contextPath}/scores/datagrid',
			method : "GET",
			fit : false,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			queryParams : {
				//初始化时传入公司id
				companyId : getCookie("id")
			},
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'user.username',
				title : '用户名',
				width : 150
			}, {
				field : 'user.realname',
				title : '真实姓名',
				width : 150
			}, {
				field : 'cscore',
				title : '公司评分',
				width : 150,
				sortable : true
			}, {
				field : 'tscore',
				title : '教师评分',
				width : 150,
				sortable : true
			}, {
				field : 'sum',
				title : '总分',
				width : 150,
				sortable : true
			}, {
				field : 'gmtModify',
				title : '修改时间',
				width : 200,
				sortable : true
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					return formatHref(value, row);
				}
			} ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
		cComment = KindEditor.create('textarea[id="cComment"]', {
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
		tComment = KindEditor.create('textarea[id="tComment"]', {
			items : []
		});
	});

	/* 保存成绩 */
	function saveScore() {
		if (!checkBeforeSubmit()) {
			return;
		}
		$.ajax({
			type : "PATCH",
			url : "${pageContext.request.contextPath}/scores",
			data : {
				"id" : $("#scoreId").val(),
				"cscore" : $("#cscore").val(),
				"cComment" : cComment.html()
			},
			success : function(result) {
				if (result.resultCode == 200) {
					$.messager.alert("系统提示", "保存成功");
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
					resetValue();
				} else {
					$.messager.alert("系统提示", "操作失败");
					$("#dlg").dialog("close");
					resetValue();
				}
				;
			},
			error : function() {
				$.messager.alert("系统提示", "操作失败");
			}
		});
	}

	function formatHref(val, row) {
		var str = new StringBuffer();
		str.append("<a onclick='openScoreModifyDialog(").append(row.id);
		str.append(")' class='easyui-linkbutton' iconCls='icon-ok'>");
		str.append("评价");
		str.append("</a>");
		return str.toString();
	}

	/* 查询用户成绩 */
	function searchUserScore() {
		$("#dg").datagrid('load', {
			"user.realname" : $("#realname").val(),
			"companyId" : getCookie("id")
		});
	}

	/* 打开修改成绩页面 */
	function openScoreModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "评价");
		$("#scoreId").val(row.id);
		$("#username").val(row.user.username);
		$("#trealname").val(row.user.realname);
		$("#cscore").val(row.cscore);
		/* 奇怪，字段明明是cComment，debug看了后台传给来的也是cComment，怎么到了前端就变成了CComment了 */
		cComment.html(row.CComment);
		tComment.html(row.TComment);
		tComment.readonly(true);
		cComment.edit.setHeight(cComment.edit.doc.body.scrollHeight);
		tComment.edit.setHeight(tComment.edit.doc.body.scrollHeight);
	}

	/* 重置对话框的值 */
	function resetValue() {
		$("#scoreId").val("");
		$("#username").val("");
		$("#trealanme").val("");
		$("#cscore").val("");
		cComment.html("");
		tComment.html("");
	}

	function closeScoreDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	/* 成绩保存前校验 */
	function checkBeforeSubmit() {
		var num = $("#cscore").val();
		if (num<0||num>100) {
			alert("成绩应该在0~100之间");
			return false;
		}
		return true;
	}
</script>

</html>