<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>实习鉴定标准</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
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
        <form id="appraisalform" method="post" style="">
            <input name="id" type="text" id="id" hidden>

            实习鉴定标准
            <textarea id="content" style="visibility: hidden;"></textarea>
            <input type="button" name="button" value="保存" class="button" id="bc"
                   onclick="saveInfo();" style="margin: 10px">
        </form>
    </div>
</div>
</body>
<script type="text/javascript">

    $(function() {
        if("teacher"==getCookie("usertype")){
            document.getElementById("bc").setAttribute("style","");
        }else{
            document.getElementById("bc").setAttribute("style","display:none");
        }
        //实习鉴定表编辑器
        content = KindEditor.create('textarea[id="content"]', {
            height:"800px",
            width:"400px",
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
        setappraisalformInfo();
    });

    //checkCookie();
    setappraisalformInfo();
    function setappraisalformInfo() {
        $.ajax({
            type : "GET",
            url : "${pageContext.request.contextPath}/appraisalform/content",
            success : function(result) {
                if (result.resultCode == 200) {
                    $('#id').val(result.data.currentAppraisalForm.id);
                    content.html(result.data.currentAppraisalForm.content);
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
            "id": $('#id').val(),
            "content" : content.html()
        };
        $.ajax({
            type : method,
            url : "${pageContext.request.contextPath}/appraisalform",
            data : data,
            success : function(result) {
                if (result.resultCode == 200) {
                    alert("更新成功！");
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