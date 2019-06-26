<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/26
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        img {
           height: 20px;
            width: 20px;
        }
    </style>
</head>
<body>
    <h3>订单提交成功,等待付款...</h3>
    <span>应付金额</span><span style="color: red">$999</span>
    <ul >
        <li >
              <span>
                <input type="radio" name="pay"  value="alipay" />
                <img src="resource\img\alipay.jpg" alt="">支付宝
              </span>
        </li>
        <li >
              <span>
                <input type="radio" name="pay"  value="mx" />
                <img src="resource\img\weixin.png" alt="">微信支付
              </span>

        </li>
        <li>
            <button id="paymentButton" >立即支付</button>
        </li>
    </ul>

    <form method="post" id="paymentForm" name="payWay">
        <input type="hidden" name="orderId" value="000000" >
    </form>

</body>
<script src="resource/js/jquery_2.1.4_baidu_min.js"></script>
<script type="text/javascript">
    $(function() {
        $("#paymentButton").click(function () {
            // alipay/submit
            // mx/submit
            $("#paymentForm").attr("action","/"+$("input[type='radio']:checked").val()+"/submit")  ;
            $("#paymentForm").submit();
        })
    })
    //立即支付按钮效果
    $("#paymentButton").mouseover(function(){
        $(this).css({"cursor":"pointer","background":"#FF5350"})
    }).mouseout(function(){
        $(this).css("background","#FC6E6C")
    })
</script>
</html>
