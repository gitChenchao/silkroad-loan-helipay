package com.danning.silkroad.helipay.controller;

import com.danning.silkroad.helipay.common.enums.HelipayBizType;
import com.danning.silkroad.helipay.common.request.*;
import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.*;
import com.danning.silkroad.helipay.service.QuickPaymentService;
import com.danning.silkroad.helipay.utils.JsonUtils;
import com.danning.silkroad.helipay.utils.ValidatorUtil;
import com.danning.silkroad.helipay.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类名称：QuickPaymentApi<br>
 * 类描述：<br>
 * 创建时间：2019年01月26日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@RestController
@Slf4j
@Api(value = "合利宝-快捷支付API", description = "合利宝-快捷支付接口")
@RequestMapping("/trade/quickPayment/")
public class QuickPaymentApi {

    private final QuickPaymentService quickPaymentService;
    private final String customerNumber;

    public QuickPaymentApi(QuickPaymentService quickPaymentService,
                           @Value("${customerNumber}")String customerNumber) {
        this.quickPaymentService = quickPaymentService;
        this.customerNumber = customerNumber;
    }


    @PostMapping(value = { "authBindCardSms" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_authBindCardSms",value = "合利宝快捷支付鉴权绑卡短信接口", notes = "合利宝快捷支付鉴权绑卡短信接口")
    public SilkroadResponse<AuthBindCardSmsResp> authBindCardSms(@RequestBody AuthBindCardSmsReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【鉴权绑卡短信】请求参数异常");
        }
        //组装请求合利宝接口参数
        AuthBindCardSmsVO vo = AuthBindCardSmsVO.builder()
                .P1_bizType(HelipayBizType._AGREEMENTPAYBINDCARDVALIDATECODE.value)
                .P2_customerNumber(customerNumber)
                .P3_userId(request.getUserId())
                .P4_orderId(request.getOrderId())
                .P5_timestamp(request.getTimestamp())
                .P6_cardNo(request.getCardNo())
                .P7_phone(request.getPhone())
                .P8_idCardNo(request.getIdCardNo())
                .P9_idCardType(request.getIdCardType())
                .P10_payerName(request.getPayerName())
                .P12_year(request.getYear())
                .P13_month(request.getMonth())
                .P14_cvv2(request.getCvv2())
                .signatureType(request.getSignatureType()).build();
        return quickPaymentService.authBindCardSms(vo);
    }

    @PostMapping(value = { "authBindCard" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_authBindCard",value = "合利宝快捷支付鉴权绑卡接口", notes = "合利宝快捷支付鉴权绑卡接口")
    public SilkroadResponse<AuthBindCardResp> authBindCard(@RequestBody AuthBindCardReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【鉴权绑卡】请求参数异常");
        }
        //组装请求合利宝接口参数
        AuthBindCardVO vo = AuthBindCardVO.builder()
                .P1_bizType(HelipayBizType._QUICKPAYBINDCARD.value)
                .P2_customerNumber(customerNumber)
                .P3_userId(request.getUserId())
                .P4_orderId(request.getOrderId())
                .P5_timestamp(request.getTimestamp())
                .P6_payerName(request.getPayerName())
                .P7_idCardType(request.getIdCardType())
                .P8_idCardNo(request.getIdCardNo())
                .P9_cardNo(request.getCardNo())
                .P10_year(request.getYear())
                .P11_month(request.getMonth())
                .P12_cvv2(request.getCvv2())
                .P13_phone(request.getPhone())
                .P14_validateCode(request.getValidateCode())
                .P15_isEncrypt(request.getIsEncrypt())
                .signatureType(request.getSignatureType()).build();
        return quickPaymentService.authBindCard(vo);
    }

    @PostMapping(value = { "bindCardPaymentSms" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_bindCardPaymentSms",value = "合利宝快捷支付绑卡支付短信接口", notes = "合利宝快捷支付绑卡支付短信接口")
    public SilkroadResponse<BindCardPaymentSmsResp> bindCardPaymentSms(@RequestBody BindCardPaymentSmsReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【绑卡支付短信】请求参数异常");
        }
        //组装请求合利宝接口参数
        BindCardPaymentSmsVO vo = BindCardPaymentSmsVO.builder()
                .P1_bizType(HelipayBizType._QUICKPAYBINDPAYVALIDATECODE.value)
                .P2_customerNumber(customerNumber)
                .P3_bindId(request.getBindId())
                .P4_userId(request.getUserId())
                .P5_orderId(request.getOrderId())
                .P6_timestamp(request.getTimestamp())
                .P7_currency(request.getCurrency())
                .P8_orderAmount(request.getOrderAmount())
                .P9_phone(request.getPhone())
                .signatureType(request.getSignatureType()).build();
        return quickPaymentService.bindCardPaymentSms(vo);
    }

    @PostMapping(value = { "bindCardPayment" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_bindCardPayment",value = "合利宝快捷支付绑卡支付接口", notes = "合利宝快捷支付绑卡支付接口")
    public SilkroadResponse<BindCardPaymentResp> bindCardPayment(@RequestBody BindCardPaymentReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【绑卡支付】请求参数异常");
        }
        //组装请求合利宝接口参数
        BindCardPaymentVO vo = BindCardPaymentVO.builder()
                .P1_bizType(HelipayBizType._QUICKPAYBINDPAY.value)
                .P2_customerNumber(customerNumber)
                .P3_bindId(request.getBindId())
                .P4_userId(request.getUserId())
                .P5_orderId(request.getOrderId())
                .P6_timestamp(request.getTimestamp())
                .P7_currency(request.getCurrency())
                .P8_orderAmount(request.getOrderAmount())
                .P9_goodsName(request.getGoodsName())
                .P10_goodsDesc(request.getGoodsDesc())
                .P11_terminalType(request.getTerminalType())
                .P12_terminalId(request.getTerminalId())
                .P13_orderIp(request.getOrderIp())
                .P14_period(request.getPeriod())
                .P15_periodUnit(request.getPeriodUnit())
                .P16_serverCallbackUrl(request.getServerCallbackUrl())
                .P17_validateCode(request.getValidateCode())
                .signatureType(request.getSignatureType()).build();
        return quickPaymentService.bindCardPayment(vo);
    }

    @PostMapping(value = { "quickPayQuery" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_quickPayQuery",value = "合利宝快捷支付订单查询接口", notes = "合利宝快捷支付订单查询接口")
    public SilkroadResponse<QuickPayQueryResp> quickPayQuery(@RequestBody QuickPayQueryReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【订单查询】请求参数异常");
        }
        //组装请求合利宝接口参数
        QuickPayQueryVO vo = QuickPayQueryVO.builder()
                .P1_bizType(HelipayBizType._QUICKPAYQUERY.value)
                .P2_orderId(request.getOrderId())
                .P3_customerNumber(customerNumber)
                .signatureType(request.getSignatureType()).build();
        return quickPaymentService.quickPayQuery(vo);
    }

    @PostMapping(value = { "bankCardUnbind" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_bankCardUnbind",value = "合利宝快捷支付银行卡解绑接口", notes = "合利宝快捷支付银行卡解绑接口")
    public SilkroadResponse<BankCardUnbindResp> bankCardUnbind(@RequestBody BankCardUnbindReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【银行卡解绑】请求参数异常");
        }
        //组装请求合利宝接口参数
        BankCardUnbindVO vo = BankCardUnbindVO.builder()
                .P1_bizType(HelipayBizType._BANKCARDUNBIND.value)
                .P2_customerNumber(customerNumber)
                .P3_userId(request.getUserId())
                .P4_bindId(request.getBindId())
                .P5_orderId(request.getOrderId())
                .P6_timestamp(request.getTimestamp())
                .signatureType(request.getSignatureType())
                .build();
        return quickPaymentService.bankCardUnbind(vo);
    }

    @PostMapping(value = { "bankCardbindList" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_quickPayment_bankCardbindList",value = "用户绑定银行卡信息查询", notes = "合利宝快捷支付银行卡解绑接口")
    public SilkroadResponse<BankCardbindListResp> bankCardbindList(@RequestBody BankCardbindListReq request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【用户绑定银行卡信息查询】请求参数异常");
        }
        //组装请求合利宝接口参数
        BankCardbindListVO vo = BankCardbindListVO.builder()
                .P1_bizType(HelipayBizType._BANKCARDBINDLIST.value)
                .P2_customerNumber(customerNumber)
                .P3_userId(request.getUserId())
                .P4_bindId(request.getBindId())
                .P5_timestamp(request.getTimestamp())
                .signatureType(request.getSignatureType()).build();
        return quickPaymentService.bankCardbindList(vo);
    }

    @PostMapping(value = { "syncNotice" }, produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    @ApiOperation(nickname="trade_quickPayment_syncNotice",value = "合利宝快捷支付异步通知接口", notes = "合利宝快捷支付异步通知接口")
    public String syncNotice(HttpServletRequest request){
        SyncNoticeVO vo = SyncNoticeVO.builder()
                .rt1_bizType(request.getParameter("rt1_bizType"))
                .rt2_retCode(request.getParameter("rt2_retCode"))
                .rt3_retMsg(request.getParameter("rt3_retMsg"))
                .rt4_customerNumber(request.getParameter("rt4_customerNumber"))
                .rt5_orderId(request.getParameter("rt5_orderId"))
                .rt6_serialNumber(request.getParameter("rt6_serialNumber"))
                .rt7_completeDate(request.getParameter("rt7_completeDate"))
                .rt8_orderAmount(request.getParameter("rt8_orderAmount"))
                .rt9_orderStatus(request.getParameter("rt9_orderStatus"))
                .rt10_bindId(request.getParameter("rt10_bindId"))
                .rt11_bankId(request.getParameter("rt11_bankId"))
                .rt12_onlineCardType(request.getParameter("rt12_onlineCardType"))
                .rt13_cardAfterFour(request.getParameter("rt13_cardAfterFour"))
                .rt14_userId(request.getParameter("rt14_userId"))
                .sign(request.getParameter("sign"))
                .build();
        LOGGER.info("【绑卡支付回调】请求参数:"+JsonUtils.getInstance().toJson(vo));
        return quickPaymentService.syncNotice(vo);
    }
}
