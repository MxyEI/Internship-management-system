<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/recruitDetail.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/kindeditor-all.js"></script>
</head>
<body class="job-details"
	style="display: flex; flex-direction: column; height: auto; min-height: 100%;">
	<div class="container" style="flex: 1 1 auto;">
		<div class="inner">
			<div class="pub-details" id="data_details">
				<div class="main">
					<div class="main-inner">
						<div class="details-head">
							<h1 class="dh-tit" style="font-weight: normal;">
								<p id="company_name"></p>
								<p id="job_name"></p>
							</h1>
							<h1 style="float: right; font-size: 20px" id="spzt">
								审批状态：<span style="color: red" id="success"></span>
							</h1>
							<div class="tag-list">
								<span class="tag-item pub-orange-text" id="job_money"></span> <span
									class="tag-item" id="job_address"></span> <span
									class="tag-item" id="job_degree"></span>

								<button class="pub-btn btn-normal btn-primary fr"
									onclick="apply()" id="apply" style="letter-spacing: 0">申请</button>
								<button class="pub-btn btn-normal btn-primary fr"
									onclick="sendResume()"
									style="letter-spacing: 0; margin: 0px 20px">投递简历</button>
							</div>
							<p class="publish-time" id="recruit_gmtCreate"></p>
						</div>
						<div class="detail-module">
							<div class="dm-tit">职位描述</div>
							<div class="dm-cont">
								<textarea id="jobDescribe"
									style="font-size: 16px; line-height: 175%; font-family: 宋体; visibility: hidden;"></textarea>
							</div>
						</div>
						<div class="detail-module">
							<div class="dm-tit">公司简介</div>
							<div class="dm-cont">
								<textarea id="company_about"
									style="font-size: 16px; line-height: 175%; font-family: 宋体; visibility: hidden;"></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="side">
					<div class="side-inner">
						<div class="company-infor clearfix">
							<a class="ci-link">
								<p class="ci-text" id="rcompany_name"></p>
							</a>
							<dl class="ci-module">
								<dd class="ci-cont">
									<p class="ci-text" id="company_world"></p>
									<p class="ci-text" id="company_scale"></p>
									<p class="ci-text" id="company_address"></p>
								</dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var detailDate;
	var jobDescribe_editor;
	var about_editor;
	//岗位id
	var jId = getQueryStringByName("jId");

	$(function() {
		jobDescribe_editor = KindEditor.create('textarea[id="jobDescribe"]', {
			items : []
		});
		about_editor = KindEditor.create('textarea[id="company_about"]', {
			items : []
		});
		checkIsSelected();
		if (!checkId()) {
			return;
		}
		getDate();
	});
	/* 检验是否已经申请该岗位 */
	function checkIsSelected() {
	    var userid = getCookie("id");
		$.ajax({
			type : "GET",
			async : false,
			url : "${pageContext.request.contextPath}/userJobs/"
					+ userid,
			success : function(result) {
				if (result.resultCode == 200 && result.data != null
						&& result.data.data != null
						&& result.data.data.jobId != null) {
					if (isNull(jId) || result.data.data.jobId == jId) {
						jId = result.data.data.jobId + "";
						setSuccess(result.data.data.success);
						$("#apply").text("取消申请");
					} else if (isNotNull(jId)) {
						//$("#apply").hide();
						$("#spzt").hide();
					}
				} else {
					$("#spzt").hide();
				}
			},
			error : function() {
				$("#spzt").hide();
			}
		});
	};

	/* 检验是否获取到岗位的id */
	function checkId() {
		if (isNull(jId)) {
			alert("未申请任何岗位,请先申请");
			window.location.href = "${pageContext.request.contextPath}/views/companyRec.jsp";
			return false;
		}
		return true;
	}
	/* 获取岗位信息 */
	function getDate() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/companys/job/" + jId,
			success : function(result) {
				if (result.resultCode == 200) {
					detailDate = result.data.data;
					setDate();
				} else {
					alert("错误代码：" + result.resultCode);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	};

	/* 填充页面数据 */
	function setDate() {
		$("#rcompany_name").text(detailDate.name);
		$("#company_world").text("领域：" + detailDate.world);
		$("#company_scale").text("规模：" + detailDate.scale);
		$("#company_address").text("地址：" + detailDate.address);
		$("#company_name").text("宣讲单位：" + detailDate.name);
		$("#job_name").text(detailDate.jobs[0].name);
		$("#job_money").text(detailDate.jobs[0].money);
		$("#job_address").text(detailDate.jobs[0].address);
		$("#job_degree").text(detailDate.jobs[0].degree);
		about_editor.html(detailDate.about);
		about_editor.edit.setHeight(about_editor.edit.doc.body.scrollHeight);
		jobDescribe_editor.html(detailDate.jobs[0].jobDescribe);
		jobDescribe_editor.edit
				.setHeight(jobDescribe_editor.edit.doc.body.scrollHeight);
		if (isNotNull(detailDate.recruit.gmtCreate)) {
			$("#recruit_gmtCreate")
					.text("发布时间：" + detailDate.recruit.gmtCreate);
		}
	}

	/* 投递简历 */
	function sendResume() {
		if (isNotNull(detailDate.recruit.sendResumeUrl)) {
			window.open(detailDate.recruit.sendResumeUrl,"_blank");
		} else {
			alert("该公司未设置简历投递地址")
		}
	}

	/* 申请，取消申请 */
	function apply() {
		var method = "POST";
		var url = new StringBuffer();
		url.append("${pageContext.request.contextPath}/userJobs");
		if ($("#apply").text() != "申请") {
			method = "DELETE";
			url.append("/").append(getCookie("id"));
		}
		$.ajax({
			type : method,
			url : url,
			data : {
				"userid" : getCookie("id"),
				"jobId" : jId
			},
			success : function(result) {
				if (result.resultCode == 200) {
					if ($("#apply").text() != "申请") {
						$("#apply").text("申请");
					} else {
						$("#apply").text("取消申请");
					}
				} else {
					alert("申请已被实习单位批准，不能取消申请");
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	}

	function setSuccess(success) {
		if (success == null) {
			$("#success").text("未处理");
		} else if (success) {
			$("#success").text("成功");
		} else {
			$("#success").text("拒绝");
		}
	}
</script>
</html>