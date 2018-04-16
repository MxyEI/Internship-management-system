<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body style="margin: 1px;">
	<div id="tb">
		<div>
			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openUserModifyDialog()" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteUser()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;用户名：&nbsp;<input type="text" id="s_username" size="20"
				onkeydown="if(event.keyCode==13) searchUser()" /> <a
				href="javascript:searchUser()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<table id="dg"></table>
	<div id="dlg" class="easyui-dialog"
		style="width: 620px; height: 250px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm">
			<table cellspacing="8px">
				<input id="id" name="id" hidden>
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="username" name="username"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" id="password" name="password"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>用户类型：</td>
					<td><select id="usertype" class="easyui-combobox"
						style="width: 145px" data-options="editable:false"
						panelHeight="auto">
							<option value="student">学生</option>
							<option value="company">公司</option>
							<option value="teacher">教师</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeUserDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
<script type="text/javascript">
	var method;
	function searchUser() {
		$("#dg").datagrid('load', {
			"username" : $("#s_username").val()
		});
	}
	function deleteUser() {
		var selectedRows = $("#dg").datagrid('getChecked');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要删除这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.ajax({
					type : "DELETE",
					url : "${pageContext.request.contextPath}/users/" + ids,
					success : function(result) {
						if (result.resultCode == 200) {
							$.messager.alert("系统提示", "数据已成功删除！");
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert("系统提示", "数据删除失败！");
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

	function openUserAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
		method = "POST";
	}

	function saveUser() {
		var username = $("#username").val();
		var password = $("#password").val();
		var id = $("#id").val();
		var data = {
			"id" : id,
			"password" : password,
			"username" : username,
			"usertype" : $("#usertype").combobox('getValue')
		}
		if (!checkAdd()) {
			return;
		}
		$.ajax({
			type : method,
			url : "${pageContext.request.contextPath}/users",
			data : data,
			success : function(result) {
				if (result.resultCode == 200) {
					$.messager.alert("系统提示", "保存成功");
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
					resetValue();
				} else {
					alert(result.message);
				}
				;
			},
			error : function() {
				$.messager.alert("系统提示", "操作失败");
			}
		});
	}

	function openUserModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑用户信息");
		$('#fm').form('load', row);
		$("#password").val("******");
		$("#id").val(row.id);
		$('#usertype').combobox('setValue', row.usertype);
		method = "PATCH";
	}

	function resetValue() {
		$("#username").val("");
		$("#password").val("");
		$('#usertype').combobox('setValue', "student");
	}

	function closeUserDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	function checkAdd() {
		if (isNull($("#username").val())) {
			alert("用户名必填");
			return false;
		} else if (isNull($("#password").val())) {
			alert("密码必填");
			return false;
		}
		return true;
	}

	$(function() {
		$('#dg').datagrid({
			url : '${pageContext.request.contextPath}/users/datagrid',
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
			frozenColumns : [ [ {
				field : 'cb',
				title : '',
				checkbox : true,
				align : 'center',
				width : 150,
			}, {
				field : 'id',
				title : '编号',
				width : 150,
			} ] ],
			columns : [ [ {
				field : 'username',
				title : '用户名',
				width : 150,
				sortable : true,
			}, {
				field : 'usertype',
				title : '用户类型',
				width : 150,
				sortable : true,
			}, {
				field : 'gmtCreate',
				title : '创建时间',
				width : 150,
				sortable : true,
			}, {
				field : 'gmtModify',
				title : '修改时间',
				width : 150,
				sortable : true,
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
	});
</script>
</html>