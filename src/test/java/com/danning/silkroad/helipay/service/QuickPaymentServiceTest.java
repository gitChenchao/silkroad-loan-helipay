package com.danning.silkroad.helipay.service;

import com.danning.silkroad.helipay.BaseTestCase;
import com.danning.silkroad.helipay.common.constents.AuthConstants;
import com.danning.silkroad.helipay.common.enums.HelipayBizType;
import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.*;
import com.danning.silkroad.helipay.utils.JsonUtils;
import com.danning.silkroad.helipay.vo.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 类名称：QuickPaymentServiceTest<br>
 * 类描述：<br>
 * 创建时间：2019年01月27日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
public class QuickPaymentServiceTest extends BaseTestCase {

    @Autowired
    private QuickPaymentService quickPaymentService;

    @Test
    public void authBindCardSms(){
        AuthBindCardSmsVO vo = new AuthBindCardSmsVO();
        vo.setP1_bizType(HelipayBizType._AGREEMENTPAYBINDCARDVALIDATECODE.value);
        vo.setP2_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP3_userId("1000000001");
        vo.setP4_orderId("2019012818105512346");
        vo.setP5_timestamp("20190127181055");
        vo.setP6_cardNo("6222620910022100139");
        vo.setP7_phone("18788866925");
        vo.setP8_idCardNo("342623199303223016");
        vo.setP9_idCardType("IDCARD");
        vo.setP10_payerName("陈超");
        vo.setP12_year("");
        vo.setP13_month("");
        vo.setP14_cvv2("");
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<AuthBindCardSmsResp> result = quickPaymentService.authBindCardSms(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void authBindCard(){
        AuthBindCardVO vo = new AuthBindCardVO();
        vo.setP1_bizType(HelipayBizType._QUICKPAYBINDCARD.value);
        vo.setP2_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP3_userId("1000000001");
        vo.setP4_orderId("2019012818105512346");
        vo.setP5_timestamp("20190127181255");
        vo.setP6_payerName("陈超");
        vo.setP7_idCardType("IDCARD");
        vo.setP8_idCardNo("342623199303223016");
        vo.setP9_cardNo("6222620910022100139");
        vo.setP10_year("");
        vo.setP11_month("");
        vo.setP12_cvv2("");
        vo.setP13_phone("18788866925");
        vo.setP14_validateCode("414202");
        vo.setP15_isEncrypt("TRUE");
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<AuthBindCardResp> result = quickPaymentService.authBindCard(vo);
        //{"success":true,"code":"000000","msg":"【鉴权绑卡】请求成功","data":{"rt1_bizType":"QuickPayBindCard","rt2_retCode":"0000","rt3_retMsg":"认证成功","rt4_customerNumber":"C1800532519","rt5_userId":"1000000001","rt6_orderId":"2019012818105512346","rt7_bindStatus":"SUCCESS","rt8_bankId":"BOCO","rt9_cardAfterFour":"0139","rt10_bindId":"ad15032db3eb4b3187e6a3c44bb2fe9a","rt11_serialNumber":"AUTHENTICATION190128112036KCWY","sign":"ZXpFUwCsxVqTwrEb+CJP/f3Z8ThJRBGxUAa5dp005TZ2QgMxcQGGM5nafH9D2uKiIcje42fjDKSXH4LIQ/iLTeTfSD5st7wKrY8V5JeaaNTZ28iZzzkuydVkS9q5RCbmPnKrMhkPINgHgPNJBq9L4+tNf3P5dK96SDeRZSEcgVLyn2ncf0F+QY+mAzR2t6g/TfXdOVX6nVQ5fwYkfL2jP9PI3RrSGSEJI7Fjl3T1i5gH24Cvoe5lYSrxttHZRGzzubZLl6ct0k7TrJUiImdI2Y2WavklPdtH6DdP3LYGq7M9gjgGdXRRd/EdZOPsdDHzlV1DmLB+dNVYTqnkwfC7+g=="}}
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bindCardPaymentSms(){
        BindCardPaymentSmsVO vo = new BindCardPaymentSmsVO();
        vo.setP1_bizType(HelipayBizType._QUICKPAYBINDPAYVALIDATECODE.value);
        vo.setP2_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP3_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a");
        vo.setP4_userId("1000000001");
        vo.setP5_orderId("2019012917265512348");
        vo.setP6_timestamp("20190129201355");
        vo.setP7_currency("CNY");
        vo.setP8_orderAmount("0.11");
        vo.setP9_phone("18788866925");
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<BindCardPaymentSmsResp> result = quickPaymentService.bindCardPaymentSms(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bindCardPayment(){
        BindCardPaymentVO vo = new BindCardPaymentVO();
        vo.setP1_bizType(HelipayBizType._QUICKPAYBINDPAY.value);
        vo.setP2_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP3_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a");
        vo.setP4_userId("1000000001");
        vo.setP5_orderId("2019012917265512348");
        vo.setP6_timestamp("20190129203155");
        vo.setP7_currency("CNY");
        vo.setP8_orderAmount("0.11");
        vo.setP9_goodsName("苹果");
        vo.setP10_goodsDesc("ipad");
        vo.setP11_terminalType("IMEI");
        vo.setP12_terminalId("122121212121");
        vo.setP13_orderIp("127.0.0.1");
        vo.setP14_period("");
        vo.setP15_periodUnit("");
        vo.setP16_serverCallbackUrl("http://150hy91322.51mypc.cn:57396/trade/quickPayment/syncNotice");
        vo.setP17_validateCode("745288");
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<BindCardPaymentResp> result = quickPaymentService.bindCardPayment(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void quickPayQuery(){
        QuickPayQueryVO vo = new QuickPayQueryVO();
        vo.setP1_bizType(HelipayBizType._QUICKPAYQUERY.value);
        vo.setP2_orderId("2019012815215512348");
        vo.setP3_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<QuickPayQueryResp> result = quickPaymentService.quickPayQuery(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bankCardUnbind(){
        BankCardUnbindVO vo = new BankCardUnbindVO();
        vo.setP1_bizType(HelipayBizType._BANKCARDUNBIND.value);
        vo.setP2_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP3_userId("1000000001");
        vo.setP4_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a");
        vo.setP5_orderId("2019012718105512350");
        vo.setP6_timestamp("20190127213355");
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<BankCardUnbindResp> result = quickPaymentService.bankCardUnbind(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bankCardbindList(){
        BankCardbindListVO vo = new BankCardbindListVO();
        vo.setP1_bizType(HelipayBizType._BANKCARDBINDLIST.value);
        vo.setP2_customerNumber(AuthConstants.CUSTOMERNUMBER);
        vo.setP3_userId("1000000001");
        vo.setP4_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a");
        vo.setP5_timestamp("20190127213655");
        vo.setSignatureType("MD5WITHRSA");
        SilkroadResponse<BankCardbindListResp> result = quickPaymentService.bankCardbindList(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
        String resultJson = StringEscapeUtils.unescapeJava(result.getData().getRt5_bindCardList().toString());
        List<BindCardResp> br = JsonUtils.getInstance().toList(resultJson,BindCardResp.class);
        System.out.println("resp:"+JsonUtils.getInstance().toJson(resultJson));
    }
}
