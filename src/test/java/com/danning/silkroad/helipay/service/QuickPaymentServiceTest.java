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
        AuthBindCardSmsVO vo = AuthBindCardSmsVO.builder()
            .P1_bizType(HelipayBizType._AGREEMENTPAYBINDCARDVALIDATECODE.value)
            .P2_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P3_userId("1000000001")
            .P4_orderId("2019012818105512346")
            .P5_timestamp("20190127181055")
            .P6_cardNo("6222621230322999139")
            .P7_phone("18788866925")
            .P8_idCardNo("342623198906224128")
            .P9_idCardType("IDCARD")
            .P10_payerName("陈超")
            .P12_year("")
            .P13_month("")
            .P14_cvv2("")
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<AuthBindCardSmsResp> result = quickPaymentService.authBindCardSms(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void authBindCard(){
        AuthBindCardVO vo = AuthBindCardVO.builder()
            .P1_bizType(HelipayBizType._QUICKPAYBINDCARD.value)
            .P2_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P3_userId("1000000001")
            .P4_orderId("2019012818105512346")
            .P5_timestamp("20190127181255")
            .P6_payerName("陈超")
            .P7_idCardType("IDCARD")
            .P8_idCardNo("342623199303223016")
            .P9_cardNo("6222621230322999139")
            .P10_year("")
            .P11_month("")
            .P12_cvv2("")
            .P13_phone("18788866925")
            .P14_validateCode("414202")
            .P15_isEncrypt("TRUE")
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<AuthBindCardResp> result = quickPaymentService.authBindCard(vo);
        //{"success":true,"code":"000000","msg":"【鉴权绑卡】请求成功","data":{"rt1_bizType":"QuickPayBindCard","rt2_retCode":"0000","rt3_retMsg":"认证成功","rt4_customerNumber":"C1800532519","rt5_userId":"1000000001","rt6_orderId":"2019012818105512346","rt7_bindStatus":"SUCCESS","rt8_bankId":"BOCO","rt9_cardAfterFour":"0139","rt10_bindId":"ad15032db3eb4b3187e6a3c44bb2fe9a","rt11_serialNumber":"AUTHENTICATION190128112036KCWY","sign":"ZXpFUwCsxVqTwrEb+CJP/f3Z8ThJRBGxUAa5dp005TZ2QgMxcQGGM5nafH9D2uKiIcje42fjDKSXH4LIQ/iLTeTfSD5st7wKrY8V5JeaaNTZ28iZzzkuydVkS9q5RCbmPnKrMhkPINgHgPNJBq9L4+tNf3P5dK96SDeRZSEcgVLyn2ncf0F+QY+mAzR2t6g/TfXdOVX6nVQ5fwYkfL2jP9PI3RrSGSEJI7Fjl3T1i5gH24Cvoe5lYSrxttHZRGzzubZLl6ct0k7TrJUiImdI2Y2WavklPdtH6DdP3LYGq7M9gjgGdXRRd/EdZOPsdDHzlV1DmLB+dNVYTqnkwfC7+g=="}}
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bindCardPaymentSms(){
        BindCardPaymentSmsVO vo = BindCardPaymentSmsVO.builder()
            .P1_bizType(HelipayBizType._QUICKPAYBINDPAYVALIDATECODE.value)
            .P2_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P3_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a")
            .P4_userId("1000000001")
            .P5_orderId("2019012917265512348")
            .P6_timestamp("20190129201355")
            .P7_currency("CNY")
            .P8_orderAmount("0.11")
            .P9_phone("18788866925")
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<BindCardPaymentSmsResp> result = quickPaymentService.bindCardPaymentSms(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bindCardPayment(){
        BindCardPaymentVO vo = BindCardPaymentVO.builder()
            .P1_bizType(HelipayBizType._QUICKPAYBINDPAY.value)
            .P2_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P3_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a")
            .P4_userId("1000000001")
            .P5_orderId("2019012917265512348")
            .P6_timestamp("20190129203155")
            .P7_currency("CNY")
            .P8_orderAmount("0.11")
            .P9_goodsName("苹果")
            .P10_goodsDesc("ipad")
            .P11_terminalType("IMEI")
            .P12_terminalId("122121212121")
            .P13_orderIp("127.0.0.1")
            .P14_period("")
            .P15_periodUnit("")
            .P16_serverCallbackUrl("http://150hy91322.51mypc.cn:57396/trade/quickPayment/syncNotice")
            .P17_validateCode("745288")
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<BindCardPaymentResp> result = quickPaymentService.bindCardPayment(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void quickPayQuery(){
        QuickPayQueryVO vo = QuickPayQueryVO.builder()
            .P1_bizType(HelipayBizType._QUICKPAYQUERY.value)
            .P2_orderId("2019012815215512348")
            .P3_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<QuickPayQueryResp> result = quickPaymentService.quickPayQuery(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bankCardUnbind(){
        BankCardUnbindVO vo = BankCardUnbindVO.builder()
            .P1_bizType(HelipayBizType._BANKCARDUNBIND.value)
            .P2_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P3_userId("1000000001")
            .P4_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a")
            .P5_orderId("2019012718105512350")
            .P6_timestamp("20190127213355")
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<BankCardUnbindResp> result = quickPaymentService.bankCardUnbind(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
    }

    @Test
    public void bankCardbindList(){
        BankCardbindListVO vo = BankCardbindListVO.builder()
            .P1_bizType(HelipayBizType._BANKCARDBINDLIST.value)
            .P2_customerNumber(AuthConstants.CUSTOMERNUMBER)
            .P3_userId("1000000001")
            .P4_bindId("ad15032db3eb4b3187e6a3c44bb2fe9a")
            .P5_timestamp("20190127213655")
            .signatureType("MD5WITHRSA").build();
        SilkroadResponse<BankCardbindListResp> result = quickPaymentService.bankCardbindList(vo);
        System.out.println("result:"+JsonUtils.getInstance().toJson(result));
        String resultJson = StringEscapeUtils.unescapeJava(result.getData().getRt5_bindCardList().toString());
        List<BindCardResp> br = JsonUtils.getInstance().toList(resultJson,BindCardResp.class);
        System.out.println("resp:"+JsonUtils.getInstance().toJson(resultJson));
    }
}
