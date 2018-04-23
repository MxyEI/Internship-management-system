<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>简历信息</title>
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
        <form id="userForm" method="post" style="">
            <table class="table table-hover table-condensed">

                <tr>
                    <th>真实姓名</th>
                    <td><input name="realname" type="text" id="realname"></td>
                </tr>
                <tr>
                    <th>专业</th>
                    <td><input name="major" type="text" id="major"></td>
                </tr>
            </table>
            课程信息

            <textarea id="course" style="visibility: hidden;"></textarea>

            基础技能

            <textarea id="skill" style="visibility: hidden;"></textarea>

            项目经验

            <textarea id="project" style="visibility: hidden;"></textarea>

            兴趣爱好

            <textarea id="hobbies" style="visibility: hidden;"></textarea>

            <input name="id" type="text" id="id" hidden>
            <input name="studentid" type="text" id="studentid" hidden>


            <input type="button" name="button" value="保存" class="button" id="bcanniu"
                   onclick="saveInfo();" style="margin: 10px">
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    var userId = getQueryStringByName("userid");
    $(function() {
        //课程信息编辑器
        course = KindEditor.create('textarea[id="course"]', {
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

        //基础技能编辑器
        skill = KindEditor.create('textarea[id="skill"]', {
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

        //项目经验编辑器
        project = KindEditor.create('textarea[id="project"]', {
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

        //兴趣爱好编辑器
        hobbies = KindEditor.create('textarea[id="hobbies"]', {
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

        setResumeInfo();
    });


    checkCookie();
    setResumeInfo();

    function setResumeInfo() {
        var urlstr;

        var type= "student";
        if(getCookie("usertype")!=type){
            urlstr = "${pageContext.request.contextPath}/personresume/"+userId;
            //如果不是学生则把保存按钮隐藏
            document.getElementById('bcanniu').style.display="none";
        }else{
            urlstr = "${pageContext.request.contextPath}/personresume/"+getCookie("id");
            document.getElementById('bcanniu').style.display="none";
        }
        $.ajax({
            type : "GET",
            url : urlstr,
            success : function(result) {
                if (result.resultCode == 200) {

                    $('#realname').val(result.data.currentResume.realname);
                    $('#major').val(result.data.currentResume.major);
                    course.html(result.data.currentResume.course);
                    skill.html(result.data.currentResume.skill);
                    project.html(result.data.currentResume.project);
                    hobbies.html(result.data.currentResume.hobbies);
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
//            "id" : getCookie("id"),
            "realname" : $("#realname").val(),
            "major" : $("#major").val(),
            "course" : course.html(),
            "skill" : skill.html(),
            "project" : project.html(),
            "hobbies" : hobbies.html(),
            "studentid": getCookie("id")
        };
        $.ajax({
            type : method,
            url : "${pageContext.request.contextPath}/personresume",
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