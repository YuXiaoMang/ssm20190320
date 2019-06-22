
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
        <form id="forms" action="updateBook.do" method="post" >

            <table border="1px" style="text-align: center;">
                <tr>
                    <td>图书编号</td>
                    <td>${bookInfo.bookCode}</td>
                </tr>
                <tr>
                    <td>图书名称</td>
                    <td>${bookInfo.bookName}</td>
                </tr>
                <tr>
                    <td>图书分类</td>
                    <td style="text-align: left;">${bookInfo.bookTypes.typeName}</td>
                </tr>
                <tr>
                    <td>作者</td>
                    <td>${bookInfo.bookAuthor}</td>
                </tr>
                <tr>
                    <td>出版社</td>
                    <td>${bookInfo.publishPress}</td>
                </tr>
                <tr>
                    <td>出版时间</td>
                    <td><fmt:formatDate value="${bookInfo.publishDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>图片上传</td>
                    <td><img id="img" src="${bookInfo.bookPath}" width="120px" height="150px" >

                </tr>
                <tr>
                    <td colspan="2"><a href="index.do">返回</a>
                            </td>

                </tr>
            </table>
        </form>
    </div>


</div>
</body>


</html>
