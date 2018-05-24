<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请信息</title>
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
			&nbsp;学号：&nbsp;<input type="text" id="username" size="20"
				onkeydown="if(event.keyCode==13) searchUser()" />
			<%--&nbsp;真实姓名：&nbsp;<input type="text" id="realname" size="20"--%>
				<%--onkeydown="if(event.keyCode==13) searchUserJob()" />--%>
			&nbsp;&nbsp;<a onclick="searchUser()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<table id="dg"></table>
</body>
<script type="text/javascript">
	$(function() {
		dataGrid = $('#dg').datagrid({
			url : '${pageContext.request.contextPath}/userJobs/datagrid',
			method : 'GET',
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
				field : 'user.username',
				title : '学号',
				width : 150,
				sortable : true
			}, {
				field : 'user.realname',
				title : '真实姓名',
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
                field : 'gmtCreate',
                title : '申请时间',
                width : 150,
                sortable : true
            },{
				field : 'gmtModify',
				title : '修改时间',
				width : 200,
				hidden : true
			}, {
                field : 'status',
                title : '简历信息',
                width : 100,
                formatter : function(value, row, index) {
                    console.log(row);
                    return seejianli(row.user.id);
                }
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
	});
	/* 申请状态 */
	function resume(success) {
		if (success) {
			return "拒绝";
		}
		return "批准";
	}

	function sendResume(success, id,userid,jobid) {
		var existsuc=false;
		if(success==false){
            $.ajax({
                type : "get",
                url : "${pageContext.request.contextPath}/userJobs/uid/"+userid,
                success : function(result) {
                    console.log(result.data.length);
                    for(var i=0;i<result.data.length;i++){
                        if(result.data[i].success==true){
                            console.log("success2::"+result.data[i].success);
                            existsuc=true;
                            break;
						}
					}
                    if(existsuc==false){
                        $.ajax({
                            type : "PATCH",
                            url : "${pageContext.request.contextPath}/userJobs",
                            data : {
                                "id" : id,
                                "userid":userid,
                                "success" : true
                            },
                            success : function(result) {
                                if (result.resultCode == 200) {
                                    $("#dg").datagrid("reload");
                                } else {
                                    $.messager.alert("系统提示", "操作失败");
                                }
                            },
                            error : function() {
                                $.messager.alert("系统提示", "操作失败");
                            }
                        });
                    }else{
                        $.messager.alert("系统提示", "该学生已经存在被批准的单位");
                    }

                },
                error : function() {
                    $.messager.alert("系统提示", "操作失败");
                }
            });
		}else{
            $.ajax({
                type : "PATCH",
                url : "${pageContext.request.contextPath}/userJobs",
                data : {
                    "id" : id,
                    "userid":userid,
                    "success" : !success
                },
                success : function(result) {
                    if (result.resultCode == 200) {
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "操作失败");
                    }
                    ;
                },
                error : function() {
                    $.messager.alert("系统提示", "操作失败");
                }
            });
		}



	}

	function formatHref(val, row) {
        //<a onclick='sendResume(row.success,row.id) class='easyui-linkbutton' iconCls='icon-ok'> resume(row.success)</a><a onclick='seejianli(row)'>查看简历</a>
		var str = new StringBuffer();
		str.append("<a onclick='sendResume(");
		str.append(row.success).append(",").append(row.id).append(",").append(row.user.id).append(",").append(row.job.id);
		str.append(")' class='easyui-linkbutton' iconCls='icon-ok'>");
		str.append(resume(row.success));
		str.append("</a> ");
		return str.toString();
	}

	//查看简历
    function seejianli(id) {
        return "<a href='${pageContext.request.contextPath}/views/jianli.jsp?userid="
            + id + "' target='_blank'>查看简历</a>";
    }

	function searchUser() {
		$("#dg").datagrid('load', {
			"user.username" : $("#username").val(),
//			"user.realname" : $("#realname").val(),
			"companyId":getCookie("id")
		});
	}
</script>

</html>