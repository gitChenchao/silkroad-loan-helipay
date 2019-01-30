package com.danning.silkroad.helipay.controller;

import com.danning.silkroad.helipay.common.response.SilkroadResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentBizResponse;
import com.danning.silkroad.helipay.common.response.builder.SinglePaymentQueryBizResponse;
import com.danning.silkroad.helipay.service.TransferService;
import com.danning.silkroad.helipay.utils.JsonUtils;
import com.danning.silkroad.helipay.utils.ValidatorUtil;
import com.danning.silkroad.helipay.vo.PaymentResultNoticeVO;
import com.danning.silkroad.helipay.vo.SinglePaymentQueryVO;
import com.danning.silkroad.helipay.vo.SinglePaymentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类名称：TradeApi<br>
 * 类描述：合利宝-代付-交易API<br>
 * 创建时间：2019年01月25日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@RestController
@Slf4j
@Api(value = "合利宝-代付-交易API", description = "合利宝-代付-交易接口")
@RequestMapping("/trade/trans/")
public class TransferApi {

    private final TransferService transferService;

    public TransferApi(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(value = { "singlePayment" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_trans_authBindCardSms",value = "单笔代付", notes = "单笔代付")
    public SilkroadResponse<SinglePaymentBizResponse> singlePayment(@RequestBody SinglePaymentVO request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【单笔代付】请求参数异常");
        }
        return transferService.singlePayment(request);
    }

    @PostMapping(value = { "singlePayment" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ApiOperation(nickname="trade_trans_singlePayment",value = "单笔代付查询接口", notes = "单笔代付查询接口")
    public SilkroadResponse<SinglePaymentQueryBizResponse> singlePaymentQuery(@RequestBody SinglePaymentQueryVO request){
        Map<String, String> errorMap = ValidatorUtil.validate(request);
        if (errorMap.size() > 0) {
            LOGGER.error("请求参数【{}】异常", JsonUtils.getInstance().toJson(errorMap));
            return SilkroadResponse.fail("【单笔代付查询接口】请求参数异常");
        }
        return transferService.singlePaymentQuery(request);
    }

    @PostMapping(value = { "paymentResultNotice" }, produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    @ApiOperation(nickname="trade_trans_paymentResultNotice",value = "合利宝代付回调接口", notes = "合利宝代付回调接口")
    public String paymentResultNotice(HttpServletRequest request){
        PaymentResultNoticeVO vo = PaymentResultNoticeVO.builder()
                .rt1_bizType(request.getParameter("rt1_bizType"))
                .rt2_retCode(request.getParameter("rt2_retCode"))
                .rt3_retMsg(request.getParameter("rt3_retMsg"))
                .rt4_customerNumber(request.getParameter("rt4_customerNumber"))
                .rt5_orderId(request.getParameter("rt5_orderId"))
                .rt6_serialNumber(request.getParameter("rt6_serialNumber"))
                .rt7_orderStatus(request.getParameter("rt7_orderStatus"))
                .rt8_notifyType(request.getParameter("rt8_notifyType"))
                .rt9_reason(request.getParameter("rt9_reason"))
                .rt10_createDate(request.getParameter("rt10_createDate"))
                .rt11_completeDate(request.getParameter("rt11_completeDate"))
                .sign(request.getParameter("sign"))
                .build();
        LOGGER.info("【代付回调通知】请求参数:"+JsonUtils.getInstance().toJson(vo));
        return transferService.paymentResultNotice(vo);
    }
}
