package com.danning.silkroad.helipay.controller;

import com.danning.silkroad.helipay.BaseTestCase;
import com.danning.silkroad.helipay.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：QuickPaymentApiTest<br>
 * 类描述：<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public class QuickPaymentApiTest extends BaseTestCase {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void bankCardbindList(){
        Map<String , String> params = new HashMap<>();
        params.put("userId","1000000001");
        params.put("bindId","ad15032db3eb4b3187e6a3c44bb2fe9a");
        params.put("timestamp","20190127213655");
        params.put("signatureType","MD5WITHRSA");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/bankCardbindList", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void authBindCardSms(){
        Map<String , String> params = new HashMap<>();
        params.put("userId","1000000001");
        params.put("orderId","123");
        params.put("timestamp","20190127213655");
        params.put("cardNo","12379182739180273");
        params.put("phone","18788862728");
        params.put("idCardNo","123123123123");
        params.put("idCardType","IDCARD");
        params.put("payerName","陈超");
        params.put("year","");
        params.put("month","");
        params.put("cvv2","");
        params.put("signatureType","MD5WITHRSA");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/authBindCardSms", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void authBindCard(){
        Map<String , String> params = new HashMap<>();
        params.put("userId","1000000001");
        params.put("orderId","123");
        params.put("timestamp","20190127213655");
        params.put("cardNo","12379182739180273");
        params.put("phone","18788862728");
        params.put("idCardNo","123123123123");
        params.put("idCardType","IDCARD");
        params.put("payerName","陈超");
        params.put("year","");
        params.put("month","");
        params.put("cvv2","");
        params.put("validateCode","123123");
        params.put("isEncrypt","TRUE");
        params.put("signatureType","MD5WITHRSA");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/authBindCard", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void bindCardPaymentSms(){
        Map<String , String> params = new HashMap<>();
        params.put("bindId","1231231");
        params.put("userId","12312111");
        params.put("orderId","2019012917265512348");
        params.put("timestamp","20190129201355");
        params.put("currency","CNY");
        params.put("orderAmount","0.11");
        params.put("phone","18788866925");
        params.put("signatureType","MD5WITHRSA");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/bindCardPaymentSms", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void bindCardPayment(){
        Map<String , String> params = new HashMap<>();
        params.put("bindId","1231231");
        params.put("userId","12312111");
        params.put("orderId","2019012917265512348");
        params.put("timestamp","20190129201355");
        params.put("currency","CNY");
        params.put("orderAmount","0.11");
        params.put("goodsName","ajsdk");
        params.put("goodsDesc","asd");
        params.put("terminalType","IMEI");
        params.put("terminalId","122121212121");
        params.put("orderIp","127.0.0.1");
        params.put("period","");
        params.put("periodUnit","");
        params.put("serverCallbackUrl","http://150hy91322.51mypc.cn:57396/trade/quickPayment/syncNotice");
        params.put("validateCode","123123");
        params.put("signatureType","MD5WITHRSA");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/bindCardPayment", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void quickPayQuery(){
        Map<String , String> params = new HashMap<>();
        params.put("signatureType","MD5WITHRSA");
        params.put("orderId","2019012815215512348");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/quickPayQuery", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void bankCardUnbind(){
        Map<String , String> params = new HashMap<>();
        params.put("signatureType","MD5WITHRSA");
        params.put("userId","12311111");
        params.put("bindId","1aklsjdlaksjd");
        params.put("orderId","12111111122222");
        params.put("timestamp","123333333333");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/quickPayment/bankCardUnbind", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }
}
