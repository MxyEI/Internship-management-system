<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位管理</title>
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
</head>
<body style="margin: 1px;" id="ff">
	<div id="tb">
		<div>
			<a onclick="openJobAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				onclick="openJobModifyDialog()" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true">修改</a> <a onclick="deleteJob()"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;岗位名称：&nbsp;<input type="text" id="jobname" size="20"
				onkeydown="if(event.keyCode==13) searchJob()" />&nbsp; <a
				onclick="searchJob()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<table id="dg"></table>
	<div id="dlg" class="easyui-dialog"
		style="width: 850px; height: 555px; padding: 10px 20px; position: relative; z-index: 1000;"
		closed="true" buttons="#dlg-buttons">
		<div class="middle-box text-center loginscreen  animated fadeInDown">
			<div>
				<form id="jobForm" method="post" style="">
					<table class="table table-hover table-condensed">
						<tr>
							<th>招聘岗位</th>
							<td><input name="name" type="text"
								class="easyui-validatebox" id="name"
								data-options="required:true"> <input id="jobId"
								name="id" hidden></td>
						</tr>
						<tr>
							<th>专业要求</th>
							<td><input name="major" type="text"
								class="easyui-validatebox" data-options="required:true"
								id="major"></td>
						</tr>
						<tr>
							<th>需求人数</th>
							<td><input name="num" type="text" id="num"
								class="easyui-numberbox" data-options="min:0"></td>
						</tr>
						<tr>
							<th>工作地点</th>
							<td><input name="address" type="text" id="address"
								class="easyui-validatebox" data-options="required:true"></td>
						</tr>
						<tr>
							<th>学历要求</th>
							<td><input name="degree" type="text" id="degree"
								class="easyui-validatebox" data-options="required:true"></td>
						</tr>
						<tr>
							<th>工资</th>
							<td><input name="money" type="text" id="money"
								class="easyui-validatebox" data-options="required:true"></td>
						</tr>
						<tr>
							<th>状态</th>
							<td><span class="radioSpan" id="lapse"> <input
									type="radio" name="lapse" value="false">生效</input> <input
									type="radio" name="lapse" value="true">失效</input>
							</span>
						</tr>
					</table>
					职位描述
					<textarea id="jobDescribe"
						style="width: 780px; visibility: hidden;"></textarea>
				</form>
			</div>
		</div>
	</div>
	<div id="dlg-buttons">
		<a onclick="saveJob()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a onclick="closeJobDialog()" class="easyui-linkbutton"
			iconCls="icon-cancel">关闭</a>
	</div>
</body>
<script type="text/javascript">
	/* 查询的是公司对应的岗位，所以需要先获取公司id */
	var url = "${pageContext.request.contextPath}/jobs";
	var method;
	var jobDescribe;
	$(function() {
		dataGrid = $('#dg').datagrid({
			url : '${pageContext.request.contextPath}/jobs/datagrid',
			method : "GET",
			fit : false,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'gmtCreate',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			queryParams : {
				companyId : getCookie("id")
			},
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'jobDescribe',
				title : '岗位详情',
				width : 150,
				sortable : true,
				hidden : true
			}, {
				field : 'name',
				title : '岗位名称',
				width : 150,
				sortable : true
			}, {
				field : 'major',
				title : '专业要求',
				width : 150,
				sortable : true
			}, {
				field : 'num',
				title : '需求人数',
				width : 100,
				sortable : true
			}, {
				field : 'address',
				title : '工作地点',
				width : 150,
				sortable : true
			}, {
				field : 'major',
				title : '学历要求',
				width : 150,
				sortable : true
			}, {
				field : 'money',
				title : '工资',
				width : 150,
				sortable : true
			}, {
				field : 'lapse',
				title : '失效',
				width : 150,
				sortable : true
			}, {
				field : 'gmtCreate',
				title : '创建时间',
				width : 150,
				sortable : true,
			}, {
				field : 'gmtModify',
				title : '修改时间',
				width : 200,
				sortable : true
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
		jobDescribe = KingEditor.create('textarea[id="jobDescribe"]', {
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

	/* 查找岗位 */
	function searchJob() {
		$("#dg").datagrid('load', {
			"name" : $("#jobname").val(),
		});
	}

	/* 删除岗位 */
	function deleteJob() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		$.messager.confirm("系统提示", "您确认要删除这</font>条数据吗？", function(r) {
			if (r) {
				$.ajax({
					type : "DELETE",
					url : url + "/" + selectedRows[0].id,
					success : function(result) {
						if (result.resultCode == 200) {
							$.messager.alert("系统提示", "数据已成功删除！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert(result.messager);
						}

						;
					},
					error : function() {
						$.messager.alert("ERROR！");
					}
				});
			}
		});

	}

	/* 打开岗位添加对话框 */
	function openJobAddDialog() {
		jobDescribe.html('');
		jobDescribe.edit.setHeight(jobDescribe.edit.doc.body.scrollHeight);
		method = "POST";
		$("#dlg").dialog("open").dialog("setTitle", "添加岗位");
	}

	/* 保存岗位信息 */
	function saveJob() {
		var data = {
			"id" : $("#jobId").val(),
			"companyId" : getCookie("id"),
			"name" : $("#name").val(),
			"major" : $("#major").val(),
			"num" : $("#num").val(),
			"jobDescribe" : jobDescribe.html(),
			"address" : $("#address").val(),
			"degree" : $("#degree").val(),
			"money" : $("#money").val(),
			"lapse" : $("input[name='lapse']:checked").val(),
		};
		$.ajax({
			type : method,
			url : url,
			data : data,
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

	/* 打开岗位修改对话框 */
	function openJobModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "修改岗位");
		$('#jobForm').form('load', row);
		jobDescribe.html(row.jobDescribe);
		jobDescribe.edit.setHeight(jobDescribe.edit.doc.body.scrollHeight);
		method = "PATCH";
		$("#jobId").val(row.id);
	}

	function formatHref(val, row) {
		return "<a href='${pageContext.request.contextPath}/views/jobDetail.jsp?id="
				+ row.id + "' target='_blank'>查看详情</a>";
	}

	/* 重置对话框的值 */
	function resetValue() {
		$("#jobId").val("");
		$("#name").val("");
		$("#major").val("");
		$("#num").val("");
		$("#address").val("");
		$("#degree").val("");
		$("#money").val("");
		jobDescribe.html();
		jobDescribe.edit.setHeight(jobDescribe.edit.doc.body.scrollHeight);
	}

	function closeJobDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</html>