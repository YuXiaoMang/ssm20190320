<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<div style="height: 600px; width: 800px; margin: 0px auto;">


    <form action="index.do" method="post" id="query">
        图书分类<select name="bookTypeid">
        <option value="0">全部</option>
        <c:forEach items="${bookTypes}" var="type">

            <option value="${type.typeId}"
                    <c:if test="${type.typeId==bookTypeid}">selected="selected"</c:if>${type.typeName}</option>
        </c:forEach>
    </select> &nbsp; 图书名称<input name="bookname" value="${bookname}">
        &nbsp;是否借阅 <select name="borrow">
        <option value="-1">请选择</option>
        <option value="0"
                <c:if test="${borrow==0 }">selected="selected" </c:if>>未借阅</option>
        <option value="1"
                <c:if test="${borrow==1 }">selected="selected" </c:if>>已借阅</option>
    </select> <input type="hidden" name="now" id="pageno"> <input
            type="submit" value="查询">
    </form>



    <a href="add.do">添加</a> &nbsp;
    <button onclick="deleteAll();">批量删除</button>
    <form id="delete" action="deleteBookByIds.do" method="post">
        <input type="hidden" name="choose" value="7">
        <table>
            <tr>
                <td><input type="checkbox" name="ids"
                           onchange="changecheckBox(this.checked);"></td>
                <td>图书编号</td>
                <td>图书分类</td>
                <td>图书名称</td>
                <td>作者</td>
                <td>出版社</td>
                <td>操作</td>
                <td>详情</td>
                <td>删除</td>
                <td>修改</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="book">
                <tr>
                    <td><input type="checkbox" name="bookid"
                               value="${book.bookId }" ></td>
                    <td>${book.bookCode }</td>
                    <td>${book.bookTypes.typeName}</td>
                    <td>${book.bookName }</td>
                    <td>${book.bookAuthor }</td>
                    <td>${book.publishPress }</td>
                    <td><c:if test="${book.isBorrow ==0 }">未借阅</c:if> <c:if
                            test="${book.isBorrow ==1 }">已借阅</c:if></td>
                    <td><a href="toDetails.do?bookId=${book.bookId }">详情</a></td>
                    <td><a href="deleteById.do?bookId=${book.bookId }">删除</a></td>
                    <td><a href="toupdatepage.do?bookId=${book.bookId }">修改</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="9"><a href="javascript:doPage(1);">首页</a>&nbsp;&nbsp;
                    <a href="javascript:doPage(${pageInfo.pageNum-1});">上一页</a>&nbsp;&nbsp;
                    <a href="javascript:doPage(${pageInfo.pageNum+1});">下一页</a>&nbsp;&nbsp;
                    <a href="javascript:doPage(${pageInfo.pages});">末页</a>&nbsp;&nbsp;
                    ${pageInfo.pageNum}/${pageInfo.pages}页</td>

            </tr>
        </table>
    </form>
</div>
</body>
<script src="../../resource/js/jquery_2.1.4_baidu_min.js"></script>
<script>
    
    function deleteAll() {
        $("#delete").submit();
    }

    function changecheckBox(check) {
        var input = $("input[name='bookid']");
        if (check) {
            $(input).each(function (i) {
                this.setAttribute("checked","checked");
                //this.setAttribute("checked","true");
            })
        } else {
            $(input).each(function (i) {
                this.removeAttribute("checked");
            })
        }
    }

    function doPage(pageno) {
        $("#pageno").val(pageno);

        $("#query").submit();
    }
</script>
</html>
