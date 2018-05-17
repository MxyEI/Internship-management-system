<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的申请信息</title>
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
</head>
<body style="margin: 1px;" id="ff">
	<div id="tb">
		<div>
			&nbsp;岗位名称：&nbsp;<input type="text" id="jobname" size="20"
				onkeydown="if(event.keyCode==13) searchUserJob()" />&nbsp;
			实习单位名称：&nbsp;<input type="text" id="comname" size="20"
				onkeydown="if(event.keyCode==13) searchUserJob()" />&nbsp;&nbsp;<a
				onclick="searchUserJob()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<table id="dg"></table>
</body>
<script type="text/javascript">
	$(function() {
		dataGrid = $('#dg').datagrid({
			url : '${pageContext.request.contextPath}/userJobs/datagridwithmyapply/'+getCookie("id"),
			method : 'GET',
			fit : false,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'success',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			queryParams : {
				userid : getCookie("id")
			},
			frozenColumns : [ [ {
				field : 'dd',
				title : '',
				width : 150,
				hidden : true
			}, {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			},{
                field : 'user.id',
                title : '用户编号',
                width : 150,
                hidden : true
            } ] ],
			columns : [ [ {
                field : 'company.name',
                title : '实习单位名称',
                width : 150,
                sortable : true
            }, {
				field : 'job.name',
				title : '岗位名称',
				width : 150,
				sortable : true
			}, {
				field : 'success',
				title : '申请状态',
				width : 100,
				sortable : true,
                formatter : function(value, row, index) {

                    if(value==true){
                        return "通过"
					}else{
                        //console.log(row);
                        return "不通过"
					}
                }
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
				    console.log(row);
					return seedetail(value, row);
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
	});

	//查看申请详情
    function seedetail(val,row) {
        return "<a href='${pageContext.request.contextPath}/views/jobDetail.jsp?jId="
            + row.job.id + "&vId="+row.id+"' target='_blank'>查看详情</a>";
    }

	function searchUserJob() {
		$("#dg").datagrid('load', {
			"job.name" : $("#jobname").val(),
			"company.name" : $("#comname").val()
		});
	}
</script>

</html>