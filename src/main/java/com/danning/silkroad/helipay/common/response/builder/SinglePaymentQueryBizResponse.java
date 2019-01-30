package com.danning.silkroad.helipay.common.response.builder;

import com.danning.silkroad.helipay.annotation.SignExclude;
import lombok.Data;

/**
 * 类名称：SinglePaymentQueryBizResponse<br>
 * 类描述：<br>
 * 创建时间：2019年01月27日<br>
 *
 * @author 陈超
 * @version 1.0.0
 */
@Data
public class SinglePaymentQueryBizResponse {
    /**
     * 交易类型
     */
    private String rt1_bizType;

    /**
     * 返回码
     */
    private String rt2_retCode;

    /**
     * 返回信息
     */
    @SignExclude
    private String rt3_retMsg;

    /**
     * 商户编号
     */
    private String rt4_customerNumber;

    /**
     * 商户订单号
     */
    private String rt5_orderId;

    /**
     * 平台流水号
     */
    private String rt6_serialNumber;

    /**
     * 打款状态
     */
    private String rt7_orderStatus;

    /**
     * 返回信息
     */
    @SignExclude
    private String rt8_reason;

    /**
     * 签名
     */
    @SignExclude
    private String sign;
}
