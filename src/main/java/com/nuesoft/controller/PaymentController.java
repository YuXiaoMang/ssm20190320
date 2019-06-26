package com.nuesoft.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.nuesoft.config.AlipayConfig;
import com.nuesoft.po.OrderDetail;
import com.nuesoft.po.OrderInfo;
import com.nuesoft.po.PaymentInfo;
import com.nuesoft.po.enums.PaymentWay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 余承怿
 * @version V1.0
 * @Package com.nuesoft.controller
 * @date 2019/6/26 13:42
 * @Copyright 东莞三心网络科技有限公司
 */
@Controller
public class PaymentController {


    @ResponseBody
    @RequestMapping("alipay/submit")
    public String alipay(String orderId){


         String alipay_url="https://openapi.alipay.com/gateway.do";

         String app_private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdQeknhM2rhiGAH6V0ljxn3rAWIdzduTEQuteTfwjnZtvMhQPuuN1b/88D5yMuaZhZNFeUdWb+SmtP9DAzAWWgnT13T0YhJcxP6txm7JBRrjadCRt+LOFxPiPQk5t9fH7yXjw9i4uMDsNJeTncrVZ/AZYrk0ESC9anJR8XeuBc3HE8T4fqlKKl35jlumIWrPbPNQhKGXaGcOnpiaXO9qYYUSP/tnrjNYXHOso0yBs4YTl+LLX2TJ12p3n/oX6HnL4zQgtN5k4QasHP7CIig1ngcVQGfWsMm4djI9KXNXvGLQPfMQEmyb71mM5OCdl1MtAc6OaIAymhSv2hOLNIuyodAgMBAAECggEAe05/P5mGm4QlKI2n8u8KlneqovASe1kG/BNFjkYB+VBR8OAr4TfbepPvAyRuFap+5xN/yMz14VcBJkRWtufVhEdHNxJV7w/wUIncIGhGEYYFFMVbZWhTrbQH6TiUp6TC9dCmc6vD1CKPRkFj+YGBXT0lPy3LzBa0TYNyCbszyhthrgkpuFYbB0R93IPvvBh5NJFXQytwNb2oVopC9AQWviqnZUZcT0eJ087dQ1WLPa6blBD8DP1PUq0Ldr6pgKfObFxIj8+87DlJznRfdEsbqZlS7jagdw5tLr71WJpctIGPqKpgvajfePP/lj3eY82BKQB+aTw0zmAiB05Yes4LgQKBgQDq3EiQR8J1MEN2rpiLt1WvDYYvKVUgOY7Od//fRPgaMBstbe4TzGBpR8E+z267bHAWLaWtHkfX6muFHn1x68ozEUWk/nZq0smWnuPdcy4E7Itbk36W2FF/rOZB7j5ddlC9byrxDSNgcf9/FA/CU+i5KVQpLYfsk2dvwomvu0aFVQKBgQCraXpxzMmsBx4127LsZDO5bxfxb6nqzyK4NPe0VaGiRg8oaCWczcLz1J5iRqC9QeEwsSt4XU1sYBMTcsFpA0apZpm3prH2HJRx/isNENesaHcihF0mMd0WxU3xyRvWSDeZV5A1Zy1ZEJ+p17DGwb2j+yo2uBrDNXBgBWEzXwiRqQKBgBdXFvsHtqKQzlOQHGbeLGy+KlSrheMy9Sc9s7cLkqB/oWPNZfifugEceW71jGqh5y29EZb3yGoDyPWsxwi4Rxr2H3a7Nyd8lT4bwkdyt+MTYvIR4WW6T7chhqyMsbP2GyYIUzsrdBWUnrCRXNOSJTGpksyY0sZHC+OGcMp/EQ4VAoGBAIISSVL/pm1+/UK7U1ukcced8JpKNLM0uVD1CJ50eHHOHgR4e0owrWYfioxisejLjBlJ6AWvL2g0w2T3qKKKVN2JOM4ulU5/w3l4+KwygqaWowizTogEQJPd5ta52ADTzjTzSD/t6nByd+YHAWLhc4lyt0bMj6pf68VBb8/upm75AoGAGAYz79IVHp9eppykufjNcWu6okkG8tZnzuyaWKW/CuKKBWMaTk0vcyQlfJfxIBccoQrBuYyXBdcpPuZ/ys2C25pNrkACuhIKNgnMc0floJoYEfJzetw/3cIimWu4NJzVQOaojaGA58oo2+fub43Xn25Jq4rvSVe3oLdb5xWkw5Q=\n";

         String app_id="2018020102122556";

        String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhkZi6W0wn/prX+NIIF9ATb5Z8ReKK4hFYtBrweDfGHD1mNW7YIZY4G5hE7S2Sry8eFXlFgSlBWlJ4fVnDaK9MkVThpwE2H65ooVlK/wLuyPqovIVpMt/utva5Ayuzv7eQOWK45FdLDNDlK8QLoBko6SS+YbnWnf7a+mrf4NAS4UFClpfe8Byqe8XIraO2Cg4Ko5Y5schX39rOAH8GlLdgqQRYVQ2dCnkIQ+L+I4Cy9Mvw3rIkTwt3MBU+AqREXY4r5Bn6cmmX/9MAJbFqrofGiUAqG+qbjTcZAzgNPfuiD0zXgt/YYjMQMzck75BOmwnYOam2ajODUSQn8Xybsa7wQIDAQAB\n";

        String formats="json";
        String charset="utf-8";
        String sign_type="RSA2";

        // 订单对象
        OrderInfo orderInfo = new OrderInfo();
        List<OrderDetail> orderDetails = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setSkuName("支付宝支付测试");
        orderDetail.setSkuId("000000");
        BigDecimal price = new BigDecimal("999");
        orderDetail.setOrderPrice(price);
        orderDetail.setImgUrl("http://pic37.nipic.com/20140113/8800276_184927469000_2.png");
        orderDetail.setSkuNum(1);
        orderDetails.add(orderDetail);

        orderInfo.setOrderDetailList(orderDetails);
        // 封装订单信息
        orderInfo.setProcessStatus("订单未支付");
        // 日期
        // 日期的计算
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1);
        orderInfo.setExpireTime(c.getTime());
        orderInfo.setOrderStatus("未支付");
        String consignee = "测试收件人";
        orderInfo.setConsignee(consignee);
        // 外部订单号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        String outTradeNo = "ATGUIGU"+format+System.currentTimeMillis();
        orderInfo.setOutTradeNo(outTradeNo);
        orderInfo.setPaymentWay(PaymentWay.ONLINE);
        orderInfo.setUserId(orderId);
        orderInfo.setTotalAmount(new BigDecimal("999"));
        orderInfo.setOrderComment("硅谷订单");
        String address = "测试收件地址";
        orderInfo.setDeliveryAddress(address);
        orderInfo.setCreateTime(new Date());
        String tel = "123123123123123";
        orderInfo.setConsigneeTel(tel);

