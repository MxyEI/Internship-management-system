<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/recruitDetail.css">
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
<body class="career-details"
	style="display: flex; flex-direction: column; height: auto; min-height: 100%;">
	<div class="container" style="flex: 1 1 auto;">
		<div class="inner">
			<div class="pub-details" id="data_details">
				<div class="main">
					<div class="main-inner">
						<div class="detail-module">
							<div class="dm-tit">宣讲会信息</div>
							<div class="dm-cont">
								<div class="dm-text"></div>
								<h1 class="dm-text" style="font-weight: normal;"
									id="company_name"></h1>
								<p class="dm-text" id="recruit_startTime"></p>
								<p class="dm-text">所在学校：江西财经大学</p>
								<p class="dm-text" id="recruit_address"></p>
							</div>
						</div>
						<div class="detail-module">
							<div class="dm-tit">单位简介</div>
							<div class="dm-cont" style="font-size: 14px; line-height: 22px;">
								<textarea id="about"
									style="font-size: 16px; line-height: 175%; font-family: 宋体; visibility: hidden;"></textarea>
							</div>
						</div>
						<div class="detail-module">
							<div class="dm-tit">招聘简章</div>
							<div class="dm-cont">
								<p style="text-indent: 32px; line-height: 175%">
									<textarea id="motoley"
										style="font-size: 16px; line-height: 175%; font-family: 宋体; visibility: hidden;"></textarea>
								<p>
									<span style="font-size: 16px; font-family: 宋体">联系人：</span> <span
										style="font-size: 16px" id="contractUser"></span>
								</p>
								<p>
									<span style="font-size: 16px; font-family: 宋体">联系电话：</span><span
										style="font-size: 16px" id="contractNumber"></span>
								</p>
								<p>
									<span style="font-size: 16px; font-family: 宋体">联系邮箱：</span><span
										style="font-size: 16px" id="contractEmail"></span>
								</p>
								<p>
									<span style="font-size: 16px; font-family: 宋体">联系地址：</span><span
										style="font-size: 16px" id="contractAddress"></span>
								</p>
								<div style="text-align: center;"></div>
							</div>
						</div>

						<div class="detail-module">
							<div class="dm-tit">招聘职位</div>
							<div class="dm-cont pub-list">
								<div id="jobInfo"></div>
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
	var about;
	var motoley;
	var id = getQueryStringByName("id");
	$(function() {
	    about = KindEditor.create('textarea[id="about"]', {
			items : []
		});
	    motoley = KindEditor.create('textarea[id="motoley"]', {
			items : []
		});
		getDate();
	});

	/* 获取数据 */
	function getDate() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/companys/detail/" + id,
			success : function(result) {
				if (result.resultCode == 200&&result.data!=null&&result.data.data!=null) {
					detailDate = result.data.data;
					setDate();
					setJobInfo();
				} else {
					alert("错误代码：" + result.resultCode);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	};

	/* 填充页面 */
	function setDate() {
		$("#rcompany_name").text(detailDate.name);
		$("#company_world").text("领域：" + detailDate.world);
		$("#company_scale").text("规模：" + detailDate.scale);
		$("#company_address").text("地址：" + detailDate.address);
		$("#company_name").text("宣讲单位：" + detailDate.name);
		$("#recruit_startTime").text("宣讲时间：" + detailDate.recruit.startTime);
		$("#recruit_address").text("宣讲地点：" + detailDate.recruit.address);
		about.html(detailDate.about);
		motoley.html(detailDate.recruit.motoley);
		/* kingeditor高度自适应 */
		about.edit.setHeight(about.edit.doc.body.scrollHeight); 
		motoley.edit.setHeight(motoley.edit.doc.body.scrollHeight);
		$("#contractUser").text(detailDate.recruit.contractUser);
		$("#contractNumber").text(detailDate.recruit.contractNumber);
		$("#contractEmail").text(detailDate.recruit.contractEmail);
		$("#contractAddress").text(detailDate.recruit.contractAddress);
	}

	/* 设置岗位信息 */
	function setJobInfo() {
		var jobInfos=new StringBuffer();
		/* of报错不用管 */
		for(var job of detailDate.jobs){
			jobInfos.append("<div style=\"border-bottom: 1px dashed #ddd; padding: 10px 0; line-height: 2;\" class=\"clearfix dm-text\">");
			jobInfos.append("<div style=\"width: 360px; float: left;\"> <p> <a class=\"item-link\" style=\"font-size: 16px;\"");
			jobInfos.append("href=").append("${pageContext.request.contextPath}/views/jobDetail.jsp?cId=").append(id).append("&jId=").append(job.id);
			jobInfos.append( " target=\"_blank\">");
			jobInfos.append(job.name);
			jobInfos.append("</a></p>");
			jobInfos.append("<p style=\"padding-right: 20px; width: 650px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;\">");
			jobInfos.append("招聘专业：");
			jobInfos.append(job.major);
			jobInfos.append("</p></div>");
			jobInfos.append("<div style=\"float: left; color: #ff9900;\">");
			jobInfos.append(job.money);
			jobInfos.append("</div>");
			jobInfos.append("<div style=\"float: right;\"><p>");
			jobInfos.append(job.num).append("人");
			jobInfos.append("</p>");
			jobInfos.append("<p style=\"width: 140px; height: 32px; overflow: hidden; white-space: pre; text-overflow: ellipsis;\">");
			jobInfos.append(job.degree).append("|").append(job.address);
			jobInfos.append("</p>");
			jobInfos.append("</div></div>");
		}; 
		$("#jobInfo").append(jobInfos.toString());
	}
</script>
</html>