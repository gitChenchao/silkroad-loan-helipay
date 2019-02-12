package com.danning.silkroad.helipay.controller;

import com.danning.silkroad.helipay.BaseTestCase;
import com.danning.silkroad.helipay.common.constents.AuthConstants;
import com.danning.silkroad.helipay.common.enums.HelipayBizType;
import com.danning.silkroad.helipay.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：TransferApiTest<br>
 * 类描述：<br>
 * 创建时间：2019年02月12日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public class TransferApiTest extends BaseTestCase {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void singlePaymentQuery(){
        Map<String , String> params = new HashMap<>();
        params.put("orderId","123");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/trans/singlePaymentQuery", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }

    @Test
    public void singlePayment(){
        Map<String , String> params = new HashMap<>();
        params.put("orderId","11111123123");
        params.put("amount","0.01");
        params.put("bankCode","BOCO");
        params.put("bankAccountNo","6222620910022100139");
        params.put("bankAccountName","陈超");
        params.put("biz","B2C");
        params.put("bankUnionCode","");
        params.put("feeType","PAYER");
        params.put("urgency","true");
        params.put("summary","结算款");
        params.put("notifyUrl","http://150hy91322.51mypc.cn:57396/trade/df/paymentResultNotice");
        String rest = restTemplate.postForObject("http://localhost:8090/trade/trans/singlePayment", params, String.class);
        System.out.println("rest:"+JsonUtils.getInstance().toJson(rest));
    }
}
