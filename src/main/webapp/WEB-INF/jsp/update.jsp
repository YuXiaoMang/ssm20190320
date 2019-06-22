
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #body {
            margin: 0px auto;
            width: 800px;
            height: 600px;
        }
    </style>
</head>
<body>
<div id="body">
    <div style="margin: 0px auto; width: 500px;">
        <form id="forms" action="updateBook.do" method="post" enctype="multipart/form-data">
            <input type="hidden" value="${bookInfo.bookId}" name="bookId"/>
            <table border="1px" style="text-align: center;">
                <tr>
                    <td>图书编号</td>
                    <td><input id="code" name="bookCode" value="${bookInfo.bookCode}"><span
                            id="codeMessage"></span></td>
                </tr>
                <tr>
                    <td>图书名称</td>
                    <td><input name="bookName" value="${bookInfo.bookName}"></td>
                </tr>
                <tr>
                    <td>图书分类</td>
                    <td style="text-align: left;"><select name="bookType">
                        <c:forEach items="${bookTypes}" var="bookType">
                            <option value="${bookType.typeId }"
                            <c:if test="${bookType.typeId ==bookInfo.bookType}">selected</c:if>>${bookType.typeName}</option>

                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td>作者</td>
                    <td><input name="bookAuthor" value="${bookInfo.bookAuthor}"></td>
                </tr>
                <tr>
                    <td>出版社</td>
                    <td><input name="publishPress" value="${bookInfo.publishPress}"></td>
                </tr>
                <tr>
                    <td>出版时间</td>
                    <td><input type="text" name="publishDate" value="<fmt:formatDate value="${bookInfo.publishDate}" pattern="yyyy-MM-dd"/>"></td>
                </tr>
                <tr>
                    <td>图片上传</td>
                    <td><img id="img" src="${bookInfo.bookPath}" width="120px" height="150px" >
                        <input type="file" name="fileImage" onchange="uploadImage()">
                        <input type="hidden" name="bookPath" id="pic"  ></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="提交"> <a href="index.do">取消</a>
                            </td>

                </tr>
            </table>
        </form>
    </div>


</div>
</body>
<script src="../../resource/js/jquery_2.1.4_baidu_min.js"></script>
<script src="../../resource/js/jquery-form.js"></script>
<script>
    function uploadImage() {

        var obj={
            url:"upload.do",
            dataType:"json",
            type:"post",
            success:function (data) {
                $("#img").attr("src",data.repath);
                $("#pic").val(data.repath);
            }
        }
        $("#forms").ajaxSubmit(obj);

    }
</script>

</html>