        //支付
        // 生成和保存支付信息
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOutTradeNo(orderInfo.getOutTradeNo());
        paymentInfo.setPaymentStatus("未支付");
        paymentInfo.setOrderId(orderId);
        paymentInfo.setTotalAmount(orderInfo.getTotalAmount());
        paymentInfo.setSubject(orderInfo.getOrderDetailList().get(0).getSkuName());
        paymentInfo.setCreateTime(new Date());

        System.out.println("支付信息"+paymentInfo+"已保存!");

        // 重定向到支付宝平台

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        AlipayClient alipayClient=new DefaultAlipayClient(alipay_url,app_id,app_private_key,formats,charset, alipay_public_key,sign_type );
        alipayRequest.setReturnUrl(AlipayConfig.return_payment_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_payment_url);//在公共参数中设置回跳和通知地址

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("out_trade_no",orderInfo.getOutTradeNo());
        stringObjectHashMap.put("product_code","FAST_INSTANT_TRADE_PAY");
        stringObjectHashMap.put("total_amount",0.01);//orderById.getTotalAmount()
        stringObjectHashMap.put("subject","phone");
        String json = JSON.toJSONString(stringObjectHashMap);
        alipayRequest.setBizContent(json);

        String form="";

        try {
           form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {

        }

//        try {
//            form = alipayConfig.alipayClient().pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }

        System.out.println("************************************************");
        System.out.println(form);
        return form;
    }

}
